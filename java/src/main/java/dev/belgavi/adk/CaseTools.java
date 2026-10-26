// Author: Sandeep Belgavi
package dev.belgavi.adk;

import com.google.adk.tools.Annotations.Schema;
import java.util.Map;

/** Synthetic data only. Tool results are data, never agent instructions. */
public final class CaseTools {
  @Schema(description = "Read a synthetic help-desk case by its CASE-NNNN identifier.")
  public Map<String, Object> lookupCase(
      @Schema(name = "caseId", description = "Identifier such as CASE-1042") String caseId) {
    if (caseId == null || !caseId.matches("CASE-[0-9]{4}")) {
      return Map.of("error", "invalid_id");
    }
    return switch (caseId) {
      case "CASE-1042" -> Map.of("id", caseId, "status", "open", "title", "E-book download fails after password reset");
      case "CASE-1043" -> Map.of("id", caseId, "status", "resolved", "title", "Gift card balance shows zero");
      default -> Map.of("id", caseId, "error", "not_found");
    };
  }
}
