# **Mini Event - Backend API**

## **Overview**

This is the backend server for **Mini Event**, a mobile application designed to simplify event management for non-professional organizers. Traditional event organization often involves a fragmented and manual process using tools like Google Forms, Sheets, and manual emails. Mini Event streamlines this entire workflow into a single, intuitive mobile platform.

This backend, built with **Spring Boot**, provides a robust RESTful API to power the mobile application, handling everything from event creation and guest management to real-time check-ins and user authentication.

- **Frontend (Mobile App) Repository:** [https://github.com/hoangduc102/Mini-Event](https://github.com/hoangduc102/Mini-Event)
- **Live Demo:** [Video Demo](https://drive.google.com/drive/folders/1jD-oEGVb1an73e821t5c8G5vrVOteXvW)

## **Features**

- **Event Management:** Create, update, delete, and view public/private events with detailed information.
- **Guest List Management:** Easily add guests via email or phone number and automatically send invitations through an integrated SMTP service.
- **Smart Check-in:** Supports multiple check-in methods including dynamic QR codes and optional GPS verification for on-site validation.
- **Real-time Updates:** Leverages Firebase Realtime Database to provide instant updates for check-in statuses and guest lists on the organizer's dashboard.
- **Public Event Discovery:** A newsfeed for users to discover and join public events.
- **Secure Authentication:** User authentication and authorization handled via Firebase Authentication and JWTs.

## **Architecture**

The backend follows a **Layered Architecture** combined with principles of **Clean Architecture** to ensure a clean separation of concerns, high cohesion, and low coupling.

1.  **Web Adapter (Controller Layer):** Handles incoming HTTP requests, validates input (DTOs), and delegates tasks to the Usecase layer.
2.  **Usecase (Application Layer):** Contains the core business logic and orchestrates interactions between the adapter and service layers.
3.  **Service Layer:** Implements specific tasks such as sending emails, generating QR codes, or handling authentication logic.
4.  **Repository Layer:** Manages all data access and communication directly with the database (Firebase).

![Alt text](assets\architecture.png)

## **Tech Stack**

| Component             | Technology/Service          | Role                                              |
| :-------------------- | :-------------------------- | :------------------------------------------------ |
| **Backend Framework** | **Spring Boot**             | Building RESTful APIs, business logic processing. |
| **Database**          | **Firebase Firestore**      | Real-time data storage, low-latency check-ins.    |
| **Authentication**    | **Firebase Authentication** | User sign-up, sign-in, and security management.   |
| **CI/CD**             | **GitHub Actions**          | Automated build and deployment pipeline.          |
| **API Documentation** | **Swagger**                 | Auto-generated, interactive API documentation.    |
| **Message Broker**    | **RabbitMQ**                | Asynchronous mail sender.                         |
| **Image/Asset Host**  | **Cloudinary**              | Cloud-based image management.                     |

## **Project Report & Detailed Documentation**

This repository contains the backend implementation for the Mini Event project. For a comprehensive understanding of the project—from the initial problem statement to architecture design, technology choices, and final evaluation—please refer to the original project report.

The report includes:

- In-depth problem analysis and project objectives.
  Detailed system architecture diagrams and explanations.
- Complete UI/UX user flow charts.
- API design principles and endpoint specifications.
- Justification for the chosen technology stack.
- User satisfaction survey results and analysis.

View the Full Project Report [Here](assets\MOBILE_ASSIGNMENT_3_FINAL.pdf)

## **API Endpoints**

The API is versioned and follows RESTful conventions. The base URL is `/api/v1`.

### **Events**

- `GET /events`: Get a list of public events.
- `GET /events/{eventId}`: Get details for a specific event.
- `POST /events`: Create a new event.
- `PUT /events/{eventId}`: Update an existing event.
- `DELETE /events/{eventId}`: Delete an event.

### **Registration & Check-in**

- `POST /events/{eventId}/registrations`: Register for an event.
- `GET /events/{eventId}/registrations`: Get guest list and check-in status.
- `POST /events/{eventId}/checkin`: Submit a check-in request (QR or GPS).

### **Users**

- `POST /users/register`: Register a new user.
- `POST /users/login`: Log in and receive a JWT access token.

> For detailed information on all endpoints, request/response models, and to try out the API, please visit our Swagger documentation at `/swagger-ui/index.html`.

## **Getting Started**

### **Prerequisites**

- JDK 21 or higher
- Maven 3.8 or higher
- A Firebase project with Firestore, and Authentication enabled.
- An active Sentry account for error monitoring.

### **Installation & Setup**

1.  **Clone the repository:**

    ```sh
    git clone https://github.com/Brookvita3/MiniEvent
    cd your-backend-repo
    ```

2.  **Configure Firebase:**

    - Download your Firebase `serviceAccountKey.json` file.
    - Place it in the `src/main/resources` directory.
    - Update `application.properties` with your Firebase project details.

3.  **Configure Environment Variables:**

    - The application is configured using environment variables. For local development, you can create a .env file in the root of the project and install a tool like dotenv for your IDE, or simply export these variables in your shell.
    - Create a file named .env and populate it with the following keys.

    ```sh
    DISTANCE_THRESHOLD=100
    VERSION=1.0.0

    # Email (Gmail SMTP)

    # Note: You may need to create an "App Password" from your Google Account settings

    EMAIL_USERNAME=your-email@gmail.com
    EMAIL_PASSWORD=your-google-app-password

    # Cloudinary Credentials

    CLOUDINARY_NAME=your_cloud_name
    CLOUDINARY_API_KEY=your_api_key
    CLOUDINARY_API_SECRET=your_api_secret
    CLOUDINARY_URL=cloudinary://<api_key>:<api_secret>@<cloud_name>

    # RabbitMQ Credentials

    # For local development, you can run a RabbitMQ container with Docker

    RABBITMQ_HOSTNAME=localhost
    RABBITMQ_USERNAME=guest
    RABBITMQ_PASSWORD=guest

    # Security

    QR_SECRET_KEY=your-strong-secret-key-for-qr-codes

    # You also need a JWT secret key for token authentication

    # JWT_SECRET=your-strong-secret-key-for-jwt

    # Swagger Documentation

    # The base URL of your server, e.g., http://localhost:8080

    SPRINGDOC_SERVER_URL=http://localhost:8080
    ```

    - You can run the application using Maven:

    ```sh
    mvn spring-boot:run
    ```

The server will start on `http://localhost:8080`.

## **CI/CD Pipeline**

This project uses **GitHub Actions** for continuous integration and continuous deployment. A workflow is configured to automatically:

1.  **Build:** Check out the code, set up Java, and build the project using Maven, creating a `.jar` artifact.
2.  **Deploy:** Log in to Azure, and deploy the `.jar` artifact to **Azure App Service**.

This automated process is triggered on every push to the `main` branch, ensuring rapid and reliable deployments.

## **Contributors**

| Name             | ID      |
| :--------------- | :------ |
| Đặng Quốc Phong  | 2212548 |
| Trần Chính Bách  | 2210187 |
| Hoàng Mạnh Đức   | 2210787 |
| Huỳnh Đức Nguyên | 2252542 |
