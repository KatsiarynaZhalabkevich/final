package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.Note;
import by.epam.web.unit6.bean.Role;
import by.epam.web.unit6.bean.Tarif;
import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.controller.RequestParameterName;
import by.epam.web.unit6.dto.UserTarif;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.ServiceProvider;
import by.epam.web.unit6.service.TarifService;
import by.epam.web.unit6.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AuthorizationCommand implements Command {

    private final static Logger logger = LogManager.getLogger();

    /**
     * Метод для авторизации пользователя. Включает в себя получение списка тарифов пользователя
     * и всех тарифов для отображения на странице пользователя
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login;
        String password;

        login = request.getParameter("login");
        password = request.getParameter("password");

        UserService userService = ServiceProvider.getInstance().getUserService();
        TarifService tarifService = ServiceProvider.getInstance().getTarifService();

        User user;
        List<Tarif> tarifList;
        String goToPage;

        try {

            user = userService.authorization(login, password);


            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user); 

                if (Role.ADMIN.equals(user.getRole())) {
                    goToPage = JSPPageName.ADMIN_PAGE;
                } else {
                    try{
                    List<UserTarif> tariffs = tarifService.showTarifsByUserId(user.getId()); //все тарифы какие есть
                    request.setAttribute("userTarifs", tariffs);
                    tarifList = tarifService.showAllTarif();
                    request.setAttribute("tarifs", tarifList);

                    } catch (ServiceException e) {
                        logger.error(e);   //никуда не переходим, тк это не главное в данной команде
                        request.setAttribute("errorMessage", "Can't get data about tariffs!");
                    }

                    goToPage = JSPPageName.USER_AUTH_PAGE;
                }
            } else {
                request.setAttribute("loginErrorMessage", "Wrong login and/or password!");
                goToPage = JSPPageName.INDEX_PAGE;
            }


            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);
            //response.sendRedirect(goToPage);

        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute("errorMessage", "Can't get data about user! Please try again later.");
            goToPage = JSPPageName.ERROR_PAGE;
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);
          //  response.sendRedirect(goToPage);
        }

    }

}

