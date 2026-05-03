# Guía de instalación paso a paso

## 1.1 Requisitos previos
-> [Requisitos mínimos](requisitos.md)

## 1.2 Instalación del entorno
Instalar Java JDK. Se parte de un entorno en el que ya se trabaja con JDK 25 para desarrollo en Java.

Instalar Git. Git permite controlar versiones del proyecto y sincronizarlo con GitHub.

Instalar DBeaver. Se utiliza como herramienta gráfica para conectarse y gestionar bases de datos, evitando parte de los problemas que pueden aparecer con otras pilas locales más pesadas.

Instalar MySQL. El entorno actual del proyecto usa MySQL como motor de base de datos.

Instalar VSCode e IntelliJ.Ambos son editores de código para trabajar HTMl/CSS y Java

Comprobar variables y funcionamiento. Tras la instalación, conviene verificar que Java, Git, MySQL y DBeaver arrancan correctamente y que las conexiones básicas funcionan.


## 1.3 Verificación mínima tras la instalación
Para considerar la instalación correcta, deberían poder hacerse estas comprobaciones:

Abrir una terminal y verificar que Java está disponible.

Abrir Git Bash y comprobar que Git funciona en el sistema.

Abrir DBeaver y confirmar que el controlador de MySQL está instalado.

Conectarse a la base de datos MySQL desde DBeaver y visualizar el esquema o las tablas.

```
git --version
java --version
javac --version
mysql --version
code --version
```

## 2 Plan de mantenimiento básico
Una puesta en marcha no se considera completa si no se acompaña de un mantenimiento mínimo que garantice continuidad, seguridad y estabilidad del entorno.

## 2.1 Qué actualizar
Los elementos más importantes a revisar y actualizar son:

Java JDK, para mantener compatibilidad y seguridad.

Git, para asegurar un flujo de trabajo estable con repositorios remotos.

DBeaver, especialmente cuando hay cambios en controladores o conexiones.

MySQL, para mantener estabilidad y compatibilidad con el proyecto.

Drivers de conexión, si aparecen errores de acceso a base de datos.

## 2.2 Frecuencia recomendada

| Elemento | Frecuencia orientativa | Qué revisar |
|---------|-------------------------|-------------|
| Java JDK | Cada 2-3 meses | Compatibilidad y versión instalada |
| Git | Cada 2-3 meses | Funcionamiento con remoto y terminal |
| DBeaver | Mensual o cuando falle una conexión | Drivers, conexiones y arranque |
| MySQL | Mensual | Servicio activo, acceso, estado de la base |
| Proyecto/documentación | Semanal | Organización de carpetas, copias y cambios recientes |

## 2.3 Revisiones mínimas
El mantenimiento básico debería incluir, como mínimo, estas tareas:

Comprobar que la base de datos arranca y acepta conexiones.

Verificar que el proyecto sigue compilando y conectando correctamente con la base de datos.

Revisar que el repositorio Git está sincronizado y no acumula cambios desordenados.

Mantener actualizada la documentación de instalación si cambia algún paso técnico.

Hacer copia de seguridad de scripts SQL, configuración importante y documentación.

## 3. Clonación del Proyecto
Independientemente del sistema operativo, el primer paso es descargar el código fuente:

```
bash
git clone https://github.com/pablotome8/FloristeriaLaBajo.git
cd FloristeriaLaBajo
```
