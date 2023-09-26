/**
 * @apiNote class
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.repository;

import com.equitytest.evelynwambui_ims_app.domain.entity.AdminUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.RegularUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.SystemUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Repository
  interface AdminUserRepository extends JpaRepository<AdminUser, Long> {}

  @Repository
  interface RegularUserRepository extends JpaRepository<RegularUser, Long> {}

  @Repository
  interface SystemUserRepository extends JpaRepository<SystemUser, Long> {}
}
