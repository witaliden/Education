package com.wd.education.api.annotation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "204",
        description = "No content"
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Bad request"
    ),
    @ApiResponse(
        responseCode = "401",
        description = "Unauthorized"
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Internal server error"
    )
})

@ResponseStatus(HttpStatus.NO_CONTENT)
public @interface ApiResponseWithoutContent {

}
