# Requerimientos de la capa de servicios (LOGICA DE NEGOCIO)
La idea principal de la capa de servicios es la de abstraer la logica de negocio del
acceso a datos, para que de esta manera el controlador no tenga que lidiar con la
logica de negocio y pueda enfocarse en recibir las peticiones y devolver las respuestas.

En esta capa pueden crear sus servicios, dentro de una carpeta principal,
la cual podrian llamarla ``<NombreEntidad>``, para una vez creada la carpeta los
archivos puedan ser llamados ``<NombreEntidad>Service.java``.

## Requerimientos de la capa
- Crear los servicios necesarios para manejar la logica de negocio.
- Implementar los metodos necesarios para manejar las operaciones CRUD.
- Validar los datos recibidos antes de procesarlos.
- Manejar las excepciones que puedan ocurrir durante el procesamiento de las solicitudes.
- Utilizar patrones de diseño adecuados para la implementacion de los servicios.
- Asegurar que los servicios sean reutilizables y mantenibles.
- Documentar los servicios y sus metodos para facilitar su uso y mantenimiento.