package by.epam.web.unit6.dao.impl;

import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.bean.util.PasswordCreater;
import by.epam.web.unit6.dao.DAOException;
import by.epam.web.unit6.dao.UserDAO;
import by.epam.web.unit6.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SQLUserDAO implements UserDAO {

    private final static Logger logger = LogManager.getLogger();
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public User authorize(String login, String password) throws DAOException {
        //через пул обратиться к базе данных, найдет данные и создат объект юзер
        //если пользователя нету, то можно вернуть null
        //это не ошибка для системы


//не будет ли проблем с коннекшном, если пользоваться траем с ресурсами, чтобы случайно не закрыть весь пул?
// где и когда закрывать пул?
        try (Connection connection = connectionPool.takeConnection();
             Statement statement = connection.createStatement()) {


            String sql = "SELECT user_card.card_id, user_card.name, user_card.surname, " +
                    "user_card.phone, user_card.email, user_card.email, user_card.balance," +
                    "user_card.role, user_card.active " +
                    "FROM mytelecom.user_card WHERE mytelecom.user.login='" + login + "'AND mytelecom.user.password='" + password + "'";

            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setPhone(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                user.setBalance(resultSet.getInt(6));
                user.setRole(resultSet.getString(7));

                user.setActive(resultSet.getBoolean(8));
                // user.setPassword(resultSet.getString(9)); //кодирование пароля продумать корректно
                // user.setLogin(resultSet.getString(10));

                return user;

            } else {
                logger.info("Login or password is incorrect!");
                return null;
            }


        } catch (Exception e) {
            throw new DAOException(e);
        }
        //метод closeConnection перегрузить на все случаи
        //можно в дао написать и закрыть, подумать как будет лучше (в утилитном классе)


    }

    @Override
    public boolean create(User user) throws DAOException {
        boolean flag = false;

        try (Connection connection = connectionPool.takeConnection()) {
            String sql = "";
            PreparedStatement statement = connection.prepareStatement(sql);

            sql = "INSERT INTO user_card (name, surname, phone, email, balance, active, card_id) values (?,?,?,?,?,?,?)";

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getBalance());
            statement.setBoolean(6, user.isActive());
            statement.setInt(7, user.getId());

            statement.executeUpdate();

            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public User takeById(int id) throws DAOException {
        return null;
    }

    @Override
    public User takeByLogin(String login) throws DAOException {
        boolean flag;
        try (Connection connection = connectionPool.takeConnection()) {

            String sql = "SELECT login FROM user WHERE login='" + login + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){

                sql ="";
                //такой логин существует
            }



            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> takeByName(String name) throws DAOException {
        return null;
    }

    @Override
    public User takeByEmail(String email) throws DAOException {
        return null;
    }

    @Override
    public List<User> takeAll() throws DAOException {
        return null;
    }

    @Override
    public boolean updateInfo(User user) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }


}
