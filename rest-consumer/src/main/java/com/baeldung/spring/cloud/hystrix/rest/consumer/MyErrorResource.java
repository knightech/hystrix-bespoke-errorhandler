package com.baeldung.spring.cloud.hystrix.rest.consumer;

public class MyErrorResource {

    private String httpStatus;
    private ErrorItem[] errorItems;

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ErrorItem[] getErrorItems() {
        return errorItems;
    }

    public void setErrorItems(ErrorItem[] errorItems) {
        this.errorItems = errorItems;
    }

    public MyErrorResource() {
    }

    public MyErrorResource(String httpStatus, ErrorItem[] errorItems) {
        this.httpStatus = httpStatus;
        this.errorItems = errorItems;
    }

    static class ErrorItem {
        private String errorCode;
        private String message;
        private String developerMessage;

        public ErrorItem() {
        }

        public ErrorItem(String errorCode, String message, String developerMessage) {
            this.errorCode = errorCode;
            this.message = message;
            this.developerMessage = developerMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

        public void setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
        }
    }


}
