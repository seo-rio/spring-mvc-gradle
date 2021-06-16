package spring.demo.common;

public enum EnumReturnType {

    DEFAULT("DEFAULT JSON");

    private final String value;

    EnumReturnType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
