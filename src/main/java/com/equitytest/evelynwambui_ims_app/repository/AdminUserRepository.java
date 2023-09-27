/**
 * @apiNote class
 * @author David Kariuki
 * @version 1.0.0
 * @created 27/09/2023
 * @see
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.repository;

import com.equitytest.evelynwambui_ims_app.domain.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
  Optional<AdminUser> findByUsername(String username);
}
