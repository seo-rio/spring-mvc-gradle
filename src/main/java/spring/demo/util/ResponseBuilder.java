package spring.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import spring.demo.common.EnumResult;
import spring.demo.common.EnumReturnType;
import spring.demo.common.ResponseBodyResultModel;

@Getter
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
            return responseEntity;
        }

        private ResponseEntity<Object> setResponse(Object body) {
            final String RESULT_CODE_NAME = "Server-Result-Code";
            final String RESULT_MESSAGE_NAME = "Server-Result-Message";

//            if(this.responseType == null || this.responseType.equals(EnumReturnType.DEFAULT)){
//
//            }

            ResponseEntity<Object> responseEntity = null;

            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add(RESULT_CODE_NAME, this.result.getCd());
            header.add(RESULT_MESSAGE_NAME, this.result.getMsg());

            try {
                if (body != null) {
                    responseEntity = new ResponseEntity<Object>(setResponseBody(body), header, this.status); //header + body
                } else {
                    responseEntity = new ResponseEntity<Object>(null, this.status); //header
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
    }

}