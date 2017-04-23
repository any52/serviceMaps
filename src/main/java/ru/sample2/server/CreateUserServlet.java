package ru.sample2.server;

import ru.sample2.shared.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 11.03.2017.
 */
public class CreateUserServlet extends HttpServlet {
    private List<UserEntity> users;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setHeader("Cache Control", "no cache");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (users == null) {
            users = new ArrayList<>();
        }
        UsersRepositoryImpl repository = new UsersRepositoryImpl();
        if (users.isEmpty()) {
            users = repository.getUsers();
        }
        for (UserEntity user: users ) {
            if (user.getLogin().equals(login)) {
                req.getRequestDispatcher("errorLogin.html").forward(req, resp);
                break;
            }

        }
        repository.addUser(login, password);
        req.getRequestDispatcher("SuggestBoxModule.html").forward(req, resp);


//        resp.getWriter().println("<html><body><p><h1>" + "Login is existed successfully!" + "</h1></p></body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
