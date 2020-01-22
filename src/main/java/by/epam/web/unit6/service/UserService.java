package by.epam.web.unit6.service;

import by.epam.web.unit6.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User authorization (String login, String password) throws ServiceException;
    boolean saveUser (User user) throws ServiceException;
    User getUserByLogin (String login) throws ServiceException;
    List<User> getUsers() throws ServiceException;
    boolean editProfile(User user) throws  ServiceException;
    boolean deleteUser (int id) throws ServiceException;
    boolean changePassword (int id, String newPassword) throws ServiceException;


}
