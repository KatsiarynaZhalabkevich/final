package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.Role;
import by.epam.web.unit6.bean.Tarif;
import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.dto.UserTarif;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.ServiceProvider;
import by.epam.web.unit6.service.TarifService;
import by.epam.web.unit6.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowUserTariffsCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = ServiceProvider.getInstance().getUserService();
        TarifService tarifService = ServiceProvider.getInstance().getTarifService();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        String goToPage = JSPPageName.SHOW_USERS_PAGE;
        logger.info("command show user tariffs");
        if (Role.ADMIN.equals(user.getRole())) {
            logger.info("inside");
            int id = Integer.parseInt(request.getParameter("user_id"));
            logger.info("id =", id);
            try {
                List<UserTarif> tariffs = tarifService.showTarifsByUserId(id);
                if (tariffs != null) {
                    logger.info("тарифы получены");
                }
                session.setAttribute("tarifList", tariffs);
                goToPage = JSPPageName.SHOW_USERS_PAGE;
            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("errorMessage", "Can't get user's tariffs!");
                goToPage = JSPPageName.ERROR_PAGE;
                request.getRequestDispatcher(goToPage).forward(request, response);
                response.sendRedirect(goToPage);
            }

        }
        request.getRequestDispatcher(goToPage).forward(request, response);
        response.sendRedirect(goToPage);

    }
}
