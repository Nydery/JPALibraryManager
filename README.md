[![CI Integration tests](https://github.com/Nydery/JPALibraryManager/actions/workflows/maven_test_ci.yml/badge.svg)](https://github.com/Nydery/JPALibraryManager/actions/workflows/maven_test_ci.yml)

# 🔴Disclaimer
Aufgrund unseres Datenmodells verzichten wir bei den Entitäten zum Thema Media auf Vererbung. (Wie am 22.03.2022 mit Prof. Nöbauer besprochen)

# Current status
- All entities are declared and contain attributes specified in our [ERD](./spec/ERD.pdf).
- Models (DTOs) for each entity are fully implemented
- Repository contains simple `add()` and `remove()` for all entities (abstract with `IEntity`).
- Testclasses for repository and logic exist, but no tests have been implemented yet.

# Note
Die Action *[CI](https://github.com/Nydery/JPALibraryManager/actions/workflows/maven_test_ci.yml)* sollte ständig bei Updates die vorhandenen Tests ausführen. Das funktioniert jedoch nicht, da die spezifizierte Datenbank auf *localhost* natürlich nicht auf dem GH runner läuft.

**Edit:** Die Action *[CI](https://github.com/Nydery/JPALibraryManager/actions/workflows/maven_test_ci.yml)* läuft nun mithilfe einer auf dem GH-Runner gestarteten PostgreSQL Datenbank.
