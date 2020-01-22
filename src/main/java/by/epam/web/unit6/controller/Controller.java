package by.epam.web.unit6.controller;

import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final CommandProvider provider = CommandProvider.getInstance();
    public Controller() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName;
        Command command;
        String page = null;

        commandName = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
        command = provider.getCommand(commandName);

        command.execute(request, response);
    }

}
