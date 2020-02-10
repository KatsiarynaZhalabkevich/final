package by.epam.web.unit6.filter;

import by.epam.web.unit6.bean.Role;
import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.ServiceProvider;
import by.epam.web.unit6.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private final static Logger logger = LogManager.getLogger();

    public void destroy() {
    }

    // из реквеста взять комманд нэйм
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserService userService = ServiceProvider.getInstance().getUserService();
        String login;
        String password;
        logger.info("фильтр работает");

        if (user != null) {
            //если он уже был авторизован, то надо взять его роль и понять, можно и ему на страницу, на которую он хочет пройти
            //но это в другом фильтре
            logger.info("фильтр работает юзер не нал");
            moveToPage(request, response, user);
        } else {
            login = request.getParameter("login");
            password = request.getParameter("password");
            logger.info("фильтр работает проверяем логин и пароль");
            if (login == null || password == null || login.equals("") || password.equals("")) {
                moveToPage(request, response, user);
            } else {
                try {
                    user = userService.authorization(login, password);
                    session.setAttribute("user", user);
                    moveToPage(request, response, user);
                    logger.info("фильтр работает");
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        }

        String servletPath = request.getServletPath();
        logger.info(servletPath);
        chain.doFilter(req, resp);
    }

    //варианты перехода на страницы
    //имеет ли пользователь право доступа
    private void moveToPage(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String goToPage;

        if (user != null) {
            //юзер прошел идентификацию, надо определить его роль
            if (user.getRole().equals(Role.ADMIN)) {
                goToPage = JSPPageName.ADMIN_PAGE;
            } else {
                goToPage = JSPPageName.USER_AUTH_PAGE;
            }
        } else {
            goToPage = JSPPageName.INDEX_PAGE;
        }
        request.getRequestDispatcher(goToPage).forward(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
