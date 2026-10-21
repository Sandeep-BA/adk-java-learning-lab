// Author: Sandeep Belgavi
package dev.belgavi.adk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LabTest {
  @Test
  void validatesIdentifiersAndMissingTickets() {
    var tools = new TicketTools();
    assertEquals("invalid_id", tools.lookupTicket("../../secret").get("error"));
    assertEquals("not_found", tools.lookupTicket("SUP-9999").get("error"));
    assertEquals("resolved", tools.lookupTicket("SUP-1043").get("status"));
  }

  @Test
  void everyReleasedLessonRunsThroughSdk() {
    for (int lesson : LessonAgents.RELEASED) {
      try (var session = new LabSession(LessonAgents.create(lesson, false))) {
        assertFalse(session.turn("Look up SUP-1042").isBlank(), "lesson " + lesson);
      }
    }
  }

  @Test
  void lookupAnswersFromTheTicketRecord() {
    try (var session = new LabSession(LessonAgents.create(2, false))) {
      String answer = session.turn("Is SUP-1042 fixed?");
      assertTrue(answer.contains("open") && answer.contains("SUP-1042"), answer);
    }
  }

  @Test
  void missingTicketSurvivesRunner() {
    try (var session = new LabSession(LessonAgents.create(9, false))) {
      assertTrue(session.turn("Look up SUP-9999").contains("not_found"));
    }
  }

  @Test
  void unreleasedLessonsSayWhenTheyArrive() {
    var error = assertThrows(IllegalArgumentException.class, () -> LessonAgents.create(3, false));
    assertTrue(error.getMessage().contains("later episode"));
  }
}
