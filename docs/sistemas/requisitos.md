# Tipo de sistema elegido



El sistema elegido para este proyecto es un PC comercial que se usara como entorno de desarrollo local y documentación.



Esta elección la encuentro adecuada para el  desarrollo, prueba y documentación de una aplicación de consola en Java conectada mediante JDBC a una base de datos MySQL, sin necesidad de desplegarla en un servidor real o en una infraestructura distribuida.



Descarto , como opción principal, el uso de un servidor dedicado porque el proyecto no requiere acceso simultáneo de múltiples usuarios reales, alta disponibilidad ni exposición pública a Internet, y tampoco se necesita una máquina virtual obligatoria porque añadiría complejidad técnica innecesaria en una fase académica tan temprana donde lo importante es demostrar que el sistema funciona, está bien documentado y es reproducible en un equipo personal además de aprender las bases de lo que en un futuro me servira para desplegar.



## Justificación del tipo de sistema

La elección de un entorno local se justifica por varios motivos:



Simplicidad de implantación: instalar y configurar Java, MySQL y DBeaver en un equipo personal es mucho más directo que preparar un servidor o una VM completa.



Facilidad de pruebas: todas las comprobaciones del proyecto pueden hacerse en local, incluyendo ejecución de la aplicación, conexión JDBC e implementación del CRUD.



Mejor trazabilidad para la entrega: resulta más sencillo documentar con capturas, logs y evidencias un entorno que controlas completamente desde tu propio equipo.



Adecuación al nivel del proyecto: el enunciado pide un entorno coherente y realista, no un despliegue complejo y escalable.



Por tanto, el escenario que encuentro más coherente es un equipo personal de desarrollo, preparado para programar, probar y mantener el proyecto de forma local.



## Requisitos de hardware

```

### Requisitos mínimos

Los requisitos mínimos propuestos para ejecutar correctamente el proyecto son los siguientes:



- CPU: procesador de 2 núcleos x64.



- RAM: 8 GB.



- Almacenamiento: 10 GB libres.



- Conectividad: acceso a Internet para descargas iniciales, dependencias y control de versiones.
```


#### Justificación de los mínimos

Estos requisitos mínimos son razonables porque el proyecto no ejecuta cargas pesadas de procesamiento ni maneja grandes volúmenes de datos, pero sí necesita mantener abiertas varias herramientas al mismo tiempo: editor o IDE, terminal, DBeaver, MySQL y navegador.



Una configuración de 8 GB de RAM permite trabajar de forma aceptable en tareas de desarrollo académico, mientras que un procesador de 2 núcleos y 10 GB libres de almacenamiento son suficientes para instalar Java 25, MySQL, DBeaver y guardar tanto el código como los scripts SQL y la documentación del proyecto.



```
### Requisitos recomendados

Los requisitos recomendados para trabajar con mayor fluidez son:



-CPU: procesador de 4 núcleos o superior.



-RAM: 16 GB.



-Almacenamiento: unidad SSD con al menos 20 GB libres.

```

#### Justificación de los recomendados

La recomendación de 16 GB de RAM y SSD no responde a una necesidad extrema del proyecto, sino a criterios de comodidad, estabilidad y rapidez de trabajo.



Un SSD mejora notablemente el arranque del sistema, la apertura de DBeaver, la carga del entorno Java y el acceso a los datos de MySQL, mientras que 16 GB de RAM permiten compilar, ejecutar consultas y mantener varias aplicaciones abiertas sin ralentizaciones perceptibles.


Esto es especialmente útil en una fase de desarrollo donde se alterna constantemente entre:escritura de código,pruebas JDBC,consulta de tablas,edición de documentación y uso de Git/GitHub.




## Sistema operativo recomendado

### Sistema principal recomendado

El sistema operativo recomendado para este proyecto es Windows 11 x64.



### Justificación de la elección

Windows 11 se propone como sistema principal porque ofrece compatibilidad directa con todas las herramientas del proyecto además de ser el SO donde todo el proyecto ha sido desarrollado:



MySQL tiene soporte para Windows 11 x86\_64.



Java 25 está certificado para Windows 11 x64.



DBeaver soporta Windows 10 o versiones posteriores.



A nivel práctico, Windows 11 también resulta una opción muy adecuada para un entorno académico y personal porque facilita la instalación de software, el uso de herramientas gráficas y la toma de evidencias del funcionamiento del sistema, algo importante para la documentación del módulo de Sistemas Informáticos.



### SO alternativo

Como alternativa secundaria, también sería totalmente válido usar Ubuntu 24.04 LTS x64, ya que MySQL lo soporta oficialmente y Java 25 también está certificado para Ubuntu 24.04 LTS.




## Conclusión técnica de la elección

En conjunto, la solución más coherente para este proyecto es un PC de usuario con Windows 11 x64, equipado con un procesador de al menos 2 núcleos, 8 GB de RAM y 10 GB libres como base mínima, aunque se recomienda una configuración de 4 núcleos, 16 GB de RAM y SSD para trabajar con mayor comodidad.



Esta elección está justificada porque equilibra:



- Compatibilidad técnica con Java 25, MySQL y DBeaver



- Facilidad de instalación y mantenimiento



- Adecuación realista al tipo de proyecto que se pide en el intermodular y al nivel técnico actual.

