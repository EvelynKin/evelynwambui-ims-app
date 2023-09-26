/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 25/09/2023
 * @apiNote JwtService class
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.service;

import io.jsonwebtoken.Claims;
import java.util.Map;
import java.util.function.Function;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

  /**
   * Method to check if JWT token is valid
   *
   * @param jwtToken - JWT token
   * @param userDetails - UserDetails
   * @return boolean - JWT valid/invalid
   */
  boolean isTokenValid(String jwtToken, UserDetails userDetails);

  /**
   * Method to generate token without extra claims
   *
   * @param userDetails - UserDetails
   * @return String
   */
  String generateToken(@NonNull UserDetails userDetails);

  /**
   * Method to generate token JWT token
   *
   * @param extraClaims - Extra claims
   * @param userDetails - UserDetails
   * @return String
   */
  String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

  /**
   * Method to generate token without extra claims
   *
   * @param regularUserDetails - RegularUserDetails
   * @return String
   */
  //String generateToken(@NonNull RegularUserDetails regularUserDetails);

  /**
   * Method to generate token JWT token for regular user
   *
   * @param extraClaims - Extra claims
   * @param userDetails - UserDetails
   * @return String
   */
  //String generateToken(@NonNull Map<String, Object> extraClaims, @NonNull RegularUserDetails userDetails);

  /**
   * Method to extract username from JWT token
   *
   * @param jwtToken - JWT token
   * @return String - username
   */
  String extractUsername(@NonNull String jwtToken);

  /**
   * Method to extract a single claim
   *
   * @param jwtToken - JWT token
   * @param claimsResolver - ClaimResolver
   * @return Generics
   */
  <T> T extractClaim(@NonNull String jwtToken, @NonNull Function<Claims, T> claimsResolver);
}
