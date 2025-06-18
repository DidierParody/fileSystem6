
---

````markdown
# 🧱 AbstractObjectManager (Clase Abstracta)

`AbstractObjectManager` es una clase base abstracta que proporciona la configuración esencial y la estructura para manejar objetos dentro de Google Cloud Storage (GCS). Su diseño permite que las clases hijas se enfoquen únicamente en la lógica de operación sobre archivos (subida, descarga, eliminación, etc.), reutilizando la inicialización y configuración común.

---

## ⚙️ ¿Qué responsabilidades tiene?

- Carga las credenciales desde el archivo `credentials.json`.
- Inicializa el cliente de GCS (`Storage`).
- Define propiedades esenciales como:
  - `projectId`: ID del proyecto GCP.
  - `bucketName`: nombre del bucket.
  - `objectName`: nombre del objeto (archivo) a gestionar.
  - `filePath`: ruta local del archivo.
- Proporciona Getters y Setters para esas propiedades.
- Define un método abstracto `management()` que será implementado por cada clase hija.

---

## 🧩 Estructura del constructor

```java
public AbstractObjectManager() {
    try {
        initializeStorage();
    } catch (Exception e) {
        throw new RuntimeException("Error al inicializar las credenciales: " + e.getMessage());
    }
}
````

Este constructor garantiza que cada clase hija inicialice correctamente el cliente GCS al instanciarse.

---

## 🔐 Método `initializeStorage()`

```java
public void initializeStorage() throws Exception {
    InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("credentials.json");

    if (credentialsStream == null) {
        throw new RuntimeException("No se encontró el archivo credentials.json en resources.");
    }

    this.storage = StorageOptions.newBuilder()
            .setProjectId(projectId)
            .setCredentials(ServiceAccountCredentials.fromStream(credentialsStream))
            .build()
            .getService();
}
```

Este método se encarga de:

* Cargar las credenciales.
* Crear el cliente `Storage` con el `projectId`.

---

## 🧪 Uso típico

Una clase hija puede extender esta clase para subir un archivo a un bucket:

```java
public class UploadFileManager extends AbstractObjectManager {
    @Override
    public void management() throws Exception {
        // lógica de subida de archivo
    }
}
```

---

## 🚨 Consideraciones

* Asegúrate de colocar `credentials.json` dentro de `src/main/resources`.
* El archivo debe contener una clave válida de cuenta de servicio con permisos suficientes sobre GCS.
* Este diseño sigue el patrón de herencia y es ideal para proyectos modulares y reutilizables.

---

## 🔚 Conclusión

`AbstractObjectManager` encapsula toda la lógica repetitiva y esencial para interactuar con objetos en Google Cloud Storage, permitiendo que las subclases se centren solo en la operación deseada. Es una base sólida para manejar archivos de manera estructurada, segura y eficiente.

```

¿Quieres que te genere la documentación de las clases hijas como `UploadObject`, `DownloadObject`, etc. si las tienes?
```
