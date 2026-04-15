# Data Model - Actualizacion de README del Proyecto

## Entity: SeccionREADME
- Description: Bloque de contenido del README con un objetivo especifico de comunicacion.
- Fields:
  - `id`: identificador de seccion (ej. `objetivo`, `funcionalidades`, `ejecucion`, `estructura`, `alcance`).
  - `titulo`: encabezado visible de la seccion.
  - `contenido`: texto markdown de la seccion.
  - `estado`: `actual` o `pendiente`.
  - `fuente`: evidencia principal (codigo, configuracion o decision de spec).
- Validation Rules:
  - Debe existir al menos una seccion por cada requisito FR-001..FR-005.
  - Secciones con `estado=actual` no pueden contener lenguaje futuro ambiguo.
  - Secciones con `estado=pendiente` deben evitar afirmaciones de disponibilidad actual.

## Entity: EstadoActualProyecto
- Description: Representacion del comportamiento implementado y visible al usuario.
- Fields:
  - `resumenJuego`: descripcion corta del objetivo y flujo principal.
  - `funcionalidadesVisibles[]`: lista de capacidades actualmente observables.
  - `comandosEjecucion[]`: comandos de preparacion/compilacion/ejecucion vigentes.
  - `componentesClave[]`: rutas o nombres de componentes relevantes para orientacion.
- Validation Rules:
  - Cada funcionalidad listada debe poder vincularse con comportamiento observable en la app actual.
  - Los comandos deben usar Maven Wrapper (`mvnw` y/o `mvnw.cmd`).

## Entity: TrabajoPendiente
- Description: Conjunto de mejoras no implementadas aun, separadas del estado actual.
- Fields:
  - `item`: descripcion de mejora o funcionalidad pendiente.
  - `justificacion`: por que aun no forma parte del estado actual.
  - `prioridad`: `alta`, `media` o `baja` (opcional para orden sugerido).
- Validation Rules:
  - Ningun item pendiente puede duplicar una funcionalidad declarada como actual.
  - Debe existir separacion textual explicita entre "actual" y "pendiente" en README.

## Relationships
- `EstadoActualProyecto` se documenta a traves de multiples `SeccionREADME`.
- `TrabajoPendiente` se representa en una o mas `SeccionREADME` con `estado=pendiente`.
