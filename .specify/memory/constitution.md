<!--
Sync Impact Report
- Version change: template (unversioned) -> 1.0.0
- Modified principles:
	- Template Principle 1 -> I. Java 21 + JavaFX Module Integrity
	- Template Principle 2 -> II. Game Rules Before UI Effects
	- Template Principle 3 -> III. Separation of Concerns for Testability
	- Template Principle 4 -> IV. Quality Gates Are Non-Negotiable
	- Template Principle 5 -> V. Keep the Experience Simple and Observable
- Added sections:
	- Technical Constraints
	- Development Workflow
- Removed sections:
	- None
- Templates requiring updates:
	- ✅ .specify/templates/plan-template.md
	- ✅ .specify/templates/spec-template.md
	- ✅ .specify/templates/tasks-template.md
	- ⚠ pending: .specify/templates/commands/*.md (path not present in repository)
- Deferred TODOs:
	- None
-->

# NuMemoryApp Constitution

## Core Principles

### I. Java 21 + JavaFX Module Integrity
- The codebase stays on Java 21 with Maven as the single build tool.
- The Java module boundary in `module-info.java` is authoritative; new dependencies must be explicitly declared and justified.
- UI components must remain under `com.example.numemoryapp` unless a clear package split is introduced by a spec.

### II. Game Rules Before UI Effects
- Game behavior is the primary contract: sequence generation, reveal window, click validation, success/fail end states.
- Visual changes may not alter game rules unless the spec explicitly says so.
- Time-based behavior (for example hide delays) must be deterministic and configurable through named constants.

### III. Separation of Concerns for Testability
- Game state and rule evaluation should be implemented in logic classes that can be tested without JavaFX runtime.
- JavaFX nodes (`Application`, `Pane`, `Timeline`, views) are adapters over game logic, not the source of truth.
- Every new feature must preserve or improve this separation; avoid embedding core rules directly in event handlers.

### IV. Quality Gates Are Non-Negotiable
- A change is complete only when it builds and tests pass with Maven.
- Minimum validation for each task: compile (`mvn -q -DskipTests compile`) and tests (`mvn test`) when tests exist.
- New behavior requires tests at the most stable layer available (unit tests first, integration tests when required by JavaFX interactions).

### V. Keep the Experience Simple and Observable
- Favor clear player feedback over complex animation stacks.
- User-visible game outcomes (start, success, fail, timeout) must be explicit and easy to trace.
- Replace ad-hoc console messages with a consistent mechanism (status label, structured logs, or both) when introducing broader flows.

## Technical Constraints
- Build system: Maven Wrapper (`mvnw`, `mvnw.cmd`) is the default entry point for local and CI commands.
- Language and runtime: Java 21.
- UI stack: JavaFX Controls/FXML.
- Test stack: JUnit 5.
- Repository layout baseline:
	- `src/main/java/module-info.java` for module wiring.
	- `src/main/java/com/example/numemoryapp` for app and views.
	- `src/test/java` for automated tests.

## Development Workflow
1. Define or update the feature in Spec Kit artifacts (spec, plan, tasks) before implementation of non-trivial changes.
2. Implement in small steps with compile/test checks between steps.
3. Keep public behavior stable unless the active spec defines a behavior change.
4. Document any new constants, timings, and user-facing flows in the corresponding spec/task artifacts.
5. Prefer small, reviewable commits aligned to task boundaries.

## Governance
- This constitution overrides local habits when there is conflict.
- Any pull request or review must check compliance with the five core principles.
- Amendments require:
	1. A short rationale.
	2. Version bump according to semantic impact:
		 - MAJOR: principle removed or redefined incompatibly.
		 - MINOR: new principle/section or materially expanded guidance.
		 - PATCH: wording and clarity updates with no behavioral change.
	3. Updates to affected Spec Kit templates or memory files when applicable.

**Version**: 1.0.0
**Ratified**: 2026-04-15
**Last Amended**: 2026-04-15
