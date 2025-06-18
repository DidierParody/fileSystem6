# Intento nuevamente de guardar el archivo README para DeleteBucket
from pathlib import Path

delete_bucket_md = """
# 🗑️ DeleteBucket (Extiende AbstractBucketManager)

Esta clase concreta extiende `AbstractBucketManager` y se encarga de **eliminar un bucket** de Google Cloud Storage (GCS). Es útil cuando necesitas limpiar recursos o gestionar buckets de forma programada.

## 🚀 ¿Qué hace esta clase?

- Implementa el método `management()` definido como abstracto en la clase padre.
- Obtiene el bucket a través del cliente `storage`.
- Verifica si el bucket existe.
- Si existe, lo elimina e imprime un mensaje de confirmación.

---

## 🔧 Componentes clave

### 🔄 Método `management()`
```java
@Override
public void management() throws Exception {
    Bucket bucket = storage.get(bucketName);

    if (bucket == null) {
        System.out.println("El bucket " + bucketName + " no existe.");
        return;
    }

    bucket.delete();
    System.out.println("✅ Bucket eliminado: " + bucket.getName());
}
