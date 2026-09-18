# CivicPulse

### Citizen Services with Intelligent Issue Tracking and Community Insights

CivicPulse is a Java-based civic issue management system that allows citizens to register complaints and helps administrators track, analyze, and manage those complaints efficiently.

The system combines **Java, OOP, JDBC, MySQL, and AI/ML** to provide intelligent complaint priority prediction, department assignment, suggestions, analytics, and decision support.

---

## 📌 Project Overview

CivicPulse is designed as a console-based citizen service application.

A citizen can:

- Register as a citizen
- Submit civic complaints
- View their complaints
- Update their address
- Delete their account

Administrators can:

- View and manage complaints
- Update complaint status
- Filter complaints
- View department-wise complaints
- View priority-wise complaints
- View high-priority pending complaints
- View complaint analytics
- View AI priority analytics
- Use AI decision support

---

## ✨ Key Features

### 👤 Citizen Management

- Add Citizen
- View Citizens
- Update Citizen Address
- Delete Citizen
- View Citizen Complaints

### 📝 Complaint Management

Citizens can register complaints under:

- Traffic
- Waste
- Water
- Electricity

Each complaint contains:

- Title
- Description
- Location
- Category
- Status
- Priority
- Suggestion
- Complaint Date

### 🏢 Department Assignment

Complaints are automatically assigned to departments based on their category.

| Category | Department |
|---|---|
| Traffic | Traffic Department |
| Waste | Waste Department |
| Water | Water Department |
| Electricity | Electricity Department |

### 🚦 Complaint Priority

Complaints are classified into:

- HIGH
- MEDIUM
- LOW

The system uses AI/ML priority prediction when the AI service is available.

If the AI service is unavailable, the system automatically falls back to rule-based priority classification.

### 🤖 AI/ML Integration

CivicPulse integrates a Python FastAPI service with the Java application.

The machine learning pipeline uses:

- TF-IDF Vectorization
- Support Vector Machine (SVM)
- Logistic Regression
- Random Forest
- Joblib model persistence

The trained model predicts complaint priority from complaint text.

The current trained model achieved:

**91.67% test accuracy using SVM** on the project's 120-record dataset.

### 💡 AI Decision Support

The system provides decision-support information for high-priority pending complaints.

It can generate recommendations such as:

- Dispatching the appropriate department
- Controlling affected water flow
- Securing an affected area
- Alerting traffic control

### 📊 Complaint Analytics

Administrators can view:

- Total complaints
- Pending complaints
- In-progress complaints
- Resolved complaints
- Category-wise analytics
- Department-wise complaints
- Priority-wise complaints
- AI priority analytics

### 📄 Report Generation

The system can generate a complaint report using file handling.

---

## 🏗️ System Architecture

```text
                    CivicPulse
                        │
                        ▼
                Console / User
                        │
                        ▼
                  Service Layer
                        │
             ┌──────────┴──────────┐
             ▼                     ▼
        DAO Layer              AI Service
             │                     │
             ▼                     ▼
          MySQL              Python FastAPI
                                   │
                                   ▼
                              TF-IDF + SVM