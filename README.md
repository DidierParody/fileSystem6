
# 🕹️ Cloud Storage Adventure - Java Edition 🚀

> _¡Prepárate para gestionar tus Buckets como un héroe de 8 bits!_

Bienvenido al **Cloud Storage Adventure**, una aplicación Java para crear, listar, subir, eliminar y administrar objetos en buckets de Google Cloud Storage. ¡Una herramienta diseñada para jugar como los grandes en la nube!

---

## 🎮 Flujo del Juego (a.k.a. Flujo del Programa)

El programa se ejecuta desde consola y presenta un **menú interactivo** donde el usuario puede:
1. Crear un bucket con un nombre generado por usuario.
2. Subir archivos desde su PC a un bucket en la nube.
3. Eliminar objetos de un bucket.
4. Listar todos los objetos dentro de un bucket.
5. Eliminar un bucket completamente.
6. Listar todos los buckets existentes en el proyecto.
7. Salir del juego.

Cada opción activa un "manejador" específico que ejecuta la acción sobre Google Cloud Platform (GCP) a través de su API oficial.

---

## ☁️ Enfoque en Cloud Storage

Este proyecto está 100% basado en **Google Cloud Storage (GCS)**, un servicio altamente disponible, duradero y seguro para almacenar objetos. El sistema utiliza las bibliotecas oficiales de GCP para autenticarse y realizar todas las operaciones, usando un archivo de credenciales `credentials.json` ubicado en `resources`.

---

## 🧩 Componentes del Proyecto

- **`AbstractBucketManager` y `AbstractObjectManager`**  
  Clases base que inicializan el cliente de GCS usando autenticación mediante `ServiceAccountCredentials`.

- **`CreateBucket`**  
  Crea un bucket con clase de almacenamiento `COLDLINE` en la región `ASIA`.

- **`UploadManager`**  
  Sube un archivo desde el sistema local al bucket especificado. Aplica precondiciones para evitar conflictos de versiones.

- **`DeleteManager`**  
  Elimina objetos específicos de un bucket, incluso usando versiones (generations).

- **`ListObjectsManager` y `ListBucketsManager`**  
  Enumeran objetos y buckets existentes en el proyecto.

- **`DeleteBucket`**  
  Elimina buckets si están vacíos.

- **`Main` y `GeneralManagement`**  
  Punto de entrada y orquestador del sistema. Gestionan entradas del usuario y ejecutan la lógica correspondiente.

---

## 🔍 Claves Técnicas del Código

- ✅ **Autenticación con GCS**
  ```java
  InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("credentials.json");
  this.storage = StorageOptions.newBuilder()
      .setProjectId(projectId)
      .setCredentials(ServiceAccountCredentials.fromStream(credentialsStream))
      .build()
      .getService();
  ```

- 📦 **Crear un bucket**
  ```java
  Bucket bucket = storage.create(
      BucketInfo.newBuilder(bucketName)
          .setStorageClass(StorageClass.COLDLINE)
          .setLocation("ASIA")
          .build()
  );
  ```

- 📤 **Subir un archivo**
  ```java
  BlobId blobId = BlobId.of(bucketName, objectName);
  BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
  storage.createFrom(blobInfo, Paths.get(filePath), Storage.BlobWriteOption.doesNotExist());
  ```

- 🗑️ **Eliminar un archivo**
  ```java
  Blob blob = storage.get(bucketName, objectName);
  storage.delete(blob.getBlobId());
  ```

- 📜 **Listar objetos**
  ```java
  Page<Blob> blobs = storage.list(bucketName);
  for (Blob blob : blobs.iterateAll()) {
      System.out.println(blob.getName());
  }
  ```

- ❌ **Eliminar bucket**
  ```java
  Bucket bucket = storage.get(bucketName);
  bucket.delete();
  ```

> Todo el código fue desarrollado siguiendo las recomendaciones de la [documentación oficial de Google Cloud Storage](https://cloud.google.com/storage/docs/).

---

## 🧑‍💻 Equipo Desarrollador

**Proyecto realizado por:**
- Didier Torres
- Jeison Méndez
- Sergio Villamizar

---

## 🕹️ Bonus

> ¡Recuerda! Si el archivo no sube, asegúrate de que el bucket exista y el archivo esté en la ruta correcta.  
>  
> ¡Y si te equivocas... siempre puedes eliminarlo como un buen ninja digital! 🥷

---

**Powered by Java + Google Cloud + Pasión Retro.**
