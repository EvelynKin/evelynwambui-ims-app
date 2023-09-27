/**
 * @apiNote class
 * @author David Kariuki
 * @version 1.0.0
 * @created 27/09/2023
 * @see
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.service_impl;

import com.equitytest.evelynwambui_ims_app.service.SharedUtilitiesService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SharedUtilitiesServiceImpl implements SharedUtilitiesService {

  @Override
  public LocalDate parseToLocalDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return LocalDate.parse(dateString, formatter);
  }
}
