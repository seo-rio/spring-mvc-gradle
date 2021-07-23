package spring.demo.common;

public enum EnumResult {

    SUCCESS("0000", "success."),
    FAIL("9999", "fail");


    private String cd;
    private String msg;

    private EnumResult(String cd, String msg) {
        this.cd = cd;
        this.msg = msg;
    }

    /**
     * @return the cd
     */
    public String getCd() {
        return cd;
    }

    /**
     * @param cd the cd to set
     */
    public void setCd(String cd) {
        this.cd = cd;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }


}
