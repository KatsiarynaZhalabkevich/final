package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import by.epam.web.unit6.service.NoteService;
import by.epam.web.unit6.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NoSuchCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String goToPage= JSPPageName.ERROR_PAGE;
        request.setAttribute("errorMessage", "Sorry, this action can't be done!");
        request.getRequestDispatcher(goToPage).forward(request, response);
        response.sendRedirect(goToPage);
    }
}
