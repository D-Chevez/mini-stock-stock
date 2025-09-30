# MINI STOCK SYSTEM


# Proyecto Mini Stock System – SUPPLIER y CUSTOMER

## 🚀 Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3** (Web, Data JPA, Validation)
- **H2 Database** (en memoria)
- **Maven**
- **Postman** (para pruebas)

## Entidades:

### SUPPLIER
- `id` (Long) – Identificador único.
- `name` (String) – Nombre del proveedor.
- `email` (String) – Correo electrónico.
- `phone` (String) – Teléfono.

### CUSTOMER
- `id` (Long) – Identificador único.
- `name` (String) – Nombre del cliente.
- `email` (String) – Correo electrónico.
- `phone` (String) – Teléfono.

---

## Endpoints:

### SUPPLIER
- **GET** `/api/suppliers` → Lista proveedores.
- **POST** `/api/suppliers` → Crear proveedor.
- **GET** `/api/suppliers/{id}` → Buscar proveedor por ID.
- **PUT** `/api/suppliers/{id}` → Actualizar proveedor.
- **DELETE** `/api/suppliers/{id}` → Eliminar proveedor.

### CUSTOMER
- **GET** `/api/customers` → Lista clientes.
- **POST** `/api/customers` → Crear cliente.
- **GET** `/api/customers/{id}` → Buscar cliente por ID.
- **PUT** `/api/customers/{id}` → Actualizar cliente.
- **DELETE** `/api/customers/{id}` → Eliminar cliente.

---

## Notas:
- La base de datos **H2** se ejecuta en memoria, los datos se pierden al cerrar la aplicación. 

