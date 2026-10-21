# References and reading order

By Sandeep Belgavi. Links checked 2026-10-19.

## Primary SDK references

- [Official Java quickstart](https://adk.dev/get-started/java/): setup, agent construction and running a first application.
- [Official function-tool guide](https://adk.dev/tools-custom/function-tools/): tool declarations and injected context.
- [Google ADK Java releases](https://github.com/google/adk-java/releases): released Java versions.

## The requested redBus reference

[redbus-labs/adk-java](https://github.com/redbus-labs/adk-java) is a public fork of Google's Java ADK, licensed Apache-2.0. We inspected its README, tutorial layout and source references at commit `f0f883783c8567ade440ba6e449cb1577b28c5ec`. These are reference links, not framework code copied into this application.

- [Pinned repository overview](https://github.com/redbus-labs/adk-java/tree/f0f883783c8567ade440ba6e449cb1577b28c5ec): browse modules without depending on a moving branch.
- [City/time/weather tutorial](https://github.com/redbus-labs/adk-java/tree/f0f883783c8567ade440ba6e449cb1577b28c5ec/tutorials/city-time-weather): another small application to study after lesson 1.
- [FunctionTool implementation](https://github.com/redbus-labs/adk-java/blob/f0f883783c8567ade440ba6e449cb1577b28c5ec/core/src/main/java/com/google/adk/tools/FunctionTool.java): read how function methods are exposed to the runtime.
- [Fork license](https://github.com/redbus-labs/adk-java/blob/f0f883783c8567ade440ba6e449cb1577b28c5ec/LICENSE): framework licensing, separate from the MIT license on our original examples.

The fork's README version and our Maven artifact version (`com.google.adk:google-adk:1.12.0`) differ. Check the API against the pinned release before adapting a fork-specific example.

The linked city/time/weather source illustrates `LlmAgent.builder`, `FunctionTool.create` and annotated parameters. Its weather response is a hard-coded demonstration, not a live weather service. Our support-ticket examples apply those framework concepts to original synthetic data and add repeatable tests; they do not copy that tutorial's implementation.

## AICassindra reading map

Read the executable lesson first, then the associated article for broader context. The links below resolved successfully when checked. Articles can evolve independently of this release.

- **Lesson 1:** [ADK Java core module](https://aicassindra.com/blogs/adk_java/adk_java_core_module_overview.html). Use it to place the agent, runner and session within the framework.

AICassindra material is by Sandeep Belgavi. It is linked rather than republished. Exact SDK code should be taken from the tested source here and the pinned upstream release, not assumed from conceptual article snippets. No private AICassindra files or local editorial URLs are required by the learner project.

Reading for the next lessons is added as each episode is released.
