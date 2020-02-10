package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.controller.RequestParameterName;
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

public class CreateUserCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Метод для создания пользователя
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
        String surname = request.getParameter(RequestParameterName.REQ_PARAM_SURNAME);
        String phone = request.getParameter(RequestParameterName.REQ_PARAM_PHONE);
        String email = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
        String login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
        String password1 = request.getParameter(RequestParameterName.REQ_PARAM_PASS + 1);
        String password2 = request.getParameter(RequestParameterName.REQ_PARAM_PASS + 2);

        UserService userService = ServiceProvider.getInstance().getUserService();

        String goToPage;

        if (password1 != null && password1.equals(password2)) {
            try {
                if (!userService.isLoginUniq(login)) { //если false, то юзера нету и можно создать нового с этим логином
                    User user = new User();
                    user.setLogin(login);
                    user.setPassword(password1);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setEmail(email);
                    user.setPhone(phone);

                    if (userService.saveUpdateUser(user)) { //user создался
                        HttpSession session = request.getSession(true);
//если через id работать, то надо еще 1 запрос к БД делать, чтобы по логину найти юзера?
                        session.setAttribute("user", user); //id user неизвестно, тк оно назначится после того, как юзер запишется в БД
                        request.setAttribute("user", user);
                        goToPage = JSPPageName.USER_AUTH_PAGE;
                    } else {
                        request.setAttribute("ErrorMessage", "Can't register a new user. Please, try again.");
                        goToPage = JSPPageName.USER_REG_PAGE;
                    }

                } else {
                    request.setAttribute("ErrorLoginMessage", "Login already exist!");
                    goToPage = JSPPageName.USER_REG_PAGE;
                }
            } catch (ServiceException e) {
                request.setAttribute("ErrorMessage", e);
                goToPage = JSPPageName.USER_REG_PAGE;
                RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
                dispatcher.forward(request, response);
                response.sendRedirect(goToPage);
            }
        } else {
            request.setAttribute("ErrorPasswordMessage", "Passwords are not equal!!!");
            goToPage = JSPPageName.USER_REG_PAGE;
            logger.info("Passwords are not equal!");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);
        response.sendRedirect(goToPage);


    }
}
