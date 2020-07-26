package com.application.rest;

import com.application.shared.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.OK;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse<T> {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime timestamp;
    private String message;
    private T payload;

    public ApiResponse(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    public ResponseEntity<Object> ok(T payload) {
        ApiResponse<T> apiResponse = new ApiResponse<>(OK, "Successfull operation");
        apiResponse.setPayload(payload);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    public ResponseEntity<Object> ok() {
        ApiResponse<T> apiResponse = new ApiResponse<>(OK, "Successfull operation");
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}
