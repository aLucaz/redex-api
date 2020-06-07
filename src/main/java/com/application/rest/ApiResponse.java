package com.application.rest;

import com.application.shared.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
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

    public ResponseEntity<Object> ok(T payload){
        ApiResponse<T> apiResponse = new ApiResponse<>(OK, "Successfull operation");
        apiResponse.setPayload(payload);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
