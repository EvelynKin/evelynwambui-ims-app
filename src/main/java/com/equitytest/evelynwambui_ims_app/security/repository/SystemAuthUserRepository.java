/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 25/09/2023
 * @apiNote UserAuthenticationRepository interface class
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAuthUserRepository extends JpaRepository<SystemAuthUser, Integer> {

  /**
   * Method to find user by username
   *
   * @param username - User's username
   * @return Optional<User
   */
  Optional<SystemAuthUser> findByUsername(final @NonNull String username);
}
