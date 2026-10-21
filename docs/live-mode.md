# Optional live-model setup

By Sandeep Belgavi.

## Prerequisites

Complete the offline build first. You need your own Google model access, an available Gemini model identifier and an API key. Model availability and charges depend on your account. The repository does not supply credentials or create accounts.

## Set configuration securely

Set `GOOGLE_API_KEY` and `GOOGLE_MODEL` in the environment used to launch the application. Use your secret manager or a private terminal session. Do not commit values, paste them into issue reports or display them in a screen recording.

`.env.example` documents the names only. The CLI does not automatically read `.env` files. `GOOGLE_MODEL` is required so a model choice is explicit rather than silently relying on an alias.

## Run the narrowest live lesson

```sh
mvn -f java/pom.xml exec:java -Dexec.args="2 live"
```

The terminal prints a LIVE MODEL label. The application sends the instruction, prompt and tool declarations to the configured provider. The tools still use synthetic data locally.

## Validate the result

For SUP-1042, check that the response agrees with the fixture's open status. For SUP-9999, check that it reports absence instead of inventing data. Record the model identifier and SDK version with any evaluation results. Do not publish credentials or full private request logs.

Live inference was not executed as part of the initial automated repository checks. A passing offline suite does not establish provider connectivity, tool-selection accuracy or injection resistance. A provider error should remain visible instead of being silently replaced by a fixture result.

## Stop and recover

Use Ctrl+C if a manual run is taking too long. Review any provider usage separately. In-memory state disappears when the process exits. For hosted usage, implement authenticated sessions, durable storage, explicit deadlines and cancellation, and a task-specific evaluation suite.
