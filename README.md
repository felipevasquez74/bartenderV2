# Prueba de Bar Tender

Esta aplicación simula el proceso de un bar ténder en el bar más famoso de la ciudad, donde se manejan arreglos de vasos numerados y se aplican operaciones basadas en números primos.

## Descripción del Problema

Se dispone de un arreglo de vasos numerados representado por el arreglo `A`. Además, se tiene un arreglo con números primos representados por `P`. En cada iteración `Qi`, se retira cada vaso de la parte superior del arreglo `A`. Se determina si el número del vaso es divisible uniformemente por el `i-ésimo` número primo en `P`. Si lo es, se agrega a un arreglo `B`. En caso contrario, se añade a un arreglo `Ai`. Los valores de `B` se guardan en el arreglo `Respuesta`. En la siguiente iteración, se repite el proceso con los valores del arreglo `Ai`. Una vez completado el número necesario de iteraciones `Q`, se almacenan los valores restantes de `Ai` al final de `Respuesta`.

## Ejemplo de Uso

**Entrada de Ejemplo:**

INPUTA0=2,3,4,5,6,7
Q=3

**Iteración Q1:**

B=6,4,2
A1=7,5,3
Respuesta=6,4,2

**Iteración Q2:**

A1=7,5,3
B=3
A2=5,7
Respuesta=6,4,2,3

**Iteración Q3:**

A2=5,7
B=5
A3=7
Respuesta=6,4,2,3,5

**Resultado Final:**

Respuesta=6,4,2,3,5,,7

## API

La aplicación expone un API que recibe un número de iteraciones `iterations` y un número del 1 al 5 que representa el ID de la pila de datos a trabajar `stackId`. Al hacer una solicitud al API, se devuelve el array de respuesta.

**Ejemplo de solicitud:**

`GET`
```
http://localhost:8080/bartender/iterations=5/stackId=2
```

**Respuesta:**
```
{
    "status": 200,
    "reason": "Ok",
    "answer": [
        3,
        7,
        9,
        5,
        4,
        2
    ],
    "executionTime": "2ms"
}
```
![Screenshot 2024-03-23 172814](https://github.com/felipevasquez74/bartenderV2/assets/65198914/62ac250f-de51-4551-aab9-78d3b6ac8a24)


## Tecnologías Utilizadas

- Java
- Spring Boot
- Base de Datos H2 (In-memory database)
- Docker

## Requisitos previos

- Docker instalado en tu máquina. Puedes descargarlo desde [la página oficial de Docker](https://www.docker.com/products/docker-desktop).

## Ejecución de la Aplicación

1. Clona este repositorio en tu máquina local:
```
git clone https://github.com/felipevasquez74/bartenderV2.git
```
2. Navega al directorio del proyecto:
```
cd bartenderV2
```
3. Construye la imagen Docker:
```
docker build -t bartender:1.0 .
```
4. Ejecuta el contenedor Docker:
```
docker run -d -p 8080:8080 bartender:1.0
```
![Screenshot 2024-03-23 173342](https://github.com/felipevasquez74/bartenderV2/assets/65198914/213cfada-6549-4f92-9164-7be6679e995f)

5. Accede a la aplicación en tu navegador web:

Abre tu navegador y ve a la siguiente URL:

http://localhost:8080/bartender/iterations=6/stackId=2

Esto te llevará a el API de la aplicación BarTender.

## Contribuciones

¡Las contribuciones son bienvenidas! Si deseas contribuir a este proyecto, por favor, abre un issue o envía un pull request.







