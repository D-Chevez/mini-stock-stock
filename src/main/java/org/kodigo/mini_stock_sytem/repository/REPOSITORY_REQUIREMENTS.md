# Requerimientos de la capa de repositorios (ACCESO A DATOS)
La idea principal de la capa de repositorios es la de abstraer el acceso a datos
de la logica de negocio, para que de esta manera el servicio no tenga que lidiar
con el acceso a datos y pueda enfocarse en la logica de negocio. De manera que esta
capa es la encargada de interactuar con la base de datos, ya sea para realizar
consultas, inserciones, actualizaciones o eliminaciones de datos.

En esta capa pueden crear sus repositories, dentro de una carpeta principal,
la cual podrian llamarla ``<NombreEntidad>``, para una vez creada la carpeta los
archivos puedan ser llamados ``<NombreEntidad>Service.java``.

## Requerimientos de la capa
- Crear los repositorios necesarios para manejar el acceso a datos.
- Implementar los metodos necesarios para manejar las operaciones CRUD.
- Utilizar un ORM (Object-Relational Mapping) para facilitar el acceso a datos.
- Manejar las excepciones que puedan ocurrir durante el acceso a datos.