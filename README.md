# EC: Microservicios - Gestión de Tareas

## Contexto
Se desarrolló una solución basada en microservicios para una aplicación sencilla de gestión de tareas.
El objetivo es registrar tareas, manejar usuarios y generar notificaciones cuando se crea una tarea.

## Microservicios
1. **tasks-service (puerto 8081)**
   - Crear tareas y listar tareas.
   - Al crear una tarea, envía una notificación al microservicio notifications-service.

2. **users-service (puerto 8082)**
   - Registrar usuarios y listar usuarios.

3. **notifications-service (puerto 8083)**
   - Registrar notificaciones y listar notificaciones.

## Endpoints (pruebas con Postman)
### tasks-service
- GET `http://localhost:8081/tasks`
- POST `http://localhost:8081/tasks`
  - Body:
    ```json
    { "title": "Nueva tarea" }
    ```

### users-service
- GET `http://localhost:8082/users`
- POST `http://localhost:8082/users`
  - Body:
    ```json
    { "name": "Victor", "email": "victor@mail.com" }
    ```

### notifications-service
- GET `http://localhost:8083/notifications`

## Ejecución
1. Abrir cada microservicio en IntelliJ.
2. Ejecutar la clase principal (Spring Boot) de cada uno.
3. Probar los endpoints con Postman.