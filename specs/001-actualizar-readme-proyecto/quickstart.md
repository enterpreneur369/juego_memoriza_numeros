# Quickstart - Actualizacion de README del Proyecto

## 1. Prerequisitos
- Java 21 instalado.
- Permisos para ejecutar Maven Wrapper (`mvnw`/`mvnw.cmd`).

## 2. Validar estado base del proyecto
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

## 3. Actualizar README.md
Asegurar que el archivo incluya como minimo:
- Objetivo del proyecto y problema que resuelve.
- Funcionalidades visibles actualmente implementadas y flujo principal del juego.
- Pasos de preparacion, compilacion y ejecucion usando Maven Wrapper.
- Vista general de estructura y componentes principales.
- Separacion explicita entre estado actual y trabajo pendiente.

## 4. Validacion funcional de contenido
- Revisar que no haya contradicciones internas en el README.
- Verificar que las afirmaciones sobre funcionalidad actual coincidan con el comportamiento observable del juego.
- Confirmar que SC-002 y SC-004 se tratan como metas aspiracionales (no bloqueantes).

## 5. Evidencia recomendada para PR
- Diff de `README.md`.
- Salida resumida de compile y test ejecutados con Maven Wrapper.
