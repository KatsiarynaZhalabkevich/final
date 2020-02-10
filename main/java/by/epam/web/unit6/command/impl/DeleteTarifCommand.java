package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.Role;
import by.epam.web.unit6.bean.Tarif;
import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.TarifService;
import by.epam.web.unit6.service.impl.TarifServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteTarifCommand implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TarifService tarifService = new TarifServiceImpl();
        HttpSession session = request.getSession(); //сессию создавать не нужно, тк пользователь уже авторизован
        User admin = (User) session.getAttribute("user");
        int tarifId = Integer.parseInt(request.getParameter("tarif_id"));

        String goToPage;
        //чтобы не было null Pointer exception
        if(Role.ADMIN.equals(admin.getRole())) {
            try {
                if(tarifService.deleteTarif(tarifId)){
                    request.setAttribute("deleteMessage", "Tarif deleted!");
                }else{
                    request.setAttribute("deleteMessage", "Tarif wasn't deleted!");
                }
                goToPage=JSPPageName.TARIF_ADMIN_PAGE; //перейдем на другую страницу потом

            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("errorMessage", "Can't delete tarif!");
                goToPage=JSPPageName.ERROR_PAGE;
                request.getRequestDispatcher(goToPage).forward(request, response);
                response.sendRedirect(goToPage);
            }
        }else{
            logger.error("Session is finished!");
            request.setAttribute("errorMessage", "Can't delete tarif!");
            goToPage=JSPPageName.ERROR_PAGE;

        }
        request.getRequestDispatcher(goToPage).forward(request, response);
        response.sendRedirect(goToPage);
    }
}
