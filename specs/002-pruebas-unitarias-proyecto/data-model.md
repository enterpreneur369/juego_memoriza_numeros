# Data Model - Pruebas Unitarias del Proyecto

## Entity: EstadoPartida
- Description: Estado del ciclo de vida de la partida.
- Values:
  - `NUEVA`: partida creada, sin intentos procesados.
  - `EN_PROGRESO`: partida activa aceptando intentos.
  - `VICTORIA`: secuencia completada correctamente.
  - `DERROTA`: partida finalizada por intento invalido/incorrecto.
- Validation Rules:
  - Solo transiciones validas: `NUEVA -> EN_PROGRESO`, `EN_PROGRESO -> VICTORIA`, `EN_PROGRESO -> DERROTA`.
  - `VICTORIA` y `DERROTA` son estados terminales e inmutables.

## Entity: SecuenciaObjetivo
- Description: Lista ordenada de valores esperados por la logica.
- Fields:
  - `valores`: lista no nula de enteros o identificadores de celda.
  - `longitud`: tamano derivado de `valores`.
- Validation Rules:
  - No puede ser vacia al iniciar una partida valida.
  - Debe conservar orden para evaluar acierto posicional.
  - Si contiene duplicados, la evaluacion debe ser posicional y determinista.

## Entity: IntentoJugador
- Description: Seleccion individual evaluada contra la secuencia en progreso.
- Fields:
  - `valorSeleccionado`: valor/celda elegida por el jugador.
  - `indiceEsperado`: posicion actual esperada en la secuencia.
  - `esValido`: indica si la entrada cae en rango permitido.
- Validation Rules:
  - Si `esValido=false`, se registra resultado de entrada invalida de forma determinista.
  - Solo se procesa cuando la partida esta en `EN_PROGRESO`.

## Entity: ResultadoValidacion
- Description: Resultado de evaluar un intento o una transicion.
- Fields:
  - `estadoAntes`: estado de partida previo.
  - `estadoDespues`: estado resultante.
  - `acierto`: booleano de coincidencia con valor esperado.
  - `motivo`: codigo/texto corto (`CORRECTO`, `INCORRECTO`, `FUERA_DE_RANGO`, `ESTADO_FINAL`, `SECUENCIA_VACIA`).
  - `progresoActual`: cantidad de aciertos acumulados.
- Validation Rules:
  - Debe ser completamente derivable de entradas y estado previo.
  - Para mismas entradas y estado, el resultado debe ser identico (determinismo).

## Relationships
- `SecuenciaObjetivo` define la referencia para cada `IntentoJugador`.
- `IntentoJugador` produce un `ResultadoValidacion`.
- `ResultadoValidacion` determina la transicion de `EstadoPartida`.
