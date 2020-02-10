package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.Role;
import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.ServiceProvider;
import by.epam.web.unit6.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        UserService userService = ServiceProvider.getInstance().getUserService();
        HttpSession session = request.getSession(); //сессию создавать не нужно, тк пользователь уже авторизован
        User user = (User) session.getAttribute("user");
        List<User> users = new ArrayList<>();

        String goToPage = JSPPageName.ADMIN_PAGE;
        logger.info("show User command");
        if (Role.ADMIN .equals(user.getRole())) {
            logger.info("role admin");
            try {
                users.addAll(userService.getUsers());
                logger.info("get users ");


                if (users != null) {
                    session.setAttribute("usersList", users);
                    goToPage = JSPPageName.SHOW_USERS_PAGE;

                }

            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("errorMessage", "Can't get data about users. Try later!");
                goToPage = JSPPageName.ERROR_PAGE;
                request.getRequestDispatcher(goToPage).forward(request, response);
            }

        }
        request.getRequestDispatcher(goToPage).forward(request, response);
       // response.sendRedirect(goToPage);
    }
}
