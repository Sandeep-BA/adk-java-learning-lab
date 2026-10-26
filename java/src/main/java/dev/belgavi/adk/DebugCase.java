// Author: Sandeep Belgavi
package dev.belgavi.adk;

/** Small debugger entry point for the real validation boundary, without model inference. */
public final class DebugCase {
  private DebugCase() {}

  public static void main(String[] args) {
    var tools = new CaseTools();
    System.out.println("Invalid identifier: " + tools.lookupCase("../../secret"));
    System.out.println("Existing case: " + tools.lookupCase("CASE-1043"));
  }
}
