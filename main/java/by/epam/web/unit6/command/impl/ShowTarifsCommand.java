package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.Role;
import by.epam.web.unit6.bean.Tarif;
import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.command.util.Paging;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.TarifService;
import by.epam.web.unit6.service.impl.TarifServiceImpl;
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
import java.util.Map;

public class ShowTarifsCommand implements Command {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TarifService tarifService = new TarifServiceImpl();
        List<Tarif> tarifList;
        String goToPage = JSPPageName.ERROR_PAGE;
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("user");


            try {
                tarifList = tarifService.showAllTarif();

//пока не придумала, как это оформить в форму красиво. Но идея постраничного вывода понятна
                if (tarifList != null) {
                //  Map map= Paging.convertListToPage(tarifList);
                    request.setAttribute("tarifs", tarifList);
               //     request.setAttribute("tarifs_size", map.size()); //ключ начинается с 1
                    if (admin==null||!Role.ADMIN.equals(admin.getRole()))  {
                        goToPage=JSPPageName.TARIF_PAGE;
                    }else  goToPage = JSPPageName.TARIF_ADMIN_PAGE;
                }
            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("ErrorMessage", "Can't get information about tariffs. Please, try later");
                goToPage = JSPPageName.ERROR_PAGE;

            }


        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);
        //  response.sendRedirect(goToPage);
    }
}
