package com.chauri.portfolio.throttling;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ApiRateLimitFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(ApiRateLimitFilter.class);
    private final Map<String, Bucket> endpointBuckets = new ConcurrentHashMap<>();

    @Value("${app.rate-limit.enabled:true}")
    private boolean rateLimitEnabled;

    @Value("${app.rate-limit.homepage.capacity:5}")
    private int homepageCapacity;

    @Value("${app.rate-limit.message.capacity:2}")
    private int messageCapacity;

    @PostConstruct
    void initBuckets() {
        Bucket portfolioBucket = createBucket(homepageCapacity);
        endpointBuckets.put("", portfolioBucket);
        endpointBuckets.put("/", portfolioBucket);
        endpointBuckets.put("/portfolio", portfolioBucket);

        endpointBuckets.put("/save-message", createBucket(messageCapacity));
    }

    private Bucket createBucket(int capacity) {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(capacity, Refill.intervally(capacity, Duration.ofMinutes(1))))
                .build();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, jakarta.servlet.ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!rateLimitEnabled) {
            chain.doFilter(request, response);
            return;
        }

        String path = httpRequest.getRequestURI();

        // Custom attribute to check if the request is already processed
        if (httpRequest.getAttribute("ApiRateLimitFilter-Processed") != null) {
            chain.doFilter(request, response);
            return;
        }

        // Mark the request as processed
        httpRequest.setAttribute("ApiRateLimitFilter-Processed", true);

        Bucket bucket = endpointBuckets.get(path);

        if (bucket != null) {
            if (bucket.tryConsume(1)) {
                log.info("Request allowed for path: {}. Remaining tokens: {}", path, bucket.getAvailableTokens());
                chain.doFilter(request, response);
            } else {
                log.warn("Rate limit exceeded for path: {}", path);
                handleRateLimit(httpRequest, httpResponse, path);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void handleRateLimit(HttpServletRequest request, HttpServletResponse response, String path)
            throws IOException {
        if (path.equals("/save-message")) {
            Bucket homePageBucket = endpointBuckets.get("/");
            if (homePageBucket != null && !homePageBucket.tryConsume(1)) {
                response.sendRedirect("/rate-limit-error?message=Message+sending+limit+reached&homepageError=Home+page+rate+limit+reached");
            } else {
                response.sendRedirect("/?rateLimitError=Message+sending+limit+reached");
            }
        } else if (path.equals("/") || path.equals("/portfolio") || path.isEmpty()) {
            response.sendRedirect("/rate-limit-error?message=Home+page+rate+limit+reached");
        }
    }
}

