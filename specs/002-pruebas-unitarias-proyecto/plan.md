# Implementation Plan: Pruebas Unitarias del Proyecto

**Branch**: `feature/add-unit-tests` | **Date**: 2026-04-15 | **Spec**: `specs/002-pruebas-unitarias-proyecto/spec.md`
**Input**: Feature specification from `specs/002-pruebas-unitarias-proyecto/spec.md`

## Summary

Agregar una base de pruebas unitarias enfocada en reglas del juego y transiciones de estado, separando la logica validable de JavaFX para ejecutar tests sin runtime grafico. El enfoque prioriza comportamiento determinista, cobertura de escenarios criticos (acierto, error, estado final inmutable, entradas invalidas y casos borde), e integracion natural con `mvnw test` dentro del flujo estandar.

## Technical Context

**Language/Version**: Java 21 (produccion y tests)  
**Primary Dependencies**: JavaFX (`javafx-controls`, `javafx-fxml`) para UI existente, JUnit 5 para pruebas unitarias, Maven Wrapper (`mvnw`, `mvnw.cmd`)  
**Storage**: N/A  
**Testing**: JUnit Jupiter (`mvnw test`) con foco en pruebas unitarias puras de logica de juego  
**Target Platform**: Aplicacion de escritorio JVM (JavaFX) ejecutable en Windows y Unix-like
**Project Type**: Proyecto unico Maven (desktop app) con capa de logica testeable  
**Performance Goals**: Suite de compile + tests <= 120 segundos en entorno local estandar (SC-003)  
**Constraints**: Mantener reglas actuales observables; comportamiento determinista en tests (sin aleatoriedad/tiempo real no controlado); evitar dependencia de JavaFX runtime en pruebas de logica; no introducir cambios de modulo innecesarios  
**Scale/Scope**: Feature centrada en `src/test/java` y extraccion minima de logica de reglas/estado fuera de handlers JavaFX para habilitar >= 8 escenarios criticos (SC-001)

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

Resultado pre-Phase 0: PASS

- [x] Java 21 and Maven wrapper usage are explicit; any dependency/module change is listed with rationale.
- [x] Game rules contract is preserved, or any rule change is explicitly approved in spec.
- [x] Time-based behavior is deterministic and represented by named constants.
- [x] Core game logic remains outside JavaFX view/event handlers and is testable without JavaFX runtime.
- [x] Validation plan includes compile (`mvn -q -DskipTests compile`) and tests (`mvn test` when tests exist).
- [x] New behavior includes tests at the most stable layer available (unit first, integration when needed).

Resultado post-Phase 1: PASS

## Project Structure

### Documentation (this feature)

```text
specs/002-pruebas-unitarias-proyecto/
├── plan.md
├── research.md
├── data-model.md
├── quickstart.md
├── contracts/
│   └── game-logic-unit-test-contract.md
└── tasks.md
```

### Source Code (repository root)

```text
pom.xml
src/
├── main/
│   └── java/
│       ├── module-info.java
│       └── com/example/numemoryapp/
│           ├── NuMemoryApp.java
│           ├── TileView.java
│           └── logic/            # nueva capa de reglas y estado testable sin JavaFX
└── test/
    └── java/
        └── com/example/numemoryapp/
            └── logic/            # pruebas unitarias de reglas/transiciones
```

**Structure Decision**: Se mantiene un solo proyecto Maven. Para cumplir la constitucion y FR-002/FR-003 se incorpora una capa de logica de juego desacoplada de JavaFX bajo `com.example.numemoryapp.logic`, y la cobertura automatizada se implementa en `src/test/java` con JUnit 5.

## Complexity Tracking

Sin violaciones de constitucion que requieran justificacion.
