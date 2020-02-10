package by.epam.web.unit6.command.impl;

import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;
import by.epam.web.unit6.controller.JSPPageName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditProfileCommand implements Command {
    private final static Logger logger = LogManager.getLogger();

    //продумать: может быть эта страница лишняя и в ней можно написать update info?
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); //по ключу получаем значение
        String goToPage;

        if (user!=null) {
            goToPage = JSPPageName.USER_EDIT_PROFILE_PAGE;
        } else goToPage = JSPPageName.INDEX_PAGE;

        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);
        response.sendRedirect(goToPage);

    }
}
