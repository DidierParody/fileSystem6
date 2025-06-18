

````markdown
# 🚀 UploadManager

`UploadManager` es una clase concreta que extiende de `AbstractObjectManager`, diseñada específicamente para subir archivos locales a un bucket de Google Cloud Storage (GCS). Utiliza el cliente `Storage` ya inicializado en la clase base y aplica una precondición para evitar sobreescrituras accidentales.

---

## 📦 ¿Qué hace esta clase?

- Sube un archivo desde el sistema de archivos local al bucket especificado.
- Aplica una verificación (`precondition`) para decidir si el archivo debe ser subido solo si no existe, o si debe respetar una versión existente (con control de concurrencia).

---

## 🧠 Lógica principal del método `management()`

```java
BlobId blobId = BlobId.of(bucketName, objectName);
BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
````

* Crea una representación del archivo (`BlobId`) con el nombre del bucket y el nombre del archivo en la nube.
* Construye los metadatos del archivo (`BlobInfo`) a partir de esa información.

```java
Storage.BlobWriteOption precondition;
if (storage.get(bucketName, objectName) == null) {
    precondition = Storage.BlobWriteOption.doesNotExist();
} else {
    precondition = Storage.BlobWriteOption.generationMatch(
        storage.get(bucketName, objectName).getGeneration());
}
```

* Si el archivo no existe, impide sobreescritura usando `doesNotExist()`.
* Si existe, permite sobreescritura solo si coincide con la generación actual (`generationMatch`), evitando colisiones.

```java
storage.createFrom(blobInfo, Paths.get(filePath), precondition);
```

* Sube el archivo usando la configuración anterior y la ruta local especificada.

---

## 📌 Consideraciones

* `bucketName`, `objectName` y `filePath` deben establecerse antes de ejecutar `management()`.
* `filePath` debe apuntar a un archivo existente y legible.
* Requiere que el bucket ya exista; no se encarga de crear el bucket.
* La precondición es útil para controlar versiones y evitar conflictos.

---

## 🧪 Ejemplo de uso

```java
UploadManager uploader = new UploadManager();
uploader.setBucketName("mi-bucket");
uploader.setObjectName("carpeta/miArchivo.txt");
uploader.setFilePath("C:/local/miArchivo.txt");
uploader.management();
```

---

## ✅ Resultado esperado

```
Archivo C:/local/miArchivo.txt subido a mi-bucket como carpeta/miArchivo.txt
```

---

## 🧱 Relación con `AbstractObjectManager`

Aprovecha:

* Cliente `Storage` inicializado
* Atributos reutilizables (`bucketName`, `filePath`, etc.)
* Patrón de herencia para separar responsabilidades entre infraestructura y lógica específica

---

`UploadManager` permite una gestión robusta de subida de archivos a GCS de forma segura y controlada. Ideal para pipelines de carga o sistemas de respaldo.


