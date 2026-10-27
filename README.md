# Belgavi's Lab

**By Sandeep Belgavi.** Build and understand a Support Desk Agent with Google ADK Java, one capability per episode of [Belgavi's AI Lab](https://www.youtube.com/@belgaviai) on YouTube.

This repository grows with the series: each episode adds its lesson here when the video is released. Every lesson has a command, expected result, code walkthrough, test and exercise. The default examples require no model account or API key.

## 1. Check your tools

Install **JDK 17+**, **Maven 3.9+** and Git.

```sh
java -version
mvn -version
git --version
```

Maven must report the intended JDK. If it reports an older Java version, fix `JAVA_HOME` before continuing.

## 2. Get the project

```sh
git clone https://github.com/Sandeep-BA/belgavis-lab.git
cd belgavis-lab
```

Run all commands below from this directory. No framework checkout or local SDK build is needed. Maven downloads the pinned `com.google.adk:google-adk:1.12.0` dependency.

## 3. Build and test

```sh
mvn -B -ntp -f java/pom.xml test
```

Expected: `BUILD SUCCESS`. The suite exercises actual ADK runners and tools with a deterministic model fixture. It also checks invalid identifiers and missing records. Initial dependency downloads need internet access. Offline model execution does not mean dependency installation works without a network.

## 4. Run your first agent

```sh
mvn -f java/pom.xml exec:java -Dexec.args="1 offline"
```

Expected: `OFFLINE FIXTURE: real ADK, simulated model`, followed by an `Offline hello` response. The model is simulated; ADK orchestration is real. This proves wiring, not live-model intelligence.

## 5. Follow the lessons

| Episode | What you build | Start here |
| --- | --- | --- |
| 1 | Your first agent, and how ADK runs it | [Your first agent](docs/01-first-agent.md) |

Episode 1 also runs the ticket lookup used in the video:

```sh
mvn -f java/pom.xml exec:java -Dexec.args="2 offline"
mvn -f java/pom.xml exec:java "-Dexec.args=9 offline 'Look up SUP-9999'"
```

**Coming next in the series:** function tools (how the model calls your code), sessions, state, guardrails, and workflows that chain lookup, policy and reply. Each lesson lands here with its episode. Watch or subscribe at [youtube.com/@belgaviai](https://www.youtube.com/@belgaviai).

Prefer a browser? Open [LEARN.html](LEARN.html) locally for the same guides, navigation and architecture diagram.

## 6. Understand the boundaries

- Only synthetic `SUP-1042` and `SUP-1043` records exist. No external business system is contacted by the tools.
- Sessions are in memory and use a fixed demo identity. This is not a hosted authentication or persistence implementation.
- A live model is optional and may incur charges. See [live setup](docs/live-mode.md) before enabling it.
- [Debugging](docs/debugging.md) provides a real breakpoint walkthrough for the tool boundary.
- [Troubleshooting](docs/troubleshooting.md) explains common setup and SDK warnings.
- [Architecture](docs/architecture.md) explains the code layout and execution flow.
- [References](docs/references.md) maps the lessons to official docs, the pinned Google ADK source and relevant AICassindra articles.

## Watch the series

Every lesson has a video on [Belgavi's AI Lab](https://www.youtube.com/@belgaviai). [Subscribe](https://www.youtube.com/@belgaviai?sub_confirmation=1) to get each episode as it lands; its lesson arrives here on the same day.

| Episode | Topic | Lesson | Video |
| --- | --- | --- | --- |
| 1 | What an AI agent really is, and how ADK runs one | [Your first agent](docs/01-first-agent.md) | coming soon |
| 2 | Function tools with explicit contracts | with the episode | coming soon |
| 3 | Sessions and multi-turn conversations | with the episode | coming soon |
| 4 | Explicit state without cross-session leaks | with the episode | coming soon |
| 5 | Block a tool call outside the prompt | with the episode | coming soon |
| 6 | A sequential workflow with a visible handoff | with the episode | coming soon |
| 7 | Parallel agents without shared-key collisions | with the episode | coming soon |
| 8 | Bound your loops and your model calls | with the episode | coming soon |
| 9 | Evaluate behavior, not polished answers | with the episode | coming soon |
| 10 | Package an agent lab for repeatable development | with the episode | coming soon |

When an episode is published, fill its Video cell with `python scripts/set_video_link.py <episode> <youtube-url-or-id>` (for example `python scripts/set_video_link.py 1 https://youtu.be/VIDEO_ID`). Link the lesson in the same commit when it is released.

## Reference projects and attribution

[google/adk-java](https://github.com/google/adk-java) is the upstream framework. This application uses its released Maven artifact; the [pinned source map](docs/references.md) links the matching source tag for reading.

Original examples and learner documentation are by Sandeep Belgavi, licensed under [MIT](LICENSE). ADK and other dependencies retain their own licenses. No framework source is vendored. This independent educational project is not an official Google course. Views are my own and do not represent any employer, past or present.

## Contributing with a coding agent

Read [AGENTS.md](AGENTS.md). Make one bounded change, add a meaningful assertion and run the existing checks. Edit `docs/` and regenerate the HTML with `python scripts/build_docs.py`. CI checks the Java build and the documentation. No workflow publishes a website or video.

Install the documentation renderer with `python -m pip install -r scripts/requirements-docs.txt`. After regenerating, run `python scripts/build_docs.py --check` to check generated content and local links.
