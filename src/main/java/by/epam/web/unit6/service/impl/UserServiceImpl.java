package by.epam.web.unit6.service.impl;

import by.epam.web.unit6.bean.Role;
import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.dao.DAOException;
import by.epam.web.unit6.dao.DAOProvider;
import by.epam.web.unit6.dao.UserDAO;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.UserService;
import by.epam.web.unit6.service.validation.UserDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private static final UserDataValidator validator = UserDataValidator.getInstance();
    private static final UserDAO userDao = DAOProvider.getInstance().getUserDao();

    @Override
    public User authorization(String login, String password) throws ServiceException {
        //проверка корректности ввода
        if (!validator.checkLoginPassword(login, password)) {
            throw new ServiceException("Incorrect login or password!");
        }

        User user;
        try {
            user = userDao.authorize(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean saveUser(User user) throws ServiceException {
        boolean flag;
        String login = user.getLogin();
        String password = user.getPassword();
        int id = user.getId();
        String name = user.getName();
        String surname = user.getSurname();
        String phone = user.getPhone();
        String email = user.getEmail();
        int balance = user.getBalance();

        if (!validator.checkLoginPassword(login, password)) {
            throw new ServiceException("Incorrect login or password!");
        }
        if (!validator.checkName(name)) {
            throw new ServiceException("Incorrect name");
        }
        if (!validator.checkName(surname)) {
            throw new ServiceException("Incorrect surname");
        }
        if (!validator.checkPhone(phone)) {

            throw new ServiceException("Incorrect phone number");
        }
        if (!validator.checkEmail(email)) {

            throw new ServiceException("Incorrect email!");
        }

        try {
            flag = userDao.create(user);

        } catch (DAOException e) {
            flag = false;
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        User user = null;
        //не проверяем логин, тк если он неверный, то просто такого пользователя не будет
        try {
            user = userDao.takeByLogin(login);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getUsers() throws ServiceException {
        List<User> users = new ArrayList<>();
        try {
            users.addAll(userDao.takeAll());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean editProfile(User user) throws ServiceException {
        //продумать как сделать метод универсальным
        return false;
    }

    @Override
    public boolean deleteUser(int id) throws ServiceException {
        boolean flag = false;
        try {
            flag = userDao.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean changePassword(int id, String newPassword) throws ServiceException {

        return false;
    }


}
