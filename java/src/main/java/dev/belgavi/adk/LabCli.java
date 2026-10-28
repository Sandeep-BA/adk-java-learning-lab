// Author: Sandeep Belgavi
package dev.belgavi.adk;

public final class LabCli {
  private LabCli() {}

  public static void main(String[] args) {
    if (args.length > 0 && args[0].equals("contract")) {
      ContractDemo.main(args);
      return;
    }
    int lesson = args.length > 0 ? Integer.parseInt(args[0]) : 1;
    boolean live = args.length > 1 && args[1].equals("live");
    if (args.length > 1 && !live && !args[1].equals("offline"))
      throw new IllegalArgumentException("Mode must be offline or live.");
    String prompt = args.length > 2 ? args[2] : "Look up CASE-1042";
    System.out.println(
        live
            ? "LIVE MODEL: API usage may incur charges"
            : "OFFLINE FIXTURE: real ADK, simulated model");
    try (var session = new LabSession(LessonAgents.create(lesson, live))) {
      System.out.println(session.turn(prompt));
    } finally {
      // This process owns the Rx schedulers. Library/session code must not stop shared pools.
      io.reactivex.rxjava3.schedulers.Schedulers.shutdown();
    }
  }
}
