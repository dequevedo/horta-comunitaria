package com.community.farm.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CommunityFarmException.class)
    public ResponseEntity<ResponseAdvice> handleCommunityFarmException(CommunityFarmException exception) {

        ResponseAdvice body = new ResponseAdvice();
        body.setCode(exception.getMessage());
        body.setDescription(exception.getCause().getMessage());

        return ResponseEntity.status(Integer.parseInt(exception.getMessage())).body(body);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("statusCode", HttpStatus.CONFLICT.value());
        body.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        return new ResponseEntity<>(body, status);
    }

}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ResponseAdvice {
    private String code;
    private String description;
}

