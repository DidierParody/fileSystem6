# 🗂️ AbstractBucketManager (Java + Google Cloud Storage)

Esta clase abstracta forma parte de un sistema de gestión para **Google Cloud Storage (GCS)** usando Java. Proporciona la **estructura base** que otras clases pueden extender para realizar operaciones concretas (como subir, listar o eliminar archivos/buckets).

## 🚀 ¿Qué hace esta clase?

`AbstractBucketManager` se encarga de:
- Cargar las **credenciales** necesarias para conectarse a GCS.
- Inicializar un cliente de Google Cloud Storage (`Storage`).
- Proporcionar métodos comunes como setters y getters.
- Definir un método abstracto `management()` que debe ser implementado por cualquier clase hija.

---

## 🔧 Componentes clave

### 🧱 Atributos protegidos:
- `projectId`: ID del proyecto en Google Cloud (`filesystem-463019` por defecto).
- `bucketName`: Nombre del bucket que se va a manejar.
- `storage`: Cliente de GCS que permite hacer operaciones con los buckets.

---

### 🏗️ Constructor:
```java
public AbstractBucketManager()
```
- Se ejecuta automáticamente al crear un objeto.
- Llama al método `initializeStorage()` para cargar las credenciales e inicializar el cliente de GCS.
- Si ocurre un error, lanza una `RuntimeException`.

---

### 🔐 Autenticación con credenciales:
```java
InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("credentials.json");
```
- Lee el archivo `credentials.json` ubicado en `src/main/resources`.
- Usa este archivo para crear una conexión segura con tu cuenta de GCP.

> ⚠️ Si el archivo no se encuentra, lanza una excepción clara para ayudarte a detectar el problema.

---

### ⚙️ Método de inicialización:
```java
public void initializeStorage() throws Exception
```
- Configura el cliente de GCS (`Storage`) con las credenciales del proyecto.
- Este cliente es lo que permite interactuar con los buckets y objetos en la nube.

---

### 📤 Getters y Setters:
Permiten modificar o acceder a las propiedades del proyecto y el bucket:

- `setProjectId()`, `getProjectId()`
- `setBucketName()`, `getBucketName()`
- `getStorage()`

---

### 🔄 Método abstracto:
```java
public abstract void management() throws Exception;
```
- Obligatorio para cada clase hija.
- Aquí cada implementación define **qué tipo de gestión realizará**: crear buckets, subir archivos, eliminarlos, etc.

---

## 🧬 ¿Cómo se usa?

Debes extender esta clase en una subclase como:

```java
public class UploadManager extends AbstractBucketManager {
    @Override
    public void management() throws Exception {
        // Lógica para subir archivos al bucket
    }
}
```

Cada subclase tendrá acceso a `storage`, `bucketName` y `projectId`, y podrá personalizar el comportamiento en el método `management()`.

---

## ✅ Requisitos

- Archivo `credentials.json` en `src/main/resources`
- Dependencias de Google Cloud Storage en tu proyecto (por ejemplo, usando Gradle o añadiendo los `.jar` manualmente)

---

## 📁 Estructura esperada del proyecto

```
project-root/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/AbstractBucketManager.java
│   │   └── resources/
│   │       └── credentials.json
│
└── build.gradle / libs/
```

---

## 📌 Nota final

Esta clase es ideal para mantener tu código organizado, reutilizable y escalable. Al separar la lógica común de conexión y configuración, puedes enfocarte en extender funcionalidades específicas sin repetir código.

---