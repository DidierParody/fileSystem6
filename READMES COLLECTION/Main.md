# 🎮 Clase `Main`

La clase `Main` es el punto de entrada principal de la aplicación Java para gestionar Google Cloud Storage (GCS). Proporciona una **interfaz por consola** donde el usuario puede realizar operaciones como:

* Crear y eliminar buckets
* Subir y eliminar archivos (objetos)
* Listar objetos y buckets existentes

---

## ▶️ ¿Qué hace esta clase?

Esta clase presenta un menú interactivo al usuario y usa la clase `GeneralManagement` para realizar las operaciones seleccionadas. Es ideal para pruebas, demostraciones o herramientas básicas sin necesidad de interfaz gráfica.

---

## 🧱 Componentes Clave

### 📦 Dependencias

```java
import java.util.Scanner;
```

* `Scanner`: permite leer la entrada del usuario desde consola.

### 🧠 Instancia

```java
GeneralManagement gm = new GeneralManagement();
```

Se instancia `GeneralManagement`, que actúa como fachada para delegar las tareas.

---

## 📋 Menú Interactivo

### Opciones disponibles:

1. **Crear bucket**: pide el nombre de usuario y genera un bucket.
2. **Subir archivo**: solicita bucket, nombre de objeto y ruta local.
3. **Eliminar archivo**: solicita bucket y nombre del objeto a borrar.
4. **Listar objetos**: muestra los objetos dentro de un bucket.
5. **Eliminar bucket**: borra el bucket indicado.
6. **Listar buckets**: muestra todos los buckets del proyecto.
7. **Salir**: cierra el programa.

### Ejemplo de flujo:

```bash
--- Menú de Gestión de Buckets ---
1. Crear bucket
2. Subir archivo
...
Elige una opción: 1
Nombre de usuario para generar bucket: didier
Bucket creado: bucket-didier
```

---

## 🛡️ Manejo de Errores

* Validación de que la entrada sea numérica.
* Uso de `try/catch` para evitar caídas por excepciones inesperadas.

```java
try {
    opcion = scanner.nextInt();
    scanner.nextLine();
} catch (Exception e) {
    System.out.println("Entrada inválida. Debes ingresar un número.");
    scanner.nextLine();
    continue;
}
```

---

## 🚪 Salida del programa

```java
case 7:
    System.out.println("Saliendo del programa.");
    scanner.close();
    return;
```

---

## 🧩 Relación con otras clases

* Utiliza directamente la clase `GeneralManagement` para delegar operaciones.
* `GeneralManagement` a su vez delega en otras clases como `UploadManager`, `DeleteBucket`, `ListObjectsManager`, etc.

---

## 💡 Consejos para ampliación

* Añadir validaciones para rutas inválidas.
* Mostrar confirmaciones antes de eliminar.
* Exportar el resultado a un archivo de log.
* Implementar versión GUI en Swing o JavaFX si se desea mayor usabilidad.

---

La clase `Main` hace que la herramienta sea simple de usar y accesible, ideal para usuarios principiantes o desarrolladores que están automatizando pruebas con GCS desde Java.
