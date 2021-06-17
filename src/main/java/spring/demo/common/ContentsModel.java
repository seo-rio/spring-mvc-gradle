package spring.demo.common;

public class ContentsModel {

    private Object contents;

    public ContentsModel(Object contents) {
        this.contents = contents;
    }

    public Object getContents() {
        return contents;
    }

    public void setContents(Object contents) {
        this.contents = contents;
    }
}
