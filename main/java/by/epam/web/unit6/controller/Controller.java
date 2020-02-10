package by.epam.web.unit6.controller;

import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Единственный сервлет
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static Logger logger = LogManager.getLogger();

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

        if(request.getParameter("local")!=null) {
            request.getSession(true).setAttribute("local", request.getParameter("local"));
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else{
            commandName = request.getParameter(RequestParameterName.REQ_PARAM_COMMAND_NAME);
            command = provider.getCommand(commandName);

            command.execute(request, response);
        }




    }

}
