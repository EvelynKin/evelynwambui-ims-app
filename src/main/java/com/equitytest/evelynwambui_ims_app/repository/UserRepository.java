package com.equitytest.evelynwambui_ims_app.repository;

import com.equitytest.evelynwambui_ims_app.domain.entity.AdminUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.RegularUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.SystemUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @apiNote class
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Repository
  public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {}

  @Repository
  public interface RegularUserRepository extends JpaRepository<RegularUser, Long> {}

  @Repository
  public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {}
}
