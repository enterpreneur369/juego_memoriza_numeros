# NuMemoryApp

NuMemoryApp es un juego de memoria visual construido con JavaFX. La aplicacion muestra una secuencia numerica distribuida en una cuadricula, la oculta despues de unos segundos y permite al jugador intentar reconstruirla haciendo clic en el orden correcto.

## Objetivo del proyecto

El proyecto sirve como base de un juego para memorizar numeros y practicar reconocimiento de patrones en una interfaz grafica sencilla. El repositorio tambien funciona como baseline de desarrollo para iteraciones futuras sobre reglas de juego, feedback visual y separacion de logica.

## Estado actual del juego

El estado implementado hoy refleja un prototipo funcional y observable desde la interfaz actual:

- La ventana principal muestra un boton `Start` para iniciar una partida.
- Al comenzar, se crean 9 celdas numeradas del `1` al `9` en posiciones aleatorias dentro del tablero.
- Cada celda se dibuja con borde visible y el numero centrado.
- Los numeros permanecen visibles durante 6 segundos antes de ocultarse.
- Despues del ocultado, la persona jugadora debe hacer clic sobre las celdas en el orden correcto de la secuencia.
- Cuando el clic es correcto, el numero de esa celda vuelve a mostrarse.
- Cuando el clic es incorrecto, la secuencia pendiente se limpia y la partida termina.
- Si se sigue haciendo clic despues de perder, la aplicacion informa en consola que la partida ya termino.

## Flujo principal del juego

1. La aplicacion abre una ventana JavaFX con el tablero vacio y el boton `Start`.
2. Al pulsar `Start`, se genera un tablero nuevo con 9 celdas numeradas en posiciones aleatorias sin repetir coordenadas.
3. La secuencia completa queda visible temporalmente para memorizarla.
4. Pasados 6 segundos, los numeros se ocultan.
5. La persona jugadora intenta reconstruir la secuencia haciendo clic en las celdas en orden ascendente.
6. Cada acierto revela la celda seleccionada; un error termina la partida inmediatamente.

## Como ejecutar

### Prerequisitos

- Java 21 instalado y disponible en el entorno.
- Permisos para ejecutar Maven Wrapper desde la raiz del repositorio.

### Compilar

Windows PowerShell:

```powershell
.\mvnw.cmd -q -DskipTests compile
```

Unix-like:

```bash
./mvnw -q -DskipTests compile
```

### Ejecutar pruebas existentes

Windows PowerShell:

```powershell
.\mvnw.cmd test
```

Unix-like:

```bash
./mvnw test
```

### Ejecutar la aplicacion

Windows PowerShell:

```powershell
.\mvnw.cmd javafx:run
```

Unix-like:

```bash
./mvnw javafx:run
```

## Estructura del proyecto

```text
.
├── README.md
├── pom.xml
├── mvnw
├── mvnw.cmd
├── constitution.md
├── specs/
│   └── 001-actualizar-readme-proyecto/
└── src/
	└── main/
		└── java/
			├── module-info.java
			└── com/example/numemoryapp/
				├── NuMemoryApp.java
				└── TileView.java
```

Componentes principales:

- `src/main/java/com/example/numemoryapp/NuMemoryApp.java`: punto de entrada JavaFX y coordinador del flujo visible del juego.
- `src/main/java/com/example/numemoryapp/TileView.java`: vista de cada celda numerada, con comportamiento para mostrar y ocultar el texto.
- `src/main/java/module-info.java`: configuracion del modulo Java y declaraciones de JavaFX.
- `pom.xml`: dependencias JavaFX/JUnit y configuracion de compilacion con Maven.

## Alcance actual

La baseline actual cubre un unico flujo de partida manual: iniciar, memorizar numeros, ocultarlos y validar clics en orden. El tablero se reconstruye al iniciar una nueva partida y el feedback de derrota se limita a mensajes por consola.

## Pruebas unitarias de logica

La logica del juego se desacoplo de JavaFX en el paquete `com.example.numemoryapp.logic` para habilitar pruebas unitarias deterministas.

Cobertura principal incorporada:

- Reglas de acierto/error y finalizacion.
- Inmutabilidad de estados terminales (victoria/derrota).
- Transiciones validas e invalidas de estado de partida.
- Casos borde: secuencia vacia, fuera de rango y duplicados.

Comandos de validacion local:

Windows PowerShell:

```powershell
.\mvnw.cmd -q -DskipTests compile
.\mvnw.cmd test
```

Unix-like:

```bash
./mvnw -q -DskipTests compile
./mvnw test
```

Para verificar repetibilidad de la suite, ejecutar `test` en corridas consecutivas y comparar resultados.

## Trabajo pendiente

Las siguientes mejoras todavia no forman parte del estado actual implementado:

- Mensajes visibles dentro de la interfaz para victoria, derrota o reinicio.
- Confirmacion explicita de victoria al completar toda la secuencia.
- Reinicio automatico o control dedicado para volver a jugar despues de perder.
- Separacion mas clara entre logica del juego y adaptadores JavaFX para facilitar pruebas unitarias.
- Cobertura automatizada para las reglas del juego y los estados de partida.

## Notas de mantenimiento

- Este README describe solo el comportamiento visible y confirmado en el codigo actual.
- Las reglas de ingenieria y validacion del repositorio estan definidas en `constitution.md`.
