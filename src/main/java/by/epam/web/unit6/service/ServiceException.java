package by.epam.web.unit6.service;

public class ServiceException extends Exception {
    public ServiceException(Exception e) {
        super(e);
    }
    public ServiceException(String message) {

    }


}
