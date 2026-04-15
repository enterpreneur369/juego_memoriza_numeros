# Contract: README Content Contract

## Purpose
Definir el contenido minimo que `README.md` debe exponer para cumplir el spec de la feature `001-actualizar-readme-proyecto`.

## Required Sections
1. `Objetivo del proyecto`
2. `Estado actual del juego` (solo funcionalidades visibles para usuario)
3. `Como ejecutar` (con Maven Wrapper para Windows y Unix-like)
4. `Estructura del proyecto`
5. `Alcance actual vs trabajo pendiente`

## Rules
- FR-001: La seccion de objetivo debe explicar problema y finalidad del juego.
- FR-002: El estado actual debe describir flujo principal observable en lenguaje claro.
- FR-003: Los comandos deben usar `mvnw` y `mvnw.cmd`.
- FR-004: Debe existir descripcion breve de componentes/rutas principales.
- FR-005: Debe existir separacion textual explicita entre funcionalidades actuales y pendientes.
- FR-006: El contenido no debe contener contradicciones con el estado real del repositorio.

## Verification Checklist
- [ ] Todas las secciones requeridas existen.
- [ ] No se documenta como "actual" algo no observable en la app.
- [ ] Los comandos de ejecucion son reproducibles con Maven Wrapper.
- [ ] El texto distingue claramente "actual" y "pendiente".
