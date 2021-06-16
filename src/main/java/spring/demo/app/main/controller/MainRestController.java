package spring.demo.app.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.common.EnumResult;
import spring.demo.util.ResponseBuilder;

@RestController
@Slf4j
public class MainRestController {

    @GetMapping(value = "/api/test")
    public ResponseEntity<Object> test() {
        log.debug("\t\t== TEST API IN ==\t\t");
        return new ResponseBuilder.Builder(HttpStatus.OK, EnumResult.SUCCESS).body("TEST").build();
    }

    @GetMapping(value = "/api/test2")
    public ResponseEntity<Object> test2() {
        log.debug("\t\t== TEST API IN ==\t\t");
        return new ResponseEntity<>("TEST", HttpStatus.OK);
    }

}
