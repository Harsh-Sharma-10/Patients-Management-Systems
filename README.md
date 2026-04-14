# 🏥 Patient Management SaaS Platform

A scalable **Patient Management SaaS platform** built using a **microservices architecture** to efficiently manage patient records and billing workflows.

---

## 🚀 Overview

This project is designed to simulate a real-world healthcare backend system where multiple services communicate seamlessly using modern backend technologies.

The system handles:
- Patient record management
- Billing workflows
- Asynchronous event processing
- Inter-service communication

---

## 🏗️ Architecture

The platform follows a **Microservices Architecture**, where each service is independently developed, deployed, and scaled.

### 🔧 Core Services

- **Patient Service**
  - Manages patient records (CRUD operations)
  
- **Billing Service**
  - Handles billing logic and transactions
  
- **Database Service**
  - Responsible for data persistence and interaction with PostgreSQL

---

## 🔗 Communication

### 1. REST APIs
- Used for synchronous communication between services

### 2. gRPC
- High-performance communication between internal services

### 3. Apache Kafka
- Enables **event-driven architecture**
- Handles asynchronous workflows like:
  - Patient creation events
  - Billing updates

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot  
- **Communication:** REST APIs, gRPC  
- **Messaging Queue:** Apache Kafka  
- **Database:** PostgreSQL  
- **Containerization:** Docker  

---

## 📦 Features

- ✅ Scalable microservices architecture  
- ✅ Event-driven system using Kafka  
- ✅ Efficient inter-service communication with gRPC  
- ✅ REST APIs for external interaction  
- ✅ Dockerized services for easy deployment  
- ✅ Separation of concerns across services  

---

## 🐳 Docker Setup

Each service is containerized using Docker.

### Steps to run locally:

```bash
# Clone the repository
git clone https://github.com/your-username/patient-management-saas.git

# Navigate to project directory
cd patient-management-saas

# Build and run containers
docker-compose up --build
