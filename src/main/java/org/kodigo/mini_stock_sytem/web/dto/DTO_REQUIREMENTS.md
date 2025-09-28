# Requerimientos de la capa DTO (ACCESO A DATOS)
La idea principal de la capa DTO (Data Transfer Object) es facilitar
la transferencia de datos entre diferentes capas de una aplicación,
especialmente entre la capa de presentación y la capa de negocio o backend.
En esta capa la idea es definir los objetos que se utilizarán para transportar datos,
asegurando que solo la información necesaria se transfiera y que los datos estén en
un formato adecuado para su uso.  

En esta capa pueden crear sus DTOs, dentro de una carpeta principal, la cual podrian
llamarla ``<NombreEntidad>``, para una vez creada la carpeta los archivos puedan ser llamados
``<NombreEntidad>DTO.java``.