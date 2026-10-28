# Development instructions

Author: Sandeep Belgavi.

This is a public learner repository. Include only original example code, tests and learner documentation. Never import presenter narration, production plans, personal recordings, credentials or private repository content.

1. Read the relevant lesson and implementation. State the behavior being changed.
2. Keep Java the primary path and update the Python companion in `python/` when a shared concept changes. Lessons are released one episode at a time; do not add an unreleased lesson here.
3. Use synthetic data. Validate tool arguments before reading or changing state. Do not add arbitrary filesystem, shell or network tools.
4. Default to offline execution. Live model calls and deployment are not incidental test steps.
5. Add a test for a meaningful failure case. Run the Java tests and, for Python changes, `python -m pytest python/tests -q` and `python -m ruff check python`.
6. Keep documentation commands executable from the repository root. Update expected output when behavior changes.
7. Inspect the staged diff. Exclude secrets, absolute personal paths and production materials. Preserve third-party attribution.
8. Credit Sandeep Belgavi on original source files where comments are valid, README and package metadata.

A good scoped task: add synthetic CASE-1044, test its status and missing-record behavior, update lesson 2's lookup example in Java and Python. Do not rewrite unrelated agents or publish a site.

## Commit messages

Use one specific imperative subject, a blank line, and a concise body explaining the behavior changed, the components covered and relevant validation. Do not paste a list of prior commit messages or narrate the development history. Group related changes into one coherent commit; preserve existing published history unless explicitly asked to rewrite it.
