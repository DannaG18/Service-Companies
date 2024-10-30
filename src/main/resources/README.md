# Service Companies API

Este proyecto es un backend desarrollado en **Spring Boot** que implementa **Spring Security** con autenticación mediante **JWT (JSON Web Tokens)**. La API está diseñada para gestionar los servicios y compañías con operaciones CRUD y autenticación de usuarios.

## Tabla de Contenidos

- [Requisitos](#requisitos)
- [Configuración del Proyecto](#configuración-del-proyecto)
- [Instalación y Ejecución](#instalación-y-ejecución)
- [Uso](#uso)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Front-end](#front-end)

## Requisitos

Antes de comenzar, asegúrate de tener instaladas las siguientes herramientas:

- **Java 17** o superior
- **Maven 3.6+**
- **MySQL** (u otro sistema de base de datos compatible)

## Configuración del Proyecto

El proyecto utiliza un archivo de propiedades (`application.properties` o `application.yml`) que debe ser configurado para la conexión con la base de datos y la configuración de JWT. 

1. **Configuración de Base de Datos**: Agrega tus credenciales de base de datos en el archivo de propiedades ubicado en `src/main/resources/application.properties`.

   ```properties
   # Configuración de Base de Datos
   spring.datasource.url=jdbc:mysql://localhost:3306/nombre_base_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   
   # Configuración de Hibernate
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

2. **Configuración de JWT**: Asegúrate de definir una clave secreta para JWT. Esto se configura también en `application.properties`.

   ```properties
   # Configuración de JWT
   jwt.secret=tu_jwt_secreto
   jwt.expiration=86400000  # Tiempo de expiración en milisegundos
   ```

3. **Datos Iniciales**: En el directorio `src/main/resources`, encontrarás un archivo SQL (`data.sql` o `import.sql`) con las sentencias `INSERT` para poblar las tablas esenciales (usuarios, roles, etc.). Asegúrate de cargar estos datos antes de ejecutar el proyecto.

## Instalación y Ejecución

Sigue estos pasos para ejecutar el proyecto en tu entorno local:

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git
   cd tu-repositorio
   ```

2. Compila el proyecto con **Maven**:

   ```bash
   mvn clean install
   ```

3. Ejecuta la aplicación:

   ```bash
   mvn spring-boot:run
   ```

La aplicación estará disponible en `http://localhost:8080`.

## Uso

Una vez que la API esté corriendo, puedes realizar solicitudes HTTP para interactuar con ella. Las rutas de autenticación y autorización incluyen:

- **Crear usuario**:
  - **POST**`http://localhost:8080/customers`: Crear un usuario.

- **Autenticación**:
  - **POST** `/auth/autenticate`: Inicia sesión y recibe un token JWT.
  - **POST** `/auth/validate-token`: Ver si el token sigue vigente.
  - **POST** `/auth/logout/`: Cerrar la sesión.
  - **GET**`/auth/profile`: Ver el perfil.

- **Servicios CRUD** (Requiere JWT):
  - **GET** `/api/services`: Obtiene la lista de servicios.
  - **POST** `/api/services`: Crea un nuevo servicio.
  - **PUT** `/api/services/{id}`: Actualiza un servicio existente.
  - **DELETE** `/api/services/{id}`: Elimina un servicio existente.

Recuerda incluir el token JWT en el encabezado de autorización para acceder a las rutas protegidas:

```http
Authorization: Bearer <tu-token-jwt>
```

## Estructura del Proyecto

```plaintext
├── src/main/java
│   ├── com.example.servicecompanies
│   │   ├── application         # Servicios de negocio
│   │   ├── config	            # Configuración de Spring Security y JWT
│   │   ├── domain          	# Entidades y dtos JPA
│   │   ├── infrastructure      # Repositorios y controllers JPA       
│   │   └── ServiceCompaniesApplication.java 
└── src/main/resources
	├── META-INF
	├── sql						# Inserts base de datos
	├── application-dev.properties
	├── application-pdn.properties
    ├── application.properties  # Configuración de base de datos y JWT
    └── README.md               
```

## Front-end

El front de este proyecto lo puedes encontrar en este repositorio https://github.com/DannaG18/PrestService