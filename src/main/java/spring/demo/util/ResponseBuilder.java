package spring.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import spring.demo.common.EnumResult;
import spring.demo.common.EnumReturnType;
import spring.demo.common.DefaultResultModel;
import spring.demo.common.GridResultModel;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Getter
@Slf4j
public class ResponseBuilder {

    private final ResponseEntity<Object> responseEntity;

    public ResponseBuilder(Builder builder) {
        responseEntity = builder.responseEntity;
    }

    public static class Builder {
        private ResponseEntity<Object> responseEntity;
        private EnumReturnType responseType;

        private final HttpStatus status;
        private final EnumResult result;

        public Builder(HttpStatus status, EnumResult result) {
            this.status = status;
            this.result = result;
        }

        public Builder body(Object obj) {
            this.responseEntity = setResponse(obj);
            return this;
        }

        public Builder responseType(EnumReturnType value) {
            this.responseType = value;
            return this;
        }

        public ResponseEntity<Object> build() {
            return responseEntity == null ? setResponse(null) : responseEntity;
        }

        private ResponseEntity<Object> setResponse(Object body) {
            final String RESULT_CODE_NAME = "Server-Result-Code";
            final String RESULT_MESSAGE_NAME = "Server-Result-Message";

            if (this.responseType == null) {
                this.responseType = EnumReturnType.DEFAULT;
            }
            Object bodyObject = null;

            switch (this.responseType) {
                case GRID:
                    bodyObject = new GridResultModel(body, true);
                    break;
                default:
                    bodyObject = new DefaultResultModel(body);
            }

            ResponseEntity<Object> responseEntity = null;

            Map<String, String> header = new HashMap<>();
            header.put(RESULT_CODE_NAME, this.result.getCd());
            header.put(RESULT_MESSAGE_NAME, this.result.getMsg());

            try {
                if (body != null) {
                    responseEntity = new ResponseEntity<Object>(setResponseBody(bodyObject), setHeader(header), this.status); //header + body
                } else {
                    responseEntity = new ResponseEntity<Object>(setHeader(header), this.status); //header
                }
            } catch (Exception e) {
                responseEntity = new ResponseEntity<>(EnumResult.FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return responseEntity;
        }

        private static String setResponseBody(Object body) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            return String.valueOf(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body));
        }

        private HttpHeaders setHeader(Map<String, String> map) {

            HttpHeaders headers = new HttpHeaders();

            final Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("charset", StandardCharsets.UTF_8.name());
            headers.setContentType(new MediaType("application", "json", parameterMap));

            for (String key : map.keySet()) {
                headers.set(key, String.valueOf(map.get(key)));
            }
            return headers;
        }

    }

}