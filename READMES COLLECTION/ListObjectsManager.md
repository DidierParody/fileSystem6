# 📄 ListObjectsManager

`ListObjectsManager` es una clase concreta que extiende de `AbstractObjectManager` y permite listar todos los objetos almacenados dentro de un bucket de Google Cloud Storage (GCS).

---

## 📦 ¿Qué hace esta clase?

* Verifica que se haya especificado un nombre de bucket válido.
* Obtiene la lista completa de objetos (blobs) contenidos en el bucket.
* Imprime por consola los nombres de los objetos encontrados.
* Informa si el bucket está vacío.

---

## 🧠 Lógica principal del método `management()`

```java
if (bucketName == null || bucketName.isEmpty()) {
    System.out.println("El nombre del bucket no fue especificado.");
    return;
}
```

* Verifica si el nombre del bucket ha sido definido correctamente antes de proceder.

```java
Page<Blob> blobs = storage.list(bucketName);
```

* Recupera una página iterable de objetos (`Blob`) del bucket usando el cliente `Storage`.

```java
System.out.println("Objetos en el bucket \"" + bucketName + "\":");
boolean empty = true;

for (Blob blob : blobs.iterateAll()) {
    System.out.println("🔹 " + blob.getName());
    empty = false;
}

if (empty) {
    System.out.println("El bucket está vacío.");
}
```

* Recorre todos los objetos encontrados e imprime sus nombres.
* Si no encuentra ninguno, muestra un mensaje indicando que el bucket está vacío.

---

## 📌 Consideraciones

* Se debe establecer correctamente el `bucketName` antes de ejecutar `management()`.
* No realiza paginación manual; `iterateAll()` recorre todos los blobs automáticamente.
* No incluye información adicional como tamaño, tipo MIME o fecha de creación de los objetos.

---

## 🧪 Ejemplo de uso

```java
ListObjectsManager lister = new ListObjectsManager();
lister.setBucketName("mi-bucket");
lister.management();
```

---

## ✅ Resultado esperado

```
Objetos en el bucket "mi-bucket":
🔹 archivo1.txt
🔹 imagen.png
🔹 carpeta/documento.pdf
```

O si el bucket no contiene objetos:

```
El bucket está vacío.
```

---

## 🧱 Relación con `AbstractObjectManager`

* Reutiliza la configuración e inicialización de credenciales y cliente `Storage`.
* Aprovecha el atributo `bucketName` proporcionado por la clase base.
* Mantiene el patrón de herencia y método abstracto `management()` para estandarizar operaciones.

---

`ListObjectsManager` es ideal para tareas de monitoreo y auditoría del contenido almacenado en los buckets de GCS.
