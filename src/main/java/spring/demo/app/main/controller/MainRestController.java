package spring.demo.app.main.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.common.EnumResult;
import spring.demo.util.DemoHeaders;
import spring.demo.util.ResponseBuilder;

@Slf4j
@RestController
public class MainRestController {

    @ApiOperation(value = "TEST1 API", notes = "TEST1 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = String.class),
            @ApiResponse(code = 500, message = "알수없는 에러")
    })
    @GetMapping(value = "/api/test")
    public ResponseEntity<Object> test() {
        log.debug("\t\t== TEST1 API IN ==\t\t");
        return new ResponseBuilder.Builder(HttpStatus.OK, EnumResult.SUCCESS).body("TEST1").build();
    }

    @ApiOperation(value = "TEST2 API", notes = "TEST2 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = String.class),
            @ApiResponse(code = 500, message = "알수없는 에러")
    })
    @GetMapping(value = "/api/test2")
    public ResponseEntity<String> test2() {
        log.debug("\t\t== TEST2 API IN ==\t\t");
        return ResponseEntity.ok().headers(new DemoHeaders(EnumResult.SUCCESS, MediaType.APPLICATION_JSON_VALUE)).body("TEST");
    }
}
