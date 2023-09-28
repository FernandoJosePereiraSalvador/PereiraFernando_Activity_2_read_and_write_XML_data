# PereiraFernando_Activity_2_read_and_write_XML_data
Este proyecto es una aplicación Java que permite realizar diversas operaciones en archivos XML. Permite abrir, leer y escribir archivos XML que contienen información sobre módulos de un curso, y también buscar y mostrar información detallada sobre un módulo específico.
# Funcionalidades
El proyecto consta de las siguientes funcionalidades principales:

1. __Read (Leer):__ Permite abrir y leer un archivo XML existente que contiene información sobre módulos de un curso. Los detalles de los módulos se muestran en la consola.
2. __Write (Escribir):__ Permite crear un nuevo archivo XML y escribir información sobre un nuevo módulo en él. El nuevo módulo se agrega al archivo XML y se guarda.
3. __Search (Buscar) Module:__ Permite buscar un módulo específico en el archivo XML según su nombre. Si se encuentra, se muestra información detallada sobre ese módulo.

# Documentacion
El proyecto ha sido documentado en html y latex. Para acceder a la documentación en html solo hace falta entrar en la carpeta docs, luego en html y ejecutar el archivo index.html.

# Uso
Para ejecutar la aplicación, sigue estos pasos:

1. Compila el código Java si aún no lo has hecho. Utiliza las herramientas de compilación que prefieras.

2. Ejecuta la aplicación Java proporcionando argumentos en línea de comandos. Los argumentos válidos son:
   - read: Para leer un archivo XML existente.
   - write: Para escribir un nuevo módulo en un archivo XML.
   - search: Para buscar un módulo por nombre en un archivo XML.

3. Dependiendo del argumento proporcionado, se requerirán argumentos adicionales:
   - Si usas read, debes proporcionar el nombre del archivo XML existente que deseas leer.
   - Si usas write, debes proporcionar el nombre del archivo XML que se creará para escribir el nuevo módulo.
   - Si usas search, debes proporcionar el nombre del archivo XML que se creará para escribir el nuevo módulo y se te pedirá que ingreses el nombre del módulo que deseas buscar.

4. La aplicación ejecutará la operación correspondiente y mostrará los resultados en la consola.
