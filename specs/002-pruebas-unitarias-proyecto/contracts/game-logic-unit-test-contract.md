# Contract: Game Logic Unit Test Contract

## Purpose
Definir el comportamiento minimo verificable por pruebas unitarias para la logica del juego en la feature `002-pruebas-unitarias-proyecto`.

## Scope
- Reglas nucleares de validacion de intentos.
- Transiciones de estado de partida.
- Casos borde con comportamiento determinista.
- Sin dependencia de JavaFX runtime.

## Required Test Scenarios
1. Inicio de partida con secuencia valida cambia estado a `EN_PROGRESO`.
2. Intento correcto avanza progreso sin finalizar prematuramente.
3. Intento incorrecto en `EN_PROGRESO` cambia a `DERROTA`.
4. Completar toda la secuencia cambia a `VICTORIA`.
5. Intento recibido en `VICTORIA` mantiene estado final sin cambios.
6. Intento recibido en `DERROTA` mantiene estado final sin cambios.
7. Inicio con secuencia vacia produce resultado invalido determinista (`SECUENCIA_VACIA` o equivalente documentado).
8. Entrada fuera de rango produce resultado invalido determinista (`FUERA_DE_RANGO` o equivalente documentado).
9. Secuencia con duplicados se evalua de forma posicional y consistente.

## Determinism Rules
- Mismas precondiciones + mismas entradas => mismo resultado en cada corrida.
- No usar `Random` ni temporizadores reales dentro de pruebas unitarias.
- Cualquier constante temporal usada por logica debe estar nombrada y ser configurable para pruebas.

## Coverage Mapping
- FR-001: Escenarios 2, 3, 4.
- FR-002: Suite ejecutable sin JavaFX runtime.
- FR-003: Escenarios 1, 3, 4, 5, 6.
- FR-004: Reglas de determinismo.
- FR-005: Ejecucion via `mvnw test`.
- FR-006: Este contrato y trazabilidad de escenarios.

## Test Traceability (implementado)
- Escenario 1: `GameSessionTransitionTest.shouldTransitionFromNewToInProgressOnStart`.
- Escenario 2: `GameEngineRulesTest.shouldAdvanceProgressOnCorrectAttempt`.
- Escenario 3: `GameEngineRulesTest.shouldEndInDefeatOnIncorrectAttempt`.
- Escenario 4: `GameSessionTransitionTest.shouldTransitionFromInProgressToVictoryWhenSequenceCompletes`.
- Escenario 5: `GameEngineTerminalStateTest.shouldKeepVictoryStateImmutableAfterAdditionalAttempts`.
- Escenario 6: `GameEngineTerminalStateTest.shouldKeepDefeatStateImmutableAfterAdditionalAttempts`.
- Escenario 7: `GameEngineEdgeCaseTest.shouldFailDeterministicallyWhenSequenceIsEmpty`.
- Escenario 8: `GameEngineEdgeCaseTest.shouldReturnOutOfRangeWithoutCorruptingState`.
- Escenario 9: `GameEngineEdgeCaseTest.shouldEvaluateDuplicatesPositionallyAndConsistently`.

## Verification Checklist
- [x] Al menos 8 escenarios criticos implementados y verdes.
- [x] Todos los tests son repetibles en corridas consecutivas.
- [x] No hay dependencias de UI en pruebas unitarias de logica.
- [x] La suite forma parte del flujo estandar de validacion del proyecto.
