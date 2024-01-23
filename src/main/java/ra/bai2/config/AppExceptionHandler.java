package ra.bai2.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.bai2.exception.CustomException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;

@RestControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public String handleExceptionUser(CustomException e){
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> invalidRequest(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        List<FieldError> listFieldError = ex.getBindingResult().getFieldErrors();
        listFieldError.forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return errors;
     }

}
