package spring.demo.common;

import lombok.Data;

@Data
public class GridResultModel {

    private boolean result;

    private Object data;

    public GridResultModel(Object data, boolean result) {
        this.data = (ContentsModel) data;
        this.result = result;
    }
}
