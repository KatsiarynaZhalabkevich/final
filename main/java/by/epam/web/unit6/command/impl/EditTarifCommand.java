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
import java.util.List;

public class EditTarifCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TarifService tarifService = new TarifServiceImpl();
        HttpSession session = request.getSession(); //сессию создавать не нужно, тк пользователь уже авторизован
        User admin = (User) session.getAttribute("user");

        int id = Integer.parseInt(request.getParameter("tarif_id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int speed = Integer.parseInt(request.getParameter("speed"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        Tarif tarif = new Tarif();
        tarif.setId(id);
        tarif.setSpeed(speed);
        tarif.setPrice(price);
        tarif.setDiscount(discount);
        tarif.setDescription(description);
        tarif.setName(name);
        //не готова jsp страница, не продумано, как получать данные при изменении

        //List<Tarif> tarifList;
        String goToPage;
        //чтобы не было null Pointer exception
        if (Role.ADMIN.equals(admin.getRole())) {
            try {
                if (tarifService.changeTarif(tarif)) {
                    request.setAttribute("editMessage", "Tariff updated!");
                } else {
                    request.setAttribute("editMessage", "Tariff not updated!");
                }
                goToPage = JSPPageName.TARIF_ADMIN_PAGE;
            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("errorMessage", "Can't add tariff!");
                goToPage = JSPPageName.ERROR_PAGE;
                request.getRequestDispatcher(goToPage).forward(request, response);
                //response.sendRedirect(goToPage);
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
