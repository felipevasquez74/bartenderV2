# BARTENDER

Programa hecho en Java con el framework de Spring, que determina si el número de un arreglo es divisible uniformemente por un numero primo.
El usuario debera ingresar la cantidad de iteraciones que desea y un numero entre 1-5 para seleccionar un arreglo que se encuentra en base de datos.

## Requisitos previos

- Docker instalado en tu máquina. Puedes descargarlo desde [la página oficial de Docker](https://www.docker.com/products/docker-desktop).

## Instrucciones para correr la aplicación

1. Clona este repositorio en tu máquina local:

git clone https://github.com/felipevasquez74/bartenderV2.git

2. Navega al directorio del proyecto:

cd BarTender

3. Construye la imagen Docker:

docker build -t bartender:1.0 .

4. Ejecuta el contenedor Docker:

docker run -d -p 8080:8080 bartender:1.0

5. Accede a la aplicación en tu navegador web:

Abre tu navegador y ve a la siguiente URL:

http://localhost:8080/bartender/iterations=6/stackId=2

Esto te llevará a el API de la aplicación BarTender.

![Screenshot 2024-03-23 172814](https://github.com/felipevasquez74/bartenderV2/assets/65198914/62ac250f-de51-4551-aab9-78d3b6ac8a24)


6. Para detener la aplicación, puedes usar el siguiente comando:

docker stop $(docker ps -q --filter ancestor=bartender:1.0)


### End points
La aplicacion esta configurada para desplegar en el puerto 8080.

**GET** http://localhost:8080/bartender/iterations=5/stackId=2

iterations = # de iteraciones que se quieren realizar

stackId = pila que se quiere seleccionar de BD

### Base de datos
La aplicacion consume de una base de datos mysql llamada **bartender**. En el esquema **arrays** estan los arreglos que seran seleccionados para validar segun el valor que se envie en **stackId**.

### Metodos
En la clase **BarServiceImpl** existen dos metodos.

**getPrimeNumbers** : Este metodo se encarga de generar los numeros primos contra los cuales se validara el array proveniente de la BD, generara los numeros a partir del numero de iteraciones solicitado, multiplicado por **6** ya que es la cantidad de numeros que existen en los arrays de BD.

**getDivisibleNumber**: Es un metodo que se encarga de validar y obtener una lista de numeros (**b**) uniformemente divisibles a partir del stack seleccionado de la BD y los numeros primos generados.

### Respuestas
En caso de ser un proceso exitoso, el programa devovlera una respuesta con un **status 200** y el arreglo de los numeros uniformemente divisibles obtenidos.
En caso de existir algun fallo, el programa devovlera una respuesta con **status 500** y la razon del fallo de la ejecucion.

ejemplo respuesta:

```
{
    "status": 200,
    "reason": "Ok",
    "arrayB": [
        4,
        2,
        3,
        9,
        5,
        7
    ]
}
```

