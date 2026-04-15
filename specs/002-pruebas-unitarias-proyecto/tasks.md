# Tasks: Pruebas Unitarias del Proyecto

**Input**: Design documents from `/specs/002-pruebas-unitarias-proyecto/`
**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: Esta feature solicita enfoque de pruebas primero en la capa de logica (TDD ligero), manteniendo ejecucion determinista y sin runtime JavaFX para pruebas unitarias.

**Organization**: Tareas agrupadas por historia de usuario para permitir implementacion y validacion independiente.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Puede ejecutarse en paralelo (archivos distintos, sin dependencia directa)
- **[Story]**: Historia de usuario (US1, US2, US3)
- Cada tarea incluye ruta de archivo exacta

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Preparar estructura y configuracion minima de pruebas para Java 21 + Maven Wrapper.

- [X] T001 Crear estructura base de pruebas unitarias en `src/test/java/com/example/numemoryapp/logic/.gitkeep`
- [X] T002 Ajustar dependencias/plugins de pruebas JUnit 5 en `pom.xml` (sin romper build JavaFX existente)
- [X] T003 [P] Registrar alcance y comandos de validacion de pruebas en `specs/002-pruebas-unitarias-proyecto/quickstart.md`

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Implementar la capa de logica testeable y desacoplada de JavaFX requerida por la constitucion.

**⚠️ CRITICAL**: Ninguna historia de usuario debe iniciar hasta completar esta fase.

- [X] T004 Crear enum de estados de partida en `src/main/java/com/example/numemoryapp/logic/GameStatus.java`
- [X] T005 [P] Crear enum de motivos de validacion en `src/main/java/com/example/numemoryapp/logic/ValidationReason.java`
- [X] T006 [P] Crear resultado inmutable de evaluacion en `src/main/java/com/example/numemoryapp/logic/ValidationResult.java`
- [X] T007 Implementar estado de sesion y reglas de transicion en `src/main/java/com/example/numemoryapp/logic/GameSession.java`
- [X] T008 Implementar evaluador determinista de intentos en `src/main/java/com/example/numemoryapp/logic/GameEngine.java`
- [X] T009 Integrar `GameEngine` en flujo de clics de `src/main/java/com/example/numemoryapp/NuMemoryApp.java` preservando comportamiento observable

**Checkpoint**: Base de logica desacoplada lista; historias pueden ejecutarse sin dependencia de runtime JavaFX en pruebas.

---

## Phase 3: User Story 1 - Validar reglas del juego sin interfaz (Priority: P1) 🎯 MVP

**Goal**: Verificar automaticamente reglas nucleares de acierto, error y estado final inmutable sin UI.

**Independent Test**: Ejecutar solo tests de reglas y confirmar escenarios de clic correcto, incorrecto y bloqueo tras estado final sin iniciar JavaFX.

### Tests for User Story 1

- [X] T010 [P] [US1] Crear pruebas de regla acierto/error en `src/test/java/com/example/numemoryapp/logic/GameEngineRulesTest.java`
- [X] T011 [P] [US1] Crear pruebas de inmutabilidad en estado terminal en `src/test/java/com/example/numemoryapp/logic/GameEngineTerminalStateTest.java`

### Implementation for User Story 1

- [X] T012 [US1] Implementar ajustes de reglas para pasar T010-T011 en `src/main/java/com/example/numemoryapp/logic/GameEngine.java`
- [X] T013 [US1] Asegurar contrato de estado terminal en `src/main/java/com/example/numemoryapp/logic/GameSession.java`
- [X] T014 [US1] Ejecutar y documentar validacion de US1 en `specs/002-pruebas-unitarias-proyecto/quickstart.md`

**Checkpoint**: US1 funcional y validable de forma independiente.

---

## Phase 4: User Story 2 - Verificar transiciones de estado de partida (Priority: P2)

**Goal**: Cubrir transiciones validas e invalidas entre NUEVA, EN_PROGRESO, VICTORIA y DERROTA.

**Independent Test**: Ejecutar tests de transicion y confirmar que solo se permiten transiciones definidas por el modelo.

### Tests for User Story 2

- [X] T015 [P] [US2] Crear pruebas de transiciones validas en `src/test/java/com/example/numemoryapp/logic/GameSessionTransitionTest.java`
- [X] T016 [P] [US2] Crear pruebas de transiciones invalidas y motivos en `src/test/java/com/example/numemoryapp/logic/GameSessionInvalidTransitionTest.java`

### Implementation for User Story 2

- [X] T017 [US2] Ajustar reglas de transicion para pasar T015-T016 en `src/main/java/com/example/numemoryapp/logic/GameSession.java`
- [X] T018 [US2] Alinear contrato y trazabilidad FR-003 en `specs/002-pruebas-unitarias-proyecto/contracts/game-logic-unit-test-contract.md`

**Checkpoint**: US2 funcional sin ambiguedad de estados.

---

## Phase 5: User Story 3 - Asegurar calidad continua con pruebas repetibles (Priority: P3)

**Goal**: Integrar ejecucion repetible de pruebas unitarias al flujo habitual de validacion del proyecto.

**Independent Test**: Ejecutar compile + test en corridas consecutivas con resultados consistentes y tiempo total <= 120 segundos.

### Tests for User Story 3

- [X] T019 [P] [US3] Crear prueba de casos borde (secuencia vacia, fuera de rango, duplicados) en `src/test/java/com/example/numemoryapp/logic/GameEngineEdgeCaseTest.java`

### Implementation for User Story 3

- [X] T020 [US3] Implementar manejo determinista de casos borde para T019 en `src/main/java/com/example/numemoryapp/logic/GameEngine.java`
- [X] T021 [US3] Documentar ejecucion repetible de validacion local en `README.md`

**Checkpoint**: US3 habilita validacion continua y repetible.

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Cerrar trazabilidad a constitucion, calidad y evidencia final.

- [X] T022 [P] Actualizar contexto de cambios y stack en `.github/copilot-instructions.md`
- [X] T023 Ejecutar gate de compilacion con Maven Wrapper y registrar resultado en `specs/002-pruebas-unitarias-proyecto/quickstart.md` (`mvnw -q -DskipTests compile` / `mvnw.cmd -q -DskipTests compile`)
- [X] T024 Ejecutar gate de pruebas con Maven Wrapper y registrar resultado en `specs/002-pruebas-unitarias-proyecto/quickstart.md` (`mvnw test` / `mvnw.cmd test`)

---

## Dependencies & Execution Order

### Phase Dependencies

- Setup (Phase 1): inicia inmediatamente.
- Foundational (Phase 2): depende de Setup; bloquea todas las historias.
- User Stories (Phase 3-5): dependen de completar Foundational.
- Polish (Phase 6): depende de completar historias objetivo.

### User Story Dependencies

- US1 (P1): inicia despues de Phase 2; sin dependencia funcional de US2/US3.
- US2 (P2): inicia despues de Phase 2; reutiliza entidades de logica creadas en Phase 2.
- US3 (P3): inicia despues de Phase 2; extiende cobertura de borde y flujo de validacion.

### Critical Dependency Chain

- T001 -> T002 -> T004 -> T007 -> T008 -> T009 -> T010 -> T012 -> T015 -> T017 -> T019 -> T020 -> T023 -> T024

### Within Each User Story

- Escribir pruebas primero y validar que fallen antes de ajustar implementacion.
- Ajustar logica de dominio despues de pruebas.
- Documentar evidencia de validacion al cierre de la historia.

### Parallel Opportunities

- Setup: T003 en paralelo con T001-T002.
- Foundational: T005 y T006 en paralelo tras T004.
- US1: T010 y T011 en paralelo.
- US2: T015 y T016 en paralelo.
- US3: T019 puede iniciar al arrancar US3.
- Polish: T022 puede ejecutarse en paralelo mientras se prepara ejecucion de gates.

---

## Parallel Example: User Story 1

- Ejecutar en paralelo T010 y T011 (archivos de pruebas distintos).
- Al terminar ambos, continuar con T012 y T013 en secuencia.

---

## Implementation Strategy

### MVP First (US1)

1. Completar Phase 1 y Phase 2.
2. Completar US1 (Phase 3).
3. Validar independientemente reglas nucleares sin UI.

### Incremental Delivery

1. Entregar MVP con US1.
2. Agregar US2 para robustecer transiciones.
3. Agregar US3 para repetibilidad y calidad continua.
4. Cerrar con gates y trazabilidad de constitucion en Phase 6.

### Constitution Alignment Checks

- Principio I: cambios de dependencias/modulo solo en `pom.xml` y `src/main/java/module-info.java` cuando aplique.
- Principio II: reglas de juego preservadas y validadas por pruebas de dominio.
- Principio III: logica central en `src/main/java/com/example/numemoryapp/logic/` sin acoplar a JavaFX.
- Principio IV: gates obligatorios compile + test incluidos como T023 y T024.
- Principio V: resultados observables y motivos de validacion trazables en contrato/documentacion.
