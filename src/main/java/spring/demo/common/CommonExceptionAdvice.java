package spring.demo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import spring.demo.util.ResponseBuilder;

@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    private ResponseEntity<Object> responseBody = null;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> commonException(Exception e) {
        log.debug(" \t\t== Common Exception ! ==\t\t");
        log.error("", e);
        responseBody = new ResponseBuilder.Builder(HttpStatus.BAD_REQUEST, EnumResult.FAIL).build();
        return responseBody;
    }

}
