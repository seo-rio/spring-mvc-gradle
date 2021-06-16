package spring.demo.common;

import lombok.Data;

@Data
public class ResponseBodyResultModel {

    private Object data;

    public ResponseBodyResultModel(Object data) {
        this.data = data;
    }
}
