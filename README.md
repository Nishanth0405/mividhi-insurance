**Overview**

This is the backend service for the Insurance Platform, built using Spring WebFlux (Reactive Spring) and Maven. It provides RESTful APIs for managing insurance policies, supporting search, filtering, sorting, and authentication.

**🛠 Setup Instructions**

✅ Prerequisites

Ensure you have the following installed before proceeding:

Java 21

Maven (Apache Maven 3.8+)

MongoDB 

**📌 Installation Steps**

Clone the repository

git clone <repository-link>
cd backend

**Build the project**

    mvn clean install

**Set up environment variables**

    Create an .env file or set properties in application.yml:

server:
  port: 8080
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/insuranceDB

**Run the application**

mvn spring-boot:run

The API will be available at http://localhost:8080
