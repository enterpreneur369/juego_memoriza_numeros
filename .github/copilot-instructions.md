# juego_memoriza_numeros Development Guidelines

Auto-generated from all feature plans. Last updated: 2026-04-15

## Active Technologies
- Java 21 (produccion y tests) + JavaFX (`javafx-controls`, `javafx-fxml`) para UI existente, JUnit 5 para pruebas unitarias, Maven Wrapper (`mvnw`, `mvnw.cmd`) (feature/add-unit-tests)

- Java 21 (codigo existente), Markdown para documentacion + JavaFX (`javafx-controls`, `javafx-fxml`), Maven Wrapper (`mvnw`, `mvnw.cmd`), JUnit 5 (stack existente) (001-actualizar-readme-proyecto)

## Project Structure

```text
src/
tests/
```

## Commands

# Add commands for Java 21 (codigo existente), Markdown para documentacion

## Code Style

Java 21 (codigo existente), Markdown para documentacion: Follow standard conventions

## Recent Changes
- feature/add-unit-tests: Added Java 21 (produccion y tests) + JavaFX (`javafx-controls`, `javafx-fxml`) para UI existente, JUnit 5 para pruebas unitarias, Maven Wrapper (`mvnw`, `mvnw.cmd`)

- 001-actualizar-readme-proyecto: Added Java 21 (codigo existente), Markdown para documentacion + JavaFX (`javafx-controls`, `javafx-fxml`), Maven Wrapper (`mvnw`, `mvnw.cmd`), JUnit 5 (stack existente)

<!-- MANUAL ADDITIONS START -->
- Feature `002-pruebas-unitarias-proyecto`: se agrego capa de logica desacoplada en `src/main/java/com/example/numemoryapp/logic/` para validar reglas sin runtime JavaFX.
- Pruebas unitarias de dominio en `src/test/java/com/example/numemoryapp/logic/` cubren reglas nucleares, estados terminales, transiciones y casos borde.
- Validacion local obligatoria de esta feature:
	- `./mvnw.cmd -q -DskipTests compile`
	- `./mvnw.cmd test`
<!-- MANUAL ADDITIONS END -->
