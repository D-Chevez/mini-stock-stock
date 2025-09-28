# Requerimientos de la capa de controladores (HTTP REQUESTS)
La idea principal de esta capa es manejar las solicitudes HTTP entrantes,
procesar los datos recibidos y devolver respuestas adecuadas al cliente.
Esta capa actúa como intermediaria entre la capa de presentación (frontend)
y la capa de negocio (backend), asegurando que las solicitudes se gestionen
de manera eficiente y segura.

En esta capa pueden crear sus controladores, dentro de una carpeta principal,
la cual podrian llamarla ``<NombreEntidad>``, para una vez creada la carpeta los
archivos puedan ser llamados ``<NombreEntidad>Controller.java``.