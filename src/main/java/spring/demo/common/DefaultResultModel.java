package spring.demo.common;

import lombok.Data;

@Data
public class DefaultResultModel {

    private Object data;

    public DefaultResultModel(Object data) {
        this.data = data;
    }
}
