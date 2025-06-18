# 🧠 GeneralManagement

`GeneralManagement` es una clase central que actúa como **fachada** para gestionar las operaciones principales relacionadas con Google Cloud Storage (GCS). Proporciona una interfaz unificada para:

* Crear y eliminar buckets
* Subir y eliminar objetos
* Listar objetos y buckets

---

## 📦 ¿Qué hace esta clase?

Sirve como punto de acceso general para ejecutar operaciones específicas, sin que el usuario tenga que interactuar directamente con las clases individuales como `CreateBucket`, `UploadManager`, etc.

---

## 🧱 Atributos principales

```java
private String bucketName;
private String objectName;
private String filePath;
private String nameUser;
private final String projectId = "filesystem-463019";
```

* `bucketName`: nombre del bucket sobre el cual se operará.
* `objectName`: nombre del objeto (archivo) que se subirá o eliminará.
* `filePath`: ruta local del archivo que se subirá.
* `nameUser`: nombre del usuario, usado para personalizar el nombre del bucket.
* `projectId`: ID del proyecto en Google Cloud (constante).

---

## 🔧 Métodos principales

### 🪣 `createBucket()`

```java
CreateBucket manager = new CreateBucket();
manager.setProjectId(projectId);
manager.setBucketName("bucket-" + nameUser);
manager.management();
```

* Crea un bucket con un nombre personalizado según el usuario.

### 🧨 `deleteBucket()`

* Elimina el bucket especificado en `bucketName`.

### 📤 `uploadObject()`

* Sube un archivo desde `filePath` al bucket indicado con nombre `objectName`.

### 🗑️ `deleteObject()`

* Elimina un objeto específico del bucket.

### 📃 `listObjects()`

* Lista todos los objetos dentro del bucket especificado.

### 📃 `listBuckets()`

* Muestra todos los buckets existentes en el proyecto.

---

## 🧪 Ejemplo de uso

```java
GeneralManagement gm = new GeneralManagement();
gm.setNameUser("didier");
gm.setBucketName("bucket-didier");
gm.setObjectName("archivo.txt");
gm.setFilePath("/ruta/local/archivo.txt");

gm.createBucket();
gm.uploadObject();
gm.listObjects();
gm.deleteObject();
gm.deleteBucket();
```

---

## 🎯 Beneficios de esta clase

* Agrupa y simplifica el uso de múltiples clases gestoras.
* Facilita la ejecución desde una interfaz gráfica o CLI.
* Mejora la legibilidad y organización del código.

---

## 🧩 Relación con otras clases

* Usa directamente instancias de:

  * `CreateBucket`
  * `DeleteBucket`
  * `UploadManager`
  * `DeleteManager`
  * `ListObjectsManager`
  * `ListBucketsManager`

Cada una de estas extiende de `AbstractBucketManager` o `AbstractObjectManager`, aplicando el patrón de plantilla para mantener una arquitectura limpia y extensible.

---

`GeneralManagement` permite orquestar todo el ciclo de vida de archivos y contenedores en GCS de manera centralizada y sencilla.
