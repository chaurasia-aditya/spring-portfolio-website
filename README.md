# Portfolio Website

This is a personal portfolio website built using Spring Boot, Thymeleaf, MySQL, Spring Data JPA, Hibernate, Spring Boot validation, HTML, CSS, JavaScript, and Bootstrap.

This project is not currently hosted. You can access a static version of the site (without the admin features) at: [chaurasia-aditya.github.io](https://chaurasia-aditya.github.io/)


https://github.com/user-attachments/assets/bab07c1c-1187-4cc6-9d20-63d2e4269a59


## Features

- Responsive Design: The website is fully responsive and works on different devices.
- Dynamic Content: Projects and other content are fetched from a MySQL database.
- Validation: Form inputs are validated using Spring Boot validation.
- Admin Control: An admin interface is provided to perform CRUD operations on the tables, dynamically affecting the website.
- Rate Limiting: API requests are rate-limited to enhance security and prevent abuse, with separate limits for the homepage and sending messages.
- Styling: Custom CSS and Bootstrap are used for styling.
- Interactive UI: JavaScript is used for dynamic interactions.

## Technologies Used

- **Backend:** Spring Boot, Thymeleaf, Spring Data JPA, Hibernate
- **Database:** MySQL
- **Validation:** Spring Boot validation
- **Rate Limiting:** Bucket4j
- **Frontend:** HTML, CSS, JavaScript, Bootstrap
- **Build Tool:** Maven
- **Java Version:** Java 17 Corretto

## Setup

- Clone the repository:

  ```
  git clone https://github.com/chaurasia-aditya/spring-portfolio-website.git
  ```
  
- Navigate to the project directory:

  ```
  cd spring-portfolio-website
  ```
  
- Set up the MySQL database:
  - Create a new MySQL database.
  - You can modify and use the SQL scripts provided in the sql-scripts-samples directory to create the necessary database and tables.
  - Update the application.properties file with your database credentials.

    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

- Build:

  ```
  mvn clean install
  ```

- Run:

  ```
  mvn spring-boot:run
  ```

## Usage

- Access the website by navigating to http://localhost:8080 in your web browser.
- Admin control can be accessed from the menu and usedfor performing CRUD operations on the database tables, which dynamically affect the website content.

## Contributing

1. Fork the repository.
2. Create your feature branch (git checkout -b feature/YourFeature).
3. Commit your changes (git commit -m 'Add some feature').
4. Push to the branch (git push origin feature/YourFeature).
5. Open a pull request.

## Acknowledgments

Special thanks to the following individuals whose videos and templates helped bring this project to fruition:

- **Chad Darby:** For Spring Boot lectures. [Link to Course](https://www.udemy.com/course/spring-hibernate-tutorial)
- **Syed Mohsin:** This template helped me develop the front end. [Link to Github](https://github.com/devsyedmohsin/portfolio-template)
