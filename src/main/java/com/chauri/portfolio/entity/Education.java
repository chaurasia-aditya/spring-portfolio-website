package com.chauri.portfolio.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "degree")
    private String degree;

    @Column(name = "program")
    private String program;

    @Column(name = "courses_taken")
    private String coursesTaken;

    @Column(name = "start_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "image_path")
    private String imagePath;

    @Transient
    private List<String> courses;

    public Education() {
    }

    public Education(String universityName, String degree, String program, String coursesTaken, LocalDate startDate, LocalDate endDate, String imagePath) {
        this.universityName = universityName;
        this.degree = degree;
        this.program = program;
        this.coursesTaken = coursesTaken;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imagePath = imagePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getCoursesTaken() {
        return coursesTaken;
    }

    public void setCoursesTaken(String coursesTaken) {
        this.coursesTaken = coursesTaken;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses() {
        if (coursesTaken != null && !coursesTaken.isEmpty()) {
            String[] temp = coursesTaken.trim().split("\\.\\s+");
            this.courses = Arrays.asList(temp);
        } else{
            this.courses = Collections.emptyList();
        }
    }

    @Override
    public String toString() {
        return "Education{" +
                "universityName='" + universityName + '\'' +
                ", degree='" + degree + '\'' +
                ", program='" + program + '\'' +
                ", coursesTaken='" + coursesTaken + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", imagePath='" + imagePath + '\'' +
                ", courses=" + courses +
                '}';
    }
}
