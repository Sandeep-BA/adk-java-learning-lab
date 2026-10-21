// Author: Sandeep Belgavi
package dev.belgavi.adk;

/** Small debugger entry point for the real validation boundary, without model inference. */
public final class DebugTicket {
  private DebugTicket() {}

  public static void main(String[] args) {
    var tools = new TicketTools();
    System.out.println("Invalid identifier: " + tools.lookupTicket("../../secret"));
    System.out.println("Existing ticket: " + tools.lookupTicket("SUP-1043"));
  }
}
