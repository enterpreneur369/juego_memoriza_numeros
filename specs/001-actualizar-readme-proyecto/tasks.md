# Tasks: Actualizacion de README del Proyecto

**Input**: Design documents from /specs/001-actualizar-readme-proyecto/
**Prerequisites**: plan.md, spec.md, research.md, data-model.md, contracts/readme-content-contract.md, quickstart.md

**Tests**: No se agregan nuevas pruebas automatizadas porque no hay cambios de comportamiento; se ejecutan gates de compilacion y tests existentes con Maven Wrapper como exige la constitucion.

**Organization**: Tareas agrupadas por historia de usuario para permitir implementacion y validacion independiente.

## Phase 1: Setup (Shared Context)

**Purpose**: Recolectar evidencia y contexto base antes de editar el README.

- [X] T001 Revisar requisitos y prioridades en specs/001-actualizar-readme-proyecto/spec.md
- [X] T002 [P] Verificar restricciones tecnicas y de validacion en specs/001-actualizar-readme-proyecto/plan.md
- [X] T003 [P] Confirmar principios aplicables de documentacion y quality gates en constitution.md
- [X] T004 [P] Extraer flujo visible del juego desde src/main/java/com/example/numemoryapp/NuMemoryApp.java
- [X] T005 [P] Extraer comportamiento visible de celdas/interaccion desde src/main/java/com/example/numemoryapp/TileView.java

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Preparar estructura base del README y trazabilidad minima a FR/contrato.

**⚠️ CRITICAL**: Ninguna historia debe empezar hasta completar esta fase.

- [X] T006 Definir esqueleto de secciones obligatorias del contrato en README.md
- [X] T007 Construir checklist de trazabilidad FR-001..FR-006 usando specs/001-actualizar-readme-proyecto/contracts/readme-content-contract.md
- [X] T008 Alinear comandos base de quickstart para Windows/Unix desde specs/001-actualizar-readme-proyecto/quickstart.md

**Checkpoint**: Estructura base y criterios de cumplimiento listos para implementar historias.

---

## Phase 3: User Story 1 - Documentar estado actual del juego (Priority: P1) 🎯 MVP

**Goal**: Dejar claro el objetivo del proyecto y el estado funcional visible del juego.

**Independent Test**: Leyendo solo README.md, una persona puede explicar objetivo del juego y flujo principal observable sin abrir codigo.

### Implementation for User Story 1

- [X] T009 [US1] Redactar objetivo del proyecto y problema que resuelve en README.md
- [X] T010 [US1] Documentar funcionalidades visibles implementadas actualmente en README.md
- [X] T011 [US1] Documentar flujo principal del juego (inicio, revelado, ocultado, validacion y fin) en README.md
- [X] T012 [US1] Verificar que cada afirmacion de estado actual sea consistente con src/main/java/com/example/numemoryapp/NuMemoryApp.java

**Checkpoint**: US1 queda funcional y validable de forma independiente.

---

## Phase 4: User Story 2 - Incluir guia de ejecucion y estructura (Priority: P2)

**Goal**: Permitir compilacion/ejecucion y orientacion de estructura usando solo el README.

**Independent Test**: Siguiendo README.md, una persona puede identificar prerequisitos, compilar/ejecutar con Maven Wrapper y ubicar componentes principales.

### Implementation for User Story 2

- [X] T013 [US2] Redactar prerequisitos del entorno (Java 21 y wrapper) en README.md
- [X] T014 [US2] Agregar comandos de compilacion, pruebas y ejecucion con .\mvnw.cmd y ./mvnw en README.md
- [X] T015 [US2] Agregar seccion de estructura del proyecto con rutas y componentes principales en README.md
- [X] T016 [US2] Validar que la guia de ejecucion coincide con pom.xml y no requiere comandos fuera de Maven Wrapper

**Checkpoint**: US2 queda funcional y validable de forma independiente.

---

## Phase 5: User Story 3 - Establecer base para futuras iteraciones (Priority: P3)

**Goal**: Separar explicitamente alcance actual y trabajo pendiente para trazabilidad futura.

**Independent Test**: Leyendo README.md, se distingue sin ambiguedad que esta implementado hoy y que queda pendiente para siguientes iteraciones.

### Implementation for User Story 3

- [X] T017 [US3] Crear seccion "Alcance actual" con resumen de baseline implementado en README.md
- [X] T018 [US3] Crear seccion "Trabajo pendiente" separada de estado actual en README.md
- [X] T019 [US3] Revisar que ningun pendiente duplique funcionalidades declaradas como actuales en README.md

**Checkpoint**: US3 queda funcional y validable de forma independiente.

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Cierre de calidad documental y validaciones obligatorias de constitucion.

- [X] T020 Revisar consistencia interna y eliminar contradicciones finales en README.md
- [X] T021 Ejecutar gate de compilacion con Maven Wrapper usando mvnw.cmd
- [X] T022 Ejecutar gate de pruebas existentes con Maven Wrapper usando mvnw.cmd
- [X] T023 Actualizar evidencia final de cumplimiento en specs/001-actualizar-readme-proyecto/tasks.md

---

## Dependencies & Execution Order

### Phase Dependencies

- Setup (Phase 1): sin dependencias.
- Foundational (Phase 2): depende de Setup y bloquea todas las historias.
- User Stories (Phases 3-5): dependen de Foundational; se recomienda ejecutarlas en prioridad P1 -> P2 -> P3 porque comparten README.md.
- Polish (Phase 6): depende de historias completadas.

### User Story Dependencies

- US1 (P1): inicia tras Phase 2; define baseline funcional del README.
- US2 (P2): depende de baseline de US1 para mantener coherencia narrativa en README.md.
- US3 (P3): depende de US1 y US2 para separar correctamente actual vs pendiente.

### Within Each User Story

- Redaccion de contenido base antes de validacion de coherencia.
- Verificacion contra evidencia de codigo/configuracion antes de cerrar la historia.

### Parallel Opportunities

- T002, T003, T004 y T005 pueden ejecutarse en paralelo (fuentes distintas).
- El resto de tareas se recomienda en serie por edicion concurrente del mismo archivo README.md.

---

## Parallel Example: Setup

- Ejecutar en paralelo T002 (plan), T003 (constitucion), T004 (NuMemoryApp), T005 (TileView).
- Consolidar hallazgos en T006 para construir esqueleto unico de README.md.

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Completar Phase 1 y Phase 2.
2. Completar US1 (Phase 3).
3. Validar lectura independiente de US1.
4. Compartir para feedback temprano.

### Incremental Delivery

1. Setup + Foundational.
2. US1 (baseline funcional visible).
3. US2 (onboarding tecnico y estructura).
4. US3 (alcance y pendientes para iteraciones futuras).
5. Polish + gates de compilacion/pruebas.

### Parallel Team Strategy

1. Una persona ejecuta T002/T003 mientras otra ejecuta T004/T005.
2. Desde T006 en adelante, una sola persona integra README.md para evitar conflictos de merge.

---

## Notes

- Formato checklist aplicado en todas las tareas: - [ ] Txxx [P?] [US?] Descripcion con ruta.
- [P] se usa solo en tareas sin colision de archivos.
- No se incluyen tareas de nuevos tests por tratarse de cambio documental sin nuevo comportamiento.
