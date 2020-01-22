package by.epam.web.unit6.dao;

import by.epam.web.unit6.dao.impl.SQLUserDAO;

public class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();
    private final UserDAO userDao = new SQLUserDAO();

    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDao() {
        return userDao;
    }


}
