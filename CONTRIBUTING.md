# Contributing to GEG2
Thank you for contributing to our DAM group project! To maintain code quality and prevent git conflicts, please adhere to the following workflow guidelines.

---

## Branching Strategy
We use a simplified branching model. Never commit directly to `main`.
* **Feature Branches:** `feature/short-description` (example: `feature/product-controller`)
* **Fix Branches:** `fix/short-description` (example: `fix/jdbc-exception`)

---

## Coding Standards
To avoid code disparities across layers, we enforce strict naming conventions:
* **Java Classes:** PascalCase (`ProductService`, `OrderController`).
* **Variables & Methods:** camelCase (`createOrder`, `tableId`).
* **Database Tables/Columns:** lowercase snake_case (`id_mesa`, `precio_total`).
* **Documentation:** All public service and controller methods **MUST** include JSDoc comments in English.

---

## Pull Request Process
1. Push your local feature branch to the remote VPS/GitHub repository.
2. Open a Pull Request (PR) targeting the `development` branch.
3. Ensure the code compiles locally with **Java 25** before requesting a review.
4. At least one peer review is required before merging.