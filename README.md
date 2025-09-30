# 📑 MINI STOCK SYSTEM

Mini Stock System es una aplicación desarrollada con Spring Boot 3, pensada para la gestión simple de inventarios.
Permite registrar productos, organizar categorías, manejar proveedores y clientes, y controlar los movimientos de entrada y salida de stock de manera clara y eficiente.

### ✨ Características principales
- CRUD completo de productos, clientes, proveedores y categorías. 
- Registro y consulta de movimientos de inventario (entradas/salidas). 
- Validaciones automáticas y persistencia con JPA/Hibernate. 
- Migraciones de base de datos gestionadas con Flyway. 
- Base de datos embebida H2 lista para pruebas rápidas.


## 🚀 Como usar este proyecto

### 1️⃣ Clona el repositorio:
```bash
git clone https://github.com/D-Chevez/mini-stock-stock/tree/main
```
### 2️⃣ Configura la base de datos:
El proyecto utiliza una base de datos H2 embebida por defecto, ideal para pruebas rápidas. 
Si deseas usar otra base de datos (MySQL, PostgreSQL, etc.), ajusta las propiedades en `src/main/resources/application.properties`.

### 3️⃣ Ejecuta la aplicación:
Desde tu IDE (IntelliJ, Eclipse, etc.), ejecuta la clase principal `org.kodigo.mini-stock-system.MiniStockApplication`. 
Alternativamente, puedes compilar y ejecutar con Maven:
```bash
mvn spring-boot:run
```

### 4️⃣ Accede a la API:
La aplicación expone una API RESTful. Puedes interactuar con ella usando herramientas como Postman o cURL. 
La documentación de la API está disponible en `http://localhost:8080/swagger-ui.html` (si Swagger está configurado).

---

## 🛠 Technologies
✅ Java 17+  
✅ Spring Boot 3  
✅ IntelliJ IDEA  
✅ Maven  
✅ JPA/Hibernate  
✅ H2 Database (embebida)  
✅ Flyway para migraciones de base de datos  
✅ Postman/Insomnia para pruebas de API  
✅ Swagger para documentación de API  
✅ Git & GitHub para control de versiones  

---

## Estructura del proyecto
Esta aplicación sigue una estructura estándar de proyecto Spring Boot,
implementando buenas practicas, como CLEAN CODE y principios SOLID.
El proyecto utiliza una arquitectura MVC (Modelo-Vista-Controlador) para
separar las responsabilidades y mejorar la mantenibilidad del código. Por lo tanto,
la estructura del proyecto es la siguiente:  

```
mini-stock-system
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org.kodigo.mini-stock-system
│   │   │       ├── model                       # Entidades JPA
│   │   │       ├── repository                  # Repositorios JPA
│   │   │       ├── service                     # Lógica de negocio
│   │   │       ├── web
│   │   │       │   ├── controller              # Controladores REST
│   │   │       │   └── dto                     # Objetos de transferencia de datos
│   │   │       └── MiniStockApplication.java   # Clase principal
│   │   └── resources
│   │       ├── application.properties          # Configuraciones de la aplicación
│   │       └── db
│   │           └── migration                   # Scripts de migración Flyway
│   └── test                                    # Pruebas unitarias e integradas
│
└── pom.xml                                    # Configuración de Maven
```

Acontinuación se visualiza un diagrama de entidad-relación (ER) que representa el modelo
de datos y las relaciones entre las entidades principales del sistema.
Este diagrama facilita la comprensión del modelo de datos y cómo interactúan los diferentes
componentes de la aplicación.  
![Diagrama ER](diagrama-er.jpg)

---

## 📦 Modulo de Productos y Categorías

--

## 🛒 Modulo de Clientes y Proveedores


--

## 🧮 Modulo de Movimientos de Inventario


--

## Autores
- 🚀 **Programador:** [Diego Chevez](https://github.com/D-Chevez)
- 🚀 **Programador:** [Nicole Sanchez]()
- 🚀 **Programador:** [Roberto Mendez]()