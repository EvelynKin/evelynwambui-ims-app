/**
 * @author David Kariuki
 * @version 1.0.0
 * @apiNote GlobalControllerExceptionHandler class
 * @created 25/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.exception;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

  /**
   * Handles ConversionFailedException runtime exceptions
   *
   * @param runtimeException the runtime exception to be handled
   * @return a ResponseEntity with a String response and HTTP status code 400 (Bad Request)
   */
  @Operation(summary = "Handle ConversionFailedException")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            }),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @ExceptionHandler(ConversionFailedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleConversion(RuntimeException runtimeException) {
    return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
