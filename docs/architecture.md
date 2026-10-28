# Architecture and code map

By Sandeep Belgavi.

## A deliberately small application

The example answers questions about two synthetic help-desk cases. Tool behavior is ordinary application code. The framework coordinates model responses, tool invocation and session events. Keeping those boundaries separate lets us test failures without depending on live inference.

```text
User message
    ↓
LabCli → LessonAgents.create(lesson, mode)
    ↓
LabSession → InMemoryRunner → session and event stream
                                ↕
                         LlmAgent
                                ↕
                     model response or tool call
                                ↓
                     validated CaseTools
```

## Java responsibilities

- `LabCli.java`: selects the lesson and mode, prints responses and shuts down the process-owned Rx schedulers on exit.
- `LessonAgents.java`: constructs the agents for the lessons released so far. It is the configuration map, not a persistence layer.
- `OfflineModel.java`: supplies deterministic responses and function calls for repeatable tests. It does not perform inference.
- `CaseTools.java`: validates synthetic IDs and looks up records.
- `LabSession.java`: owns a runner and one session, limits model calls and collects final responses.
- `LabTest.java`: checks integration behavior and boundaries through the real SDK.

All Java sources are in `java/src/main/java/dev/belgavi/adk/`, with tests in the matching test package.

## What each boundary guarantees

Tool validation checks the synthetic ID shape and record existence, and a missing record comes back as data (`not_found`), not an exception. The tests assert these narrow properties.

The application does not authenticate users, persist sessions, export records, serve HTTP traffic or enforce a comprehensive security policy. Its fixed demo identity must not be reused as production authorization.

## Bounds and lifecycle

Each turn allows at most twelve model calls. The 90-second Rx timeout measures the gap between events, not the whole turn.

`LabSession.close` releases runner resources. Only the Java command-line entry point shuts down the global Rx schedulers, because it owns the process. Reusable library code must not stop thread pools used by another caller.
