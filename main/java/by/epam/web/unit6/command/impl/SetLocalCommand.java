package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetLocalCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String goToPage = null;
        request.getSession(true).setAttribute("local",request.getParameter("local"));
        request.getRequestDispatcher(goToPage).forward(request, response);
    }
}
