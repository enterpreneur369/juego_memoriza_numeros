# Research - Actualizacion de README del Proyecto

## Decision 1: Limitar alcance a documentacion sin cambios de codigo
- Decision: La implementacion se limita a actualizar `README.md` y no modifica clases Java, `pom.xml` ni `module-info.java`.
- Rationale: El spec y la alineacion constitucional indican que no deben alterarse reglas del juego ni comportamiento temporal.
- Alternatives considered: Ajustar flujo del juego para que README y UI coincidan. Rechazado porque cambia comportamiento y excede alcance.

## Decision 2: Usar Maven Wrapper como estandar de ejecucion documentado
- Decision: Documentar comandos con `mvnw` (Unix) y `mvnw.cmd` (Windows) para compile, run y test.
- Rationale: La constitucion define Maven Wrapper como punto de entrada por defecto local/CI.
- Alternatives considered: Documentar `mvn` global. Rechazado por menor reproducibilidad entre entornos.

## Decision 3: Basar funcionalidades README solo en comportamiento visible actual
- Decision: Describir juego segun comportamiento observable en `NuMemoryApp` y `TileView`: inicio, aparicion de numeros, ocultado tras temporizador, validacion de clic en secuencia y fin por error.
- Rationale: La aclaracion del spec exige cubrir solo funcionalidades visibles para usuario y flujo principal.
- Alternatives considered: Describir arquitectura interna y deuda tecnica en detalle. Rechazado para no mezclar onboarding funcional con diseño interno.

## Decision 4: Tratar SC-002 y SC-004 como metas aspiracionales
- Decision: Mantener SC-002 y SC-004 como metas no bloqueantes para validacion estricta.
- Rationale: Fue aclarado explicitamente en la sesion de clarificacion del spec.
- Alternatives considered: Convertir SC-002/SC-004 a criterios estrictos de aceptacion. Rechazado por requerir mediciones operativas fuera de este cambio documental.

## Decision 5: Definir contrato de contenido de README
- Decision: Crear un contrato minimo de secciones obligatorias y trazabilidad a FR-001..FR-006.
- Rationale: Permite revisar consistencia de contenido sin introducir API tecnica.
- Alternatives considered: No definir contrato y dejar contenido libre. Rechazado por riesgo de omitir requisitos funcionales del spec.
