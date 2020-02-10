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

public class ChangeBalanceCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Метод администратора для изменения баланса пользователя
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = ServiceProvider.getInstance().getUserService();

        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user"); //admin
        int id = Integer.parseInt(request.getParameter("user_id"));
        double oldBalance = Double.parseDouble(request.getParameter("old_balance"));
        double balance = oldBalance+Double.parseDouble(request.getParameter("balance"));
        String goToPage = JSPPageName.SHOW_USERS_PAGE;

        if (Role.ADMIN.equals(admin.getRole())) {
            try {
                if (userService.changeBalanceById(id, balance)) {
                    request.setAttribute("updBalanceMessage", "Balance updated!");
                } else {
                    request.setAttribute("updBalanceMessage", "Can't upd user balance");
                }

            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("updBalanceMessage", "Can't upd user balance");
                request.getRequestDispatcher(goToPage).forward(request, response);
                response.sendRedirect(goToPage);

            }
        } else {
            logger.error("No permission");
            goToPage = JSPPageName.ERROR_PAGE;
            request.setAttribute("errorMessage", "You have no permission for this action! Please, log in! ");

        }
        request.getRequestDispatcher(goToPage).forward(request, response);
        response.sendRedirect(goToPage);
    }
}
