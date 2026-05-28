# TableReserve System
A robust Java Swing application designed to manage restaurant or venue reservations. This system allows users to book tables in real-time while providing administrators with a powerful dashboard for management.

## Project Overview
This application facilitates a complete workflow for table management:
    - Users can register accounts, view available tables, and manage their personal bookings.
    - The admins oversee the entire ecosystem, modify table statuses, and manage user records.
    - The architecture is built using a layered architecture (DAO/Service) to ensure scalability and clean code.

## Group Members
This project has been developed as a team by:
- **[Alejandro Cueva Acosta / ACuevaAc]** - Developer & Architect
- **[Víctor Chacón García / ether]** - Developer & DevOp
- **[Jose Manuel Ortiz / JosemaDev06 ]** - Developer & View Designer

## Task Allocation (Reparto de Tareas)
To optimize development, the workload was systematically distributed across specialized modules:

| Member | Assigned Modules & Responsibilities |
| :--- | :--- |
| **[Alejandro Cueva Acosta]** | Core System Architecture, Administrative Control Modules and Statistics Designer |
| **Víctor Chacón García** | Database Integration, Layered Validation Workflow, Git Workflow Management and Documentation. |
| **[Jose Manuel Ortiz]** | Frontend Development, Java Swing Layout UI design and Statistics Designer |

## Usage Instructions

### 1. Execution
1. Ensure your PostgreSQL database instance is up and running.
2. Compile and package the project using your standard Java IDE.
3. Execute the application lifecycle by running the main entry point:
   ```bash
   src/com/gestor/main/Main.java
   ```

### 2. User Workflow (How to use)

**As a regular User:**
- Open the application and navigate to the Signup screen to register a new account.
- Log in using your fresh credentials.
- Browse the real-time available tables and book your spot.

**As an Administrator:**
- Log in using the default secure infrastructure admin credentials provided below.
- Access the administrative panel to manage table inventory states, view live registration telemetry charts, and overview user listings.

## Project Structure
```
── src
    └── com
        ├── config
        └── gestor
            ├── controller
            ├── main
            ├── model
            │   └── entity
            ├── service
            └── view
```

## Technical Stack
    Language: Java (JDK 17+)
    GUI Library: Java Swing
    Database: PostgreSQL
    Connection: JDBC

## Setup and Configuration
### Prerequisites
    A running PostgreSQL instance.
    Java 17 or higher installed on your system.
### Database Connection
Update the Singleton connection class in the config package with your VPS credentials:
```java
String url = "jdbc:postgresql://YOUR_VPS_IP:5432/gegdb";
String user = "adm";
String password = "your_secure_password";
```

## Default Credentials
Use these credentials for the initial login after running the database migration script.
| Role | Email | Password |
| :--- | :---  | :---     |
| Administrador | admin@gegdb.com | admin123 |

[!IMPORTANT]
The default admin password is encrypted using SHA-512. If you are manually inserting users into the database, ensure the password string is hashed before the INSERT operation.
