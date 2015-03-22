# OneBoxGraph

CONSTRUCCION

Para la gestion y construccion del proyecto se ha utilizado la herramienta Maven, para construirlo se puede invocar al goal "package" añadiendo el goal "clean" por delante en caso de ser necesario:

mvn clean package

El goal package ejecutara los test incluidos en el ejercicio.

Para ejecutarlo se puede realizar de varias formas:

Ejecutarlo desde la linea de comandos como por ejemplo:

java -jar target\onebox-graph-0.0.1-SNAPSHOT.jar mapa.txt Distance AB

O bien, desde eclipse, Run --> Run Configurations --> Java Application y añadiendo los parametros de entrada

PARAMETROS DE ENTRADA

La aplicacion acepta tres parametros de entrada:

1º Ruta del fichero del grafo 2º Accion a ejecutar 3º Ruta o puntos entre ciudades

Tenemos las siguientes acciones posibles:

1º Distance --> Calculará la distancia de una ruta, la ruta tendrá un formato como el siguiente ABC ó AEBC ó ADE 2º All_RoutesWithShortest --> Calculará todas las rutas posibles entre dos ciudades y retornará la ruta mas corta, para especificar dos ciudades, el formato sería, las iniciales de la ciudad origen y destino, por ejemplo: AB, AD, CE

Ejemplo Distance : java -jar target\onebox-graph-0.0.1-SNAPSHOT.jar mapa.txt Distance ADC

Ejemplo All_RoutesWithShortest: java -jar target\onebox-graph-0.0.1-SNAPSHOT.jar mapa.txt All_RoutesWithShortest AB
