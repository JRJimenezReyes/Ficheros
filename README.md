# Ficheros en Java
## José Ramón Jiménez Reyes

Proyecto en el que se muestran ejemplos de utilización de ficheros en java. En él podemos encontrar los diferentes paquetes:

- **ficheros**: Manipulación básica de ficheros
    - **aleatorio**: En este paquete se muestra el uso de un fichero mediante acceso aleatorio en el que se almacenan registros de la clase `Amigo`. Dentro de él hay un paquete dao en el que se implementa una agenda simple en la que se insertan registros al final y permite buscar y leer registros. También se implementa una agenda ordenada en la que se insertan registros ordenados alfabéticamente y se pueden buscar y borrar. Las búsquedas están implementadas mediante una búsqueda binaria.
    - **file**: En este paquete se muestra el uso de la clase `File`. En él tenemos una clase que muestra las propiedades de un fichero y otra que nos muestra por consola el árbol de ficheros para un directorio dado. También hay tres soluciones diferentes para mostrar los ficheros entre dos fechas dadas.
    - **secuencial**: Ejemplos sobre el uso de ficheros mediante acceso secuencial.
        - **bytes**: En este paquete tenemos ejemplos sobre el uso de ficheros binarios: mediante flujos, mediante buffers, haciendo uso de datos primitivos y de objetos. Para este último, también se muestran los dos problemas que nos podemos encontrar a la hora trabajar con este tipo de ficheros y cómo solucionarlos.
        - **caracteres**: En este paquete tenemos ejemplos sobre el uso de ficheros de texto: mediante flujos y mediante buffers.
- **xml**: Manipulación básica de ficheros XML, con ejemplos para leer un fichero XML y mostrar su DOM, leer un fichero de objetos y escribirlo como fichero XML, etc.
- **csv**: Manipulación básica de ficheros CSV.