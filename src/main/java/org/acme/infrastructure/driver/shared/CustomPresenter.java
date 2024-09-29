package org.acme.infrastructure.driver.shared;

public class CustomPresenter {
    private String reason;
    private Integer statusCode;
    private String message;
    private Object data;

    public CustomPresenter() {}

    public static CustomPresenter init(){
        return new CustomPresenter();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
