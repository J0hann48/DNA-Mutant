# MUTANT DNA - MERCADO LIBRE

Proyecto prueba en donde se valida una cadena de ADN para ver si es mutante o no

## Instrucciones para su prueba

El servicio se encuentra deployado en [http://ec2-3-141-38-96.us-east-2.compute.amazonaws.com:8080]http://ec2-3-141-38-96.us-east-2.compute.amazonaws.com:8080)

El servicio actualmente cuenta con los siguientes métodos:
 
 * Método POST para detectar si un ADN es mutante:<br><br>
  La URL del método es [http://ec2-3-141-38-96.us-east-2.compute.amazonaws.com:8080/api/mutant](http://ec2-3-141-38-96.us-east-2.compute.amazonaws.com:8080/api/mutant)<br><br>
Se puede detectar si un humano es mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el siguiente formato:<br><br>
POST → /mutant/<br />
{<br />
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]<br />
}<br><br>
En caso de verificar que el ADN enviado es mutante, el método devuelve como respuesta un HTTP 200-OK, en caso contrario un
403-Forbidden <br><br>
* Método GET para obtener las estadísticas de las verificaciones de ADN<br><br>
La URL del método es [http://ec2-3-141-38-96.us-east-2.compute.amazonaws.com:8080/api/stats](http://ec2-3-141-38-96.us-east-2.compute.amazonaws.com:8080/api/stats)<br><br>

Se puede probar utilizando [Postman](https://www.getpostman.com/).

## Environment
### Pre-requisitos 📋

* [Java11](https://www.oracle.com/co/java/technologies/javase-jdk11-downloads.html)
* IDE: [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [Maven](https://maven.apache.org/)
* [JUnit 5](https://junit.org/junit5/)


## Descarga del código fuente
   
   Este proyecto utiliza Apache Maven. Antes de empezar, asegurese de descargarlo e instalarlo. Luego, Maven descargará automáticamente las librerias requeridas por el proyecto
   
   #### Repositorio
   
   El código se encuentra alojado en github. Para descargarlo necesita un cliente git, que puede encontrarlo en https://git-scm.com/downloads
   
   * Cree una carpeta en donde se incluirá el código fuente<br>
   * Abra su consola y posicionese en la carpeta previamente creada<br>
   * Ejecute el comando<br>
   
    git clone https://github.com/J0hann48/DNA-Mutant.git
   
   Luego de que termine la descarga, usted tendrá clonado el branch master en la carpeta previamente creada.

### Instalación 🔧

## Despliegue 📦

_Se despliega en instancia [EC2](https://aws.amazon.com/es/ec2/) de AWS_

## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Spring Boot](https://spring.io/projects/spring-boot) - API-REST
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [ROME](https://rometools.github.io/rome/) - Usado para generar RSS



## Versionado 📌

Se uso [Github](https://github.com/) para el versionado. 

## Autor ✒️

* **Johann Sebastian Torres** - *Trabajo Inicial* - [J0hann48](https://github.com/J0hann48)

También puedes mirar la lista de todos los [contribuyentes](https://github.com/your/project/contributors) quíenes han participado en este proyecto. 