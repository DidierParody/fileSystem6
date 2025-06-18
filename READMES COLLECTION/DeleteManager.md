# 🗑️ DeleteManager

`DeleteManager` es una clase concreta que extiende de `AbstractObjectManager` y se encarga de eliminar un objeto (archivo) específico dentro de un bucket de Google Cloud Storage (GCS).

---

## 📦 ¿Qué hace esta clase?

* Verifica si el objeto con nombre `objectName` existe en el bucket `bucketName`.
* Si existe, lo elimina de manera permanente usando su `BlobId` (incluyendo su generación).
* Si no existe, informa al usuario que el objeto no fue encontrado.

---

## 🧠 Lógica principal del método `management()`

```java
Blob blob = storage.get(bucketName, objectName);
```

* Intenta obtener el objeto (archivo) del bucket.

```java
if (blob == null) {
    System.out.println("El objeto " + objectName + " no se encontró en el bucket " + bucketName);
    return;
}
```

* Si el objeto no existe, informa por consola y termina la ejecución del método.

```java
BlobId idWithGeneration = blob.getBlobId();
storage.delete(idWithGeneration);
```

* Si el objeto existe, se obtiene su `BlobId`, que contiene la información necesaria (nombre, bucket y generación).
* Luego se elimina usando el método `storage.delete()`.

```java
System.out.println("✅ Objeto " + objectName + " eliminado permanentemente de " + bucketName);
```

* Muestra mensaje de confirmación tras la eliminación.

---

## 📌 Consideraciones

* `bucketName` y `objectName` deben ser definidos antes de llamar al método `management()`.
* El objeto debe existir para poder ser eliminado.
* Elimina completamente el objeto sin posibilidad de recuperación desde el bucket.

---

## 🧪 Ejemplo de uso

```java
DeleteManager deleter = new DeleteManager();
deleter.setBucketName("mi-bucket");
deleter.setObjectName("carpeta/archivo.txt");
deleter.management();
```

---

## ✅ Resultado esperado

```
✅ Objeto carpeta/archivo.txt eliminado permanentemente de mi-bucket
```

---

## 🧱 Relación con `AbstractObjectManager`

* Hereda atributos necesarios como `projectId`, `bucketName`, `objectName` y `storage`.
* Utiliza la conexión ya autenticada con GCS provista por la clase base.
* Aplica el patrón de plantilla para estandarizar operaciones sobre objetos del bucket.

---

`DeleteManager` permite mantener limpio y actualizado tu almacenamiento en GCS eliminando objetos innecesarios o antiguos de forma segura.
