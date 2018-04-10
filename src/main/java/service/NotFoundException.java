package service;

public class NotFoundException extends RuntimeException {
    public NotFoundException (String exception) {
        super(exception);
    }
}
