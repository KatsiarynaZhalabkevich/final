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

public class AddTarifCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Метод для создания нового тарифа, доступен только для роли Администратор
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TarifService tarifService = new TarifServiceImpl();
        HttpSession session = request.getSession(); //сессию создавать не нужно, тк пользователь уже авторизован
        User admin = (User) session.getAttribute("user");
        String goToPage;

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int speed = Integer.parseInt(request.getParameter("speed"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        Tarif tarif = new Tarif();
        tarif.setSpeed(speed);
        tarif.setPrice(price);
        tarif.setDiscount(discount);
        tarif.setDescription(description);
        tarif.setName(name);
        if (Role.ADMIN.equals(admin.getRole())) {
            try {
                if (tarifService.addTarif(tarif)) {
                    request.setAttribute("addMessage", "Tariff added!");
                } else {
                    request.setAttribute("addMessage", "Tariff not added!");
                }
                goToPage = JSPPageName.TARIF_ADMIN_PAGE; //перейдем на другую страницу потом

            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("errorMessage", "Can't add tariff");
                goToPage = JSPPageName.ERROR_PAGE;
                request.getRequestDispatcher(goToPage).forward(request, response);
                response.sendRedirect(goToPage);
            }
        } else {
            logger.error("Session is finished!");
            request.setAttribute("errorMessage", "You haven't permission for this action! ");
            goToPage = JSPPageName.ERROR_PAGE;

        }
        request.getRequestDispatcher(goToPage).forward(request, response);
        //response.sendRedirect(goToPage);
    }
}
