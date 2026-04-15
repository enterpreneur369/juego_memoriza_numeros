# Research - Pruebas Unitarias del Proyecto

## Decision 1: Extraer reglas de juego a una capa de logica pura
- Decision: Implementar una capa de dominio sin JavaFX para representar estado de partida, secuencia objetivo y validacion de intentos.
- Rationale: FR-002 y la constitucion exigen pruebas unitarias sin runtime grafico y separacion de responsabilidades.
- Alternatives considered: Probar handlers JavaFX directamente con framework de UI testing. Rechazado por mayor fragilidad, complejidad y menor determinismo.

## Decision 2: Inyectar entradas deterministas para secuencia y tiempo
- Decision: Evitar aleatoriedad y temporizacion real en pruebas mediante insumos controlados (secuencias fijas y constantes de tiempo documentadas).
- Rationale: FR-004 y SC-002 requieren resultados reproducibles en corridas consecutivas.
- Alternatives considered: Mantener `Random` y tolerancias temporales en tests. Rechazado por flakiness y falsos negativos.

## Decision 3: Cobertura minima orientada a reglas nucleares y estados
- Decision: Definir una suite inicial con al menos 8 escenarios criticos: clic correcto, clic incorrecto, progreso completo a victoria, inmutabilidad tras estado final, secuencia vacia, seleccion fuera de rango, manejo consistente de duplicados, y transiciones valida/invalida de estado.
- Rationale: Cumple SC-001 y cubre los edge cases del spec con alto valor de regresion.
- Alternatives considered: Cobertura parcial de "happy path" solamente. Rechazado por no cubrir riesgos principales del dominio.

## Decision 4: Mantener flujo de validacion estandar con Maven Wrapper
- Decision: Ejecutar validacion local con `mvnw -q -DskipTests compile` y `mvnw test` (o `mvnw.cmd` en Windows) como gate de calidad.
- Rationale: Constitucion (Principio IV) establece compile + tests como obligatorios.
- Alternatives considered: Ejecutar solo tests sin compile previo. Rechazado por menor robustez ante fallos de build.

## Decision 5: Contrato de pruebas como interfaz del comportamiento esperado
- Decision: Documentar en `contracts/` un contrato de escenarios, entradas y resultados esperados para reglas de juego.
- Rationale: Alinea FR-006 y facilita revisar que la suite cubra semantica, no solo implementacion.
- Alternatives considered: No definir contrato y depender solo de nombres de tests. Rechazado por menor trazabilidad a requisitos.
