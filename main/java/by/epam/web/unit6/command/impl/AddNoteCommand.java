package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.Note;
import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.service.NoteService;
import by.epam.web.unit6.service.ServiceException;
import by.epam.web.unit6.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddNoteCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Метод для добавления новой записи о тарифе пользователя
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        NoteService noteService = ServiceProvider.getInstance().getNoteService();
        HttpSession session = request.getSession(); //только зарегистрированный пользователь может добавить себе тариф
        User user = (User) session.getAttribute("user");
        int tarifId = Integer.parseInt(request.getParameter("tarif_id"));

        String goToPage;


        if (user != null) {
            Note note = new Note();
            note.setTarifId(tarifId);
            note.setUserId(user.getId());

            try {
                if (noteService.addNote(note)) {//добавили в БД
                    request.setAttribute("updateMessage", "New tariff is added!");
                }else{
                    request.setAttribute("errorMessage", "Can't add tariff to account!");
                }
                goToPage = JSPPageName.USER_AUTH_PAGE;

            } catch (ServiceException e) {
                logger.error(e);
                request.setAttribute("errorMessage", "Can't add tariff to account!");
                goToPage = JSPPageName.USER_AUTH_PAGE;
                request.getRequestDispatcher(goToPage).forward(request, response);
                response.sendRedirect(goToPage);
            }

        } else {
            request.setAttribute("errorMessage", "Session is expired. Please, sign in!");
            goToPage = JSPPageName.ERROR_PAGE;

        }

        request.getRequestDispatcher(goToPage).forward(request, response);
        response.sendRedirect(goToPage);
    }
}
