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

public class DeleteNoteCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    /**
     * Метод для удаления записи о тарифе пользователя
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        NoteService noteService = ServiceProvider.getInstance().getNoteService();
        HttpSession session = request.getSession(); //только зарегистрированный пользователь может удалить себе тариф
        User user = (User) session.getAttribute("user");
        int noteId = Integer.parseInt(request.getParameter("note_id"));

        String goToPage;

        if(user!=null){
            try {
                if(noteService.deleteNote(noteId)){
                    request.setAttribute("updateMessage", "Tariff is deleted!!");
                }else{
                    request.setAttribute("errorMessage", "Can't delete tariff from account!");
                }
                goToPage = JSPPageName.USER_AUTH_PAGE;
                request.getRequestDispatcher(goToPage).forward(request, response);
                response.sendRedirect(goToPage);
            } catch (ServiceException e) {
               logger.error(e);
                request.setAttribute("errorMessage", "Can't delete tariff from account!");
                goToPage = JSPPageName.USER_AUTH_PAGE;
                request.getRequestDispatcher(goToPage).forward(request, response);
                response.sendRedirect(goToPage);
            }
        }else{
            request.setAttribute("errorMessage", "Session is expired. Please, sign in!");
            goToPage = JSPPageName.ERROR_PAGE;
            request.getRequestDispatcher(goToPage).forward(request, response);
            response.sendRedirect(goToPage);
        }
    }
}
