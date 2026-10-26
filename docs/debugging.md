# Debug the tool boundary

By Sandeep Belgavi.

## Start with a predictable input

`DebugCase` calls the same `CaseTools.lookupCase` method the agent uses. Its first input (`../../secret`) is invalid; its second is `CASE-1043`. It makes no model request and performs no external operation.

In your Java IDE, open `DebugCase.java`, place a breakpoint at the start of `lookupCase` and debug `DebugCase.main`. Inspect `caseId`, step through validation and confirm `invalid_id`. Continue to the valid lookup and confirm `resolved`.

## Use the command-line debugger

The JDK includes `jdb`. If it is not on your `PATH`, use the executable in your JDK's `bin` directory. Compile the application and write the dependency classpath to a file:

```sh
mvn -f java/pom.xml compile dependency:build-classpath "-Dmdep.outputFile=target/debug-classpath.txt"
```

Start `jdb` with a classpath containing `java/target/classes` and the contents of `java/target/debug-classpath.txt`. Use a semicolon between entries on Windows, or a colon on macOS/Linux. The main class is `dev.belgavi.adk.DebugCase`.

Enter these commands one at a time, waiting for the breakpoint or prompt after each:

```text
stop in dev.belgavi.adk.CaseTools.lookupCase
run
locals
where
next
clear dev.belgavi.adk.CaseTools.lookupCase
cont
```

Expected: the breakpoint is hit in `lookupCase`, `locals` shows `caseId` equal to `../../secret`, and the final output contains `invalid_id` and `resolved`. This is tool-boundary debugging, not a full investigation of the asynchronous agent runner.
