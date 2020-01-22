package by.epam.web.unit6.main;

import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.bean.util.PasswordCreater;
import by.epam.web.unit6.dao.DAOException;
import by.epam.web.unit6.dao.DAOProvider;
import by.epam.web.unit6.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Properties;

public class Runner {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("все ок");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/mytelecom";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "123456");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
       // prop.put("useSSL", "true");
        // DAOProvider provider = DAOProvider.getInstance();
        // UserDAO userDAO = provider.getUserDao();

        String login = "admin";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(url, prop);
             Statement statement = connection.createStatement()) {
            // String sql ="select user.id, user.login, user.is_active, user.password, user_card.name, user_card.surname, user_card.address, user_card.phone, user_card.email, user_card.balance  FROM user, user_card where user.login='admin' ";


            String sql = "SELECT * FROM mytelecom.user WHERE login='admin'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                if (password.equals(resultSet.getString(3))) {
                    User user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setLogin(resultSet.getString(2));

                    System.out.println(user);
                } else {
                    logger.info("Password is incorrect!");

                }
            } else {
                logger.info("Login is incorrect!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          /*  User user = userDAO.authorize("admin", "admin");
            if (user != null) {
                System.out.println(user);
            }
        } catch (DAOException e) {
            System.out.println("что-то пошло не так");
        }*/
    }
}
