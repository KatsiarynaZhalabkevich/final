package by.epam.web.unit6.command.impl;
import by.epam.web.unit6.bean.User;
import by.epam.web.unit6.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(); //без параметрров, тк сюда попасть может только авторизированный юзер
        //если вдруг сессии нету, то и не надо (она не создастся), тк сюда может попасть только авторизированный пользователь

        User user = (User) session.getAttribute("user"); //по ключу получаем значение
        String goToPage;
//может быть null pointer exception, если объект юзер не существует
        if (user != null && user.getRole().equals("user")) {
            goToPage = "WEB-INF/jsp/edit_profile.jsp";
        } else goToPage = "index.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
        dispatcher.forward(request, response);

    }
}
