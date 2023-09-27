/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.equitytest.evelynwambui_ims_app.service_impl.SharedUtilitiesServiceImpl;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SharedUtilitiesServiceImplTest {

  private SharedUtilitiesServiceImpl sharedUtilitiesService;

  @BeforeEach
  public void setUp() {
    sharedUtilitiesService = new SharedUtilitiesServiceImpl();
  }

  @Test
  public void testParseToLocalDate_ValidDate() {
    String dateString = "15/09/2023";

    LocalDate result = sharedUtilitiesService.parseToLocalDate(dateString);

    assertEquals(2023, result.getYear());
    assertEquals(9, result.getMonthValue());
    assertEquals(15, result.getDayOfMonth());
  }

  @Test
  public void testParseToLocalDate_InvalidDate() {
    String dateString = "31/02/2023"; // Invalid date

    try {
      LocalDate result = sharedUtilitiesService.parseToLocalDate(dateString);
    } catch (DateTimeParseException e) {
      // DateTimeParseException should be thrown for invalid date format
      assertEquals("Text '31/02/2023' could not be parsed: Invalid date 'FEBRUARY 31'", e.getMessage());
    }
  }
}
