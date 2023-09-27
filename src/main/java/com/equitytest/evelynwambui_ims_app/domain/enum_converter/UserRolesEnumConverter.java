/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @apiNote UserRoleEnumConverter class
 * @created 25/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.enum_converter;

import com.equitytest.evelynwambui_ims_app.domain.enum_.UserType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRolesEnumConverter implements AttributeConverter<UserType, String> {

  /**
   * Method to convert entity attribute to database column
   *
   * @param attribute - Enum class to be converted
   * @return Character - short name
   */
  @Override
  public String convertToDatabaseColumn(UserType attribute) {
    return attribute.getUserRole();
  }

  /**
   * Method to convert from database column to entity attribute
   *
   * @param dbData - Character from the database column to be converted
   * @return UserRole - Enum class
   */
  @Override
  public UserType convertToEntityAttribute(String dbData) {
    return UserType.fromShortName(dbData);
  }
}
