package com.springmvc.sms.excetion;

public class SSMException extends RuntimeException {
    public SSMException() {
    }

    public SSMException(String message) {
        super(message);
    }

    public SSMException(String message, Throwable cause) {
        super(message, cause);
    }

    public SSMException(Throwable cause) {
        super(cause);
    }

    private Long entityId;

    public SSMException(Long entityId) {
        super("Entity with id  " + entityId + " does not exist!");
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }
}
