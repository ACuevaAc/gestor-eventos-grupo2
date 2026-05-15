# TableReserve System
A robust Java Swing application designed to manage restaurant or venue reservations. This system allows users to book tables in real-time while providing administrators with a powerful dashboard for management.

## Project Overview
This application facilitates a complete workflow for table management:
    - Users can register accounts, view available tables, and manage their personal bookings.
    - The admins oversee the entire ecosystem, modify table statuses, and manage user records.
    - The architecture is built using a layered architecture (DAO/Service) to ensure scalability and clean code.
## Project Structure
```
── src
    └── com
        ├── config
        └── gestor
            ├── controller
            ├── main
            ├── model
            │   ├── dao
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
