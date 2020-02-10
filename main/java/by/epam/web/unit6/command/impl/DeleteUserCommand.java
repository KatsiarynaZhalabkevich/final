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

public class DeleteUserCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = ServiceProvider.getInstance().getUserService();
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user"); //admin
        int id = Integer.parseInt(request.getParameter("user_id"));
        String goToPage = JSPPageName.SHOW_USERS_PAGE;

        if(admin.getRole().equals(Role.ADMIN)){
            try {
                if(userService.deleteUser(id)){
                    request.setAttribute("deleteUserMessage","User deleted!");
                }else{
                    request.setAttribute("deleteUserMessage","User not deleted!");
                }
                request.getRequestDispatcher(goToPage).forward(request, response);
                response.sendRedirect(goToPage);
            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("deleteUserMessage","User not deleted!");
                request.getRequestDispatcher(goToPage).forward(request, response);
                response.sendRedirect(goToPage);
            }

        }else{
            logger.error("No permission");
            goToPage=JSPPageName.ERROR_PAGE;
            request.setAttribute("erorMessage", "You have no permission for this action! Please, log in! ");
            request.getRequestDispatcher(goToPage).forward(request, response);
            response.sendRedirect(goToPage);
        }

    }
}
