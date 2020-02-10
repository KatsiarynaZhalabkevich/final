package by.epam.web.unit6.command.impl;

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


public class UpdateBalanceCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = ServiceProvider.getInstance().getUserService();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); //по ключу получаем значение
        String goToPage= JSPPageName.USER_AUTH_PAGE;

        double balance =Double.parseDouble(request.getParameter("balance"));
        if(user!=null){
            user.setBalance(balance);
            try{
               if(userService.saveUpdateUser(user)){
                   request.setAttribute("updBalanceMessage","Balance updated!");
                   request.getRequestDispatcher(goToPage).forward(request,response);
                   response.sendRedirect(goToPage);
               }
            }catch (ServiceException e){
                logger.error(e);
                request.setAttribute("updBalanceMessage", "Can't upd user balance");
                request.getRequestDispatcher(goToPage).forward(request,response);
                response.sendRedirect(goToPage);
            }
        }


    }
}
