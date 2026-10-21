# 1. Your first ADK Java agent

By Sandeep Belgavi.

**Outcome:** run a request through an agent, model adapter, runner and session, then explain what each component owns.

## Before you start

Complete README steps 1–3. The first run may download Maven dependencies. No model key is needed for this lesson.

## Step 1: Run the smallest configuration

```sh
mvn -f java/pom.xml exec:java -Dexec.args="1 offline"
```

Look for the offline label and `Offline hello`. The exact echoed instruction is less important than knowing where it came from. The fixture produces the answer; a live Gemini model is not contacted.

## Step 2: Read the agent definition

Open `java/src/main/java/dev/belgavi/adk/LessonAgents.java`. In `create`, lesson 1 constructs an `LlmAgent` with a name, role instruction, model and output key. There are no tools yet.

The model adapter is selected in the helper `agent` method. Offline mode constructs `OfflineModel`; live mode accepts a configured model name. Keep that decision explicit when comparing results.

## Step 3: Follow a turn

Open `LabSession.java`. Its constructor creates an `InMemoryRunner` and one session. Its `turn` method creates a user message, calls `runAsync` and collects final responses. `RunConfig` limits model calls. `close` releases the runner.

```text
LabCli → LessonAgents → LabSession
                           ↓
                       ADK runner
                           ↕
                      OfflineModel
                           ↓
                      final response
```

`OfflineModel` extends ADK's model abstraction. It is deliberately deterministic, so tests can exercise orchestration without a provider account. It is not an alternative language model.

## Step 4: Make one change

Change the lesson 1 instruction to ask for a different one-sentence role. Run again. The fixture echoes the instruction, demonstrating that the configuration reaches the model boundary. Do not interpret the echo as successful reasoning.

## Step 5: Check your understanding

```sh
mvn -f java/pom.xml test "-Dtest=LabTest#everyReleasedLessonRunsThroughSdk"
```

Explain these distinctions: the agent defines behavior, the runner executes it, the session carries context and the model supplies responses. The test covers the configured offline execution path, not live answer quality.

## Read next

Episode 1 of Belgavi's AI Lab also runs the ticket lookup, so you can try it now:

```sh
mvn -f java/pom.xml exec:java -Dexec.args="2 offline"
mvn -f java/pom.xml exec:java "-Dexec.args=9 offline 'Look up SUP-9999'"
```

The first finds SUP-1042 (open); the second shows a missing ticket returned as data, `not_found`, instead of an invented status. Lesson 2, how function tools really work, arrives with Episode 2.

For background, read [AICassindra's core-module overview](https://aicassindra.com/blogs/adk_java/adk_java_core_module_overview.html). Treat article snippets as conceptual material; the pinned examples here are the executable reference for this course.
