package by.epam.web.unit6.dao;

import by.epam.web.unit6.bean.User;

import java.util.List;

public interface UserDAO {

    User authorize(String login, String password) throws DAOException;
    boolean create(User user) throws DAOException;
    User  takeById(int id) throws DAOException;
    User takeByLogin (String login) throws DAOException;
    List<User> takeByName(String name) throws DAOException;
    User takeByEmail (String email) throws DAOException;
    List<User> takeAll() throws DAOException;
    boolean updateInfo (User user) throws DAOException;
    boolean delete (int id) throws DAOException;


}
