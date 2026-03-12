Tetris UIB (Java)

Implementación del clásico Tetris desarrollada en Java como proyecto de la asignatura de Programación II del grado en Ingeniería Informática.

El proyecto consiste en una aplicación con interfaz gráfica que permite jugar a Tetris, gestionar partidas y almacenar el historial de resultados de los jugadores.

Demo

🎥 Video del proyecto:
https://youtu.be/fYLF9c5iz4M

Descripción del Proyecto

Esta aplicación implementa una versión interactiva del juego Tetris utilizando las librerías gráficas de Java.

El sistema permite:

Iniciar nuevas partidas

Introducir el nombre del jugador

Jugar en un tablero interactivo

Controlar el tiempo de partida

Registrar la puntuación del jugador

Consultar el historial de partidas

Configurar distintos parámetros del juego

El objetivo del proyecto fue aplicar conceptos de:

Programación Orientada a Objetos

Tipos Abstractos de Datos

Interfaces gráficas con Java

Gestión de eventos

Organización modular del software

Tecnologías Utilizadas

Java

Java Swing (GUI)

Programación Orientada a Objetos

Manejo de eventos

Lectura y escritura de archivos

Arquitectura del Proyecto

El sistema se divide en cuatro componentes principales:

1. Interfaz de Usuario

Gestiona todos los elementos visuales y la interacción con el jugador.

Clases principales:

MENU
Controla la ventana principal y la navegación por la aplicación.

PanelDisplay
Gestiona los distintos paneles visuales (pantalla inicial, partida, historial, información).

BarraTiempo
Representa el tiempo restante de la partida mediante una barra de progreso.

2. Lógica del Juego

Contiene la mecánica principal del Tetris.

Clases principales:

Partida
Controla el flujo del juego, la interacción del usuario y el cronómetro.

Cuadricula
Representa el tablero del juego y gestiona la colocación de piezas.

FiguraConjunto
Define las figuras del Tetris y permite rotarlas o moverlas.

Chocolate
Representa cada casilla individual del tablero.

3. Gestión de la Partida

Encargada de administrar el estado del jugador y la puntuación.

Clase principal:

UsuarioPartida

Inicio de partida

Gestión de puntuación

Control del estado de la partida

4. Almacenamiento de Datos

Permite guardar y consultar el historial de partidas.

Clase principal:

FicheroPartidas

Lectura del historial

Escritura de nuevas partidas

Interfaz Gráfica

La aplicación utiliza Java Swing para construir la interfaz gráfica.

La ventana principal contiene:

Barra de menú con accesos rápidos

Panel lateral con botones de navegación

Panel central dinámico que muestra:

Pantalla inicial

Partida en curso

Historial de partidas

Información del juego

Configuración

Durante la partida se muestra:

El tablero de juego

Información del jugador

Barra de tiempo restante

Funcionalidades Principales

Sistema completo de partidas

Cronómetro de juego

Sistema de puntuación

Gestión del estado del tablero

Rotación y movimiento de piezas

Historial persistente de partidas

Ejecución del Proyecto

Clonar el repositorio

git clone https://github.com/tuusuario/tetris-java.git

Abrir el proyecto en un IDE compatible con Java (por ejemplo IntelliJ o Eclipse)

Ejecutar la clase principal del proyecto

Objetivos Académicos

Este proyecto fue desarrollado como práctica para consolidar conocimientos en:

Diseño orientado a objetos

Desarrollo de interfaces gráficas

Gestión de eventos en aplicaciones interactivas

Organización modular de software

Autores

Alejandro Martínez Hermosa

Félix Montero Quintana

Proyecto desarrollado para la asignatura Programación II
Grado en Ingeniería Informática.
