# RecruitCRMAssignment

A robust **Spring Boot 3 REST API** designed to manage enterprise workforce data. This system tracks employees across departments, their specific roles within various projects, and their historical performance reviews.

## 🚀 Key Features

* **Advanced Filtering Engine:** Implements a "Set Intersection" logic to handle complex "AND" searches across multiple One-to-Many relationships without duplicate record issues.
* **Performance Analytics:** Custom mapping logic that automatically extracts and ranks the **top 3 most recent** performance reviews for every employee profile.
* **Optimized Persistence:** Solved the `MultipleBagFetchException` and optimized data retrieval using `@EntityGraph` to eliminate N+1 query problems.
* **Decoupled Architecture:** Utilizes a **Manual Mapper** pattern with POJO DTOs to ensure a secure, versionable API contract that is independent of the database schema.



## 🛠️ Tech Stack

* **Java 17**
* **Spring Boot 3.x** (Web, Data JPA)
* **H2 Database** (File-based persistence)
* **Maven**
* **Jackson** (JSON Processing)

## 📊 Entity Relationship Diagram

The system manages five core interconnected entities:
1.  **Department**: Organizational units.
2.  **Employee**: Central profile managing links to projects and reviews.
3.  **Project**: Specific corporate initiatives.
4.  **EmployeeProject**: Join entity storing specific **Roles** (Lead, Developer, etc.) per assignment.
5.  **PerformanceReview**: Historical performance data including scores and comments.

## 🔌 API Endpoints

### 1. Filtered Search
`GET /api/employees`
**Query Params:** `departmentNames`, `projectNames`, `reviewDate`, `minScore`  
Returns a summary list of employees who meet **all** specified criteria.

### 2. Detailed Profile
`GET /api/employees/{id}`  
Returns full details including the department name, list of project assignments (with roles), and the last 3 performance reviews.

## ⚙️ Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Anandanajain/RecruitCRMAssignment.git