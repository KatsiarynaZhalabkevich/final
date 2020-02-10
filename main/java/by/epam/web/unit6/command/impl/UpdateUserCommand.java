package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.User;

import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.ServiceProvider;
import by.epam.web.unit6.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        UserService userService = ServiceProvider.getInstance().getUserService();
        HttpSession session = request.getSession(); //сессию создавать не нужно, тк пользователь уже авторизован
        User user = (User) session.getAttribute("user");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        double balance =Double.parseDouble(request.getParameter("balance"));
        String goToPage = JSPPageName.USER_EDIT_PROFILE_PAGE;


        if (user != null) {
            user.setName(name);
            user.setSurname(surname);
            user.setPhone(phone);
            user.setEmail(email);
            user.setBalance(balance);
            if (password1!= null && password1.equals(password2)) {

                user.setPassword(password1);
                try {
                    //  userService.updatePassword(user);
                    userService.saveUpdateUser(user);
                    request.setAttribute("updateMessage", "Information was updated!");
                    goToPage = JSPPageName.USER_AUTH_PAGE;
                } catch (ServiceException e) {
                    logger.error(e);
                    request.setAttribute("errorMessage", "Can't update password");
                    goToPage = JSPPageName.ERROR_PAGE;
                    RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
                    dispatcher.forward(request, response);
                    response.sendRedirect(goToPage);
                }
            } else if (!password1.equals(password2)) {
                request.setAttribute("passwordError", "Passwords are not equals");
                goToPage = JSPPageName.USER_EDIT_PROFILE_PAGE;
            }
            try {
                if (userService.saveUpdateUser(user)) {
                    session.setAttribute("user", user);
                    goToPage = JSPPageName.USER_AUTH_PAGE;
                }

            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("errorMessage", "Can't update user's information");
                goToPage = JSPPageName.ERROR_PAGE;
                RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
                dispatcher.forward(request, response);
                response.sendRedirect(goToPage);
            }
        } else {
            request.setAttribute("updateMessage", "Invalid data");
            goToPage = JSPPageName.USER_EDIT_PROFILE_PAGE;
        }

        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);
        response.sendRedirect(goToPage);
    }
}




