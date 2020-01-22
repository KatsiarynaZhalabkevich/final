package by.epam.web.unit6.main;

import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.dao.DAOException;
import by.epam.web.unit6.dao.DAOProvider;
import by.epam.web.unit6.dao.UserDAO;

public class Main {
    public static void main(String[] args) {
        DAOProvider provider = DAOProvider.getInstance();
        UserDAO userDAO = provider.getUserDao();



        try {
            System.out.println("Попытка создать юзера");
          boolean creation = userDAO.create(new User());
            System.out.println("Создали юзера");
            System.out.println(creation);
        } catch (DAOException e) {
            e.printStackTrace();
            System.out.println("что-то пошло не так");
        }

    }
}
