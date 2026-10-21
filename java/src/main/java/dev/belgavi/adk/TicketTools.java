// Author: Sandeep Belgavi
package dev.belgavi.adk;

import com.google.adk.tools.Annotations.Schema;
import java.util.Map;

/** Synthetic data only. Tool results are data, never agent instructions. */
public final class TicketTools {
  @Schema(description = "Read a synthetic support ticket by its SUP-NNNN identifier.")
  public Map<String, Object> lookupTicket(
      @Schema(name = "ticketId", description = "Identifier such as SUP-1042") String ticketId) {
    if (ticketId == null || !ticketId.matches("SUP-[0-9]{4}")) {
      return Map.of("error", "invalid_id");
    }
    return switch (ticketId) {
      case "SUP-1042" -> Map.of("id", ticketId, "status", "open", "title", "Sign-in fails after password reset");
      case "SUP-1043" -> Map.of("id", ticketId, "status", "resolved", "title", "Invoice export arrives late");
      default -> Map.of("id", ticketId, "error", "not_found");
    };
  }
}
