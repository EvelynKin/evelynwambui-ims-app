/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.service;

import com.equitytest.evelynwambui_ims_app.dto.input.UserManagementRequest;
import com.equitytest.evelynwambui_ims_app.dto.output.RequestResponse;
import jakarta.validation.Valid;

public interface UserService {

  RequestResponse registerUser(@Valid UserManagementRequest userManagementRequest);

  RequestResponse loginUser(@Valid UserManagementRequest userManagementRequest);

  RequestResponse logoutUser(@Valid UserManagementRequest userManagementRequest);
}
