# 📋 Sistema de Gestión de Tareas — Microservicios

> Proyecto Final — Curso: Desarrollo de Servicios Web 2  
> Docente: Ing. Alberto Velásquez  
> Instituto: IDAT

---

## 📌 Descripción

Sistema de gestión de tareas construido con arquitectura de **microservicios**, donde los usuarios pueden crear, actualizar y eliminar tareas. Cada vez que se crea una tarea, el sistema genera una notificación automáticamente mediante comunicación entre servicios usando **Feign Client**.

---

## 🏗️ Arquitectura

```
[Cliente / Postman]
        │
        ├──▶ users-service    (puerto 8082)  →  Base de datos PostgreSQL
        │
        ├──▶ tasks-service    (puerto 8081)  →  Base de datos PostgreSQL
        │         │
        │         └──Feign──▶ notifications-service (puerto 8083)
        │
        └──▶ notifications-service (puerto 8083) → Base de datos PostgreSQL
```

### Microservicios

| Servicio | Puerto | Descripción |
|----------|--------|-------------|
| `users-service` | 8082 | Gestión de usuarios |
| `tasks-service` | 8081 | Gestión de tareas (llama a notifications via Feign) |
| `notifications-service` | 8083 | Registro de notificaciones |

---

## 🛠️ Tecnologías

- **Java 21** + **Spring Boot 3.3.5**
- **Spring Data JPA** — persistencia de datos
- **Spring Cloud OpenFeign** — comunicación entre microservicios
- **PostgreSQL** — base de datos relacional
- **Docker** + **Docker Compose** — contenedorización
- **Lombok** — reducción de código boilerplate

---

## 📦 Requisitos

- Java 21+
- Docker Desktop
- IntelliJ IDEA (o cualquier IDE Java)

---

## 🚀 Ejecución con Docker Compose

### 1. Compilar cada microservicio

Desde la raíz del proyecto, entra a cada carpeta y compila:

```bash
cd users-service
./mvnw clean package -DskipTests
cd ..

cd tasks-service
./mvnw clean package -DskipTests
cd ..

cd notifications-service
./mvnw clean package -DskipTests
cd ..
```

### 2. Levantar todo con Docker Compose

```bash
docker-compose up --build
```

### 3. Verificar que todo funciona

| URL | Resultado esperado |
|-----|--------------------|
| http://localhost:8082/users | `[]` |
| http://localhost:8081/tasks | `[]` |
| http://localhost:8083/notifications | `[]` |

---

## 🖥️ Ejecución local (sin Docker)

### 1. Levantar PostgreSQL

```bash
docker run --name postgres-micro \
  -e POSTGRES_DB=microservicios \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 -d postgres
```

### 2. Ejecutar cada servicio en IntelliJ

Abrir y ejecutar en orden:
1. `NotificationsServiceApplication.java`
2. `UsersServiceApplication.java`
3. `TasksServiceApplication.java`

---

## 📡 Endpoints principales

### Users Service — `http://localhost:8082`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/users` | Listar usuarios |
| POST | `/users` | Crear usuario |
| GET | `/users/{id}` | Obtener usuario por ID |
| PUT | `/users/{id}` | Actualizar usuario |
| DELETE | `/users/{id}` | Eliminar usuario |

### Tasks Service — `http://localhost:8081`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/tasks` | Listar tareas |
| POST | `/tasks` | Crear tarea (genera notificación automática) |
| PUT | `/tasks/{id}` | Actualizar tarea |
| DELETE | `/tasks/{id}` | Eliminar tarea |

### Notifications Service — `http://localhost:8083`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/notifications` | Listar notificaciones |
| POST | `/notifications` | Crear notificación (usado internamente por Feign) |

---

## 🔗 Comunicación entre microservicios (Feign)

Cuando se crea una tarea en `tasks-service`, automáticamente se llama a `notifications-service` usando **Feign Client**:

```
POST /tasks  →  tasks-service  →  Feign  →  POST /notifications
```

Esto demuestra la interconexión entre microservicios requerida por el proyecto.

---

## 🐳 Estructura Docker

```
ec-microservicios-tareas/
├── users-service/
│   └── Dockerfile
├── tasks-service/
│   └── Dockerfile
├── notifications-service/
│   └── Dockerfile
└── docker-compose.yml
```

---

## 📁 Estructura del proyecto

```
ec-microservicios-tareas/
├── users-service/
│   └── src/main/java/com/idat/usersservice/
│       ├── controller/UserController.java
│       ├── model/User.java
│       ├── repository/UserRepository.java
│       └── UsersServiceApplication.java
├── tasks-service/
│   └── src/main/java/com/idat/tasksservice/
│       ├── client/NotificationClient.java     ← Feign Client
│       ├── controller/TaskController.java
│       ├── model/Task.java
│       ├── repository/TaskRepository.java
│       └── TasksServiceApplication.java
├── notifications-service/
│   └── src/main/java/com/idat/notificationsservice/
│       ├── controller/NotificationController.java
│       ├── model/Notification.java
│       ├── repository/NotificationRepository.java
│       └── NotificationsServiceApplication.java
├── docker-compose.yml
└── README.md
```

---

## 👥 Integrantes

- [Tu nombre aquí]
- [Nombre compañero 2]
- [Nombre compañero 3]
