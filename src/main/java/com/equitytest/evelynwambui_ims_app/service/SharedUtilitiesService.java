/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.service;

import java.time.LocalDate;

public interface SharedUtilitiesService {

  LocalDate parseToLocalDate(String dateString);
}
