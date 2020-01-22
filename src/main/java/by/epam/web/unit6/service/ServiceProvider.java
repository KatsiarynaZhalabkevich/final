package by.epam.web.unit6.service;

import by.epam.web.unit6.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private UserService userService = new UserServiceImpl();


    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

}
