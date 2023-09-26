/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @apiNote JwtServiceImpl class
 * @created 01/05/2023
 * @see com.liniantt.duesclerk.backend_api.security.authentication_filter.JwtAuthenticationFilter
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.service_impl;

import com.equitytest.evelynwambui_ims_app.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

  private final Date jwtExpiratuionDate =
      new Date(System.currentTimeMillis() + 1000 * 60 * 24); // 24 hours plus E3 milliseconds

  @Value("${jwt.token.key}")
  private String JWT_SECRET_KEY;

  /**
   * Method to check if JWT token is valid
   *
   * @param jwtToken - JWT token
   * @param userDetails - UserDetails
   * @return boolean - JWT valid/invalid
   */
  @Override
  public boolean isTokenValid(String jwtToken, UserDetails userDetails) {

    final String username = extractUsername(jwtToken);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
  }

  /**
   * Method to generate token without extra claims
   *
   * @param userDetails - UserDetails
   * @return String
   */
  @Override
  public String generateToken(@NonNull UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  /**
   * Method to generate token JWT token for system user
   *
   * @param extraClaims - Extra claims
   * @param userDetails - UserDetails
   * @return String
   */
  @Override
  public String generateToken(
      @NonNull Map<String, Object> extraClaims, @NonNull UserDetails userDetails) {

    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername()) // Should be username
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(jwtExpiratuionDate)
        .signWith(getSigningKey(), SignatureAlgorithm.HS512)
        .compact();
  }

  /**
   * Method to generate token without extra claims
   *
   * @param regularUserDetails - UserDetails
   * @return String
   */
  @Override
  public String generateToken(@NonNull RegularUserDetails regularUserDetails) {
    return generateToken(new HashMap<>(), regularUserDetails);
  }

  /**
   * Method to generate token JWT token for regular user
   *
   * @param extraClaims - Extra claims
   * @param userDetails - UserDetails
   * @return String
   */
  @Override
  public String generateToken(
      @NonNull Map<String, Object> extraClaims, @NonNull RegularUserDetails userDetails) {

    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getEmailAddress())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(jwtExpiratuionDate)
        .signWith(getSigningKey(), SignatureAlgorithm.HS512)
        .compact();
  }

  /**
   * Method to extract username from JWT token
   *
   * @param jwtToken - JWT token
   * @return String - username
   */
  @Override
  public String extractUsername(@NonNull String jwtToken) {

    return extractClaim(jwtToken, Claims::getSubject);
  }

  /**
   * Method to extract a single claim
   *
   * @param jwtToken - JWT token
   * @param claimsResolver - ClaimResolver
   * @return Generics
   */
  public <T> T extractClaim(@NonNull String jwtToken, @NonNull Function<Claims, T> claimsResolver) {

    final Claims claims = extractAllClaims(jwtToken);
    return claimsResolver.apply(claims);
  }

  /**
   * Method to check id token is expired
   *
   * @param jwtToken - JWT token
   * @return boolean
   */
  private boolean isTokenExpired(final @NonNull String jwtToken) {
    return extractClaim(jwtToken, Claims::getExpiration).before(new Date());
  }

  /**
   * Method to extract all claims from JWT token
   *
   * @param jwtToken - JWT token
   * @return Claims
   */
  private Claims extractAllClaims(String jwtToken) {
    return Jwts.parserBuilder()
        .setSigningKey(
            getSigningKey()) // Secret of size 256 to digitally sign the JWT - ascertain client is
        // valid
        .build()
        .parseClaimsJws(jwtToken)
        .getBody();
  }

  /**
   * Method to get signing key
   *
   * @return Key - signing key
   */
  private Key getSigningKey() {

    log.error("JWT Token Get {}", JWT_SECRET_KEY);

    byte[] keyBytes = Base64.getDecoder().decode(JWT_SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
