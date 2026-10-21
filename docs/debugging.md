# Debug the tool boundary

By Sandeep Belgavi.

## Start with a predictable input

`DebugTicket` calls the same `TicketTools.lookupTicket` method used by the agent. Its first input is invalid; its second is SUP-1043. It makes no model request and performs no external operation.

In your Java IDE, open `DebugTicket.java`, place a breakpoint at the start of `lookupTicket` and debug `DebugTicket.main`. Inspect `ticketId`, step through validation and confirm `invalid_id`. Continue to the valid lookup and confirm `resolved`.

## Use the command-line debugger

The JDK includes `jdb`. If it is not on PATH, use the executable in your JDK's `bin` directory. Build the application and dependency classpath:

```sh
mvn -f java/pom.xml compile dependency:build-classpath "-Dmdep.outputFile=target/debug-classpath.txt"
```

Start `jdb` with a classpath containing `java/target/classes` and the contents of `java/target/debug-classpath.txt`. Use a semicolon between entries on Windows, or a colon on macOS/Linux. The main class is `dev.belgavi.adk.DebugTicket`.

Enter these commands one at a time, waiting for the breakpoint or prompt after each:

```text
stop in dev.belgavi.adk.TicketTools.lookupTicket
run
locals
where
next
clear dev.belgavi.adk.TicketTools.lookupTicket
cont
```

Expected: a real breakpoint in `lookupTicket`, a local `ticketId` equal to `../../secret`, and final output containing `invalid_id` and `resolved`. This is tool-boundary debugging, not a full investigation of the asynchronous agent runner.
