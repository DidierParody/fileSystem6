# 🪣 CreateBucket (Extiende AbstractBucketManager)

Esta clase concreta extiende `AbstractBucketManager` y se encarga de **crear un bucket en Google Cloud Storage (GCS)** de forma programática, especificando su clase de almacenamiento y ubicación.

## 🚀 ¿Qué hace esta clase?

- Implementa el método `management()` definido como abstracto en la clase padre.
- Crea un bucket usando el cliente `Storage` ya autenticado.
- Define el tipo de almacenamiento como `COLDLINE`, ideal para datos a los que se accede con poca frecuencia.
- Define la ubicación del bucket como `ASIA`.

---

## 🔧 Componentes clave

### 🔄 Método `management()`
```java
@Override
public void management() throws Exception {
    StorageClass storageClass = StorageClass.COLDLINE; //tipo de almacenamiento
    String location = "ASIA";

    Bucket bucket = storage.create(
            BucketInfo.newBuilder(bucketName)
                    .setStorageClass(storageClass)
                    .setLocation(location)
                    .build());

    System.out.println("Bucket creado: " + bucket.getName() + " en " + bucket.getLocation() +
            " con clase de almacenamiento " + bucket.getStorageClass());
}
```

### 📌 ¿Qué hace este código?
- Define `storageClass` como `COLDLINE`, una clase económica para almacenamiento a largo plazo.
- Especifica `location` como `"ASIA"` (puede ser `"US"`, `"EU"`, etc. según tus necesidades).
- Usa el objeto `storage` (inicializado en `AbstractBucketManager`) para crear el bucket.
- Imprime un mensaje indicando que el bucket fue creado exitosamente.

---

## 🧪 Ejemplo de uso

```java
CreateBucket createBucket = new CreateBucket();
createBucket.setBucketName("nombre-de-tu-bucket");
createBucket.management();
```

---

## ✅ Requisitos

- Tener configurado `credentials.json` y el proyecto en GCP.
- Haber extendido correctamente la clase `AbstractBucketManager`.
- Tener permisos suficientes en GCP para crear buckets.

---

## 📌 Nota final

Esta clase es ideal si deseas automatizar la creación de buckets GCS desde tu aplicación Java. Úsala como base para construir operaciones más complejas o integrarla en un sistema de gestión más amplio.

---