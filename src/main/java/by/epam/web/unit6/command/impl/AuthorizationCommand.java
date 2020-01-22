package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.controller.RequestParameterName;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.ServiceProvider;
import by.epam.web.unit6.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthorizationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login;
        String password;

        login = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_LOGIN);
        password = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_PASS);

        UserService userService = ServiceProvider.getInstance().getUserService();

        User user;
        String goToPage;

        try {
            user = userService.authorization(login, password);

            if (user != null) {
                HttpSession session = request.getSession(true); //true (или ничего) сессия создатся в любом случае, false  - сессия вернется только в случае, если она существует и жива
                session.setAttribute("user", user); //пара ключ - значение доступна пока сессия активна, для повторных запросов тоже.
                //ключ-значение можно придумать любое, чтобы было удобно понимать, что пользователь авторизирован
                //может быть роль, чтобы понимать сразу, какие у него права в дальнейшем?
                goToPage = JSPPageName.USER_AUTH_PAGE;
            } else {
                request.setAttribute("loginErrorMessage", "Wrong login and/or password!");
                goToPage = "index.jsp";
            }
            request.setAttribute("user", user);

            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage );


            dispatcher.forward(request, response); //сделать sendRedirect
            PrintWriter out = response.getWriter();
            out.println("<h1>Hello, " + user.getName());


        } catch (ServiceException e) {
        }

    }

}

