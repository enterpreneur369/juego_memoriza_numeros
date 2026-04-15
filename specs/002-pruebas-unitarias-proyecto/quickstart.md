# Quickstart - Pruebas Unitarias del Proyecto

## 1. Prerequisitos
- Java 21 instalado.
- Maven Wrapper disponible (`mvnw` y `mvnw.cmd`).

## 2. Preparar y validar build base
Desde la raiz del repositorio:

### Windows (PowerShell)
```powershell
.\mvnw.cmd -q -DskipTests compile
.\mvnw.cmd test
```

### Unix-like (bash/zsh)
```bash
./mvnw -q -DskipTests compile
./mvnw test
```

## 3. Alcance de implementacion esperado
- Crear/ajustar clases de logica de juego en capa no-UI para evaluar intentos y transiciones de estado.
- Agregar pruebas unitarias JUnit 5 en `src/test/java` para escenarios P1/P2 y edge cases definidos en el spec.
- Mantener comportamiento determinista: sin dependencia de tiempo real ni aleatoriedad no controlada en tests.

## 4. Validacion de criterios de exito
- SC-001: verificar al menos 8 escenarios criticos cubiertos por pruebas unitarias.
- SC-002: ejecutar suite unitaria en corridas consecutivas y confirmar resultados consistentes.
- SC-003: confirmar compile + tests en <= 120 segundos en entorno local estandar.

## 5. Evidencia recomendada para PR
- Lista de clases de logica agregadas/ajustadas.
- Lista de tests y escenarios cubiertos.
- Salida resumida de `compile` y `test` con Maven Wrapper.

## 6. Evidencia de implementacion (2026-04-15)

### Clases de logica agregadas
- `src/main/java/com/example/numemoryapp/logic/GameStatus.java`
- `src/main/java/com/example/numemoryapp/logic/ValidationReason.java`
- `src/main/java/com/example/numemoryapp/logic/ValidationResult.java`
- `src/main/java/com/example/numemoryapp/logic/GameSession.java`
- `src/main/java/com/example/numemoryapp/logic/GameEngine.java`

### Pruebas unitarias agregadas
- `src/test/java/com/example/numemoryapp/logic/GameEngineRulesTest.java`
- `src/test/java/com/example/numemoryapp/logic/GameEngineTerminalStateTest.java`
- `src/test/java/com/example/numemoryapp/logic/GameSessionTransitionTest.java`
- `src/test/java/com/example/numemoryapp/logic/GameSessionInvalidTransitionTest.java`
- `src/test/java/com/example/numemoryapp/logic/GameEngineEdgeCaseTest.java`

### Cobertura de escenarios
- Reglas nucleares: acierto, error y finalizacion.
- Inmutabilidad en estados terminales.
- Transiciones validas e invalidas de estado.
- Casos borde: secuencia vacia, fuera de rango, duplicados.

### Resultado de gates Maven Wrapper
- `./mvnw.cmd -q -DskipTests compile`: PASS.
- `./mvnw.cmd test`: PASS, 12 tests, 0 fallos, 0 errores, 0 omitidos.
- `./mvnw.cmd -q test` (segunda corrida): PASS y resultados consistentes.

### Validacion por historias
- US1: validada por `GameEngineRulesTest` y `GameEngineTerminalStateTest`.
- US2: validada por `GameSessionTransitionTest` y `GameSessionInvalidTransitionTest`.
- US3: validada por `GameEngineEdgeCaseTest` y ejecucion repetible de `mvnw.cmd test`.
