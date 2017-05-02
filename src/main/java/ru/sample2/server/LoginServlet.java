package ru.sample2.server;

import ru.sample2.server.DAO.entity.UserEntity;
import ru.sample2.server.DAO.Impl.UsersDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Anna on 19.02.2017.
 */
public class LoginServlet extends HttpServlet {
    private List<UserEntity> users;
    private static String userLogin;

    public static String getUserLogin() {
        return userLogin;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setHeader("Cache Control", "no cache");

        UsersDAOImpl repository = new UsersDAOImpl();
        if (users == null) {
            users = new ArrayList<>();
        }
        users = repository.getUsers();

        if (req.getParameter("login") == null && req.getParameter("password") == null ) {

            resp.getWriter().println("<html><body><p><h1>" + "Null value entered. " + "</h1></p></body></html>");

        }else {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if (isLoginValid(login)) {
                if (isPasswordValid(login, password)) {
//                    resp.getWriter().println("<html><body><p><h1>" + "Welcome, " + login + "!" + "</h1></p></body></html>");
                    req.getRequestDispatcher("SuggestBoxModule.html").forward(req, resp);
                    userLogin = login;
                } else {
//                    resp.getWriter().println("<html><body><p><h1>" + "Error, password is false! " + "</h1></p></body></html>");
                    req.getRequestDispatcher("error.html").forward(req, resp);
                }
            } else {
//                resp.getWriter().println("<html><body><p><h1>" + "Error, login is false! " + "</h1></p></body></html>");
                req.getRequestDispatcher("create.html").forward(req, resp);

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public boolean isLoginValid(String login) {
        for (UserEntity user : users) {
            if (user.getLogin().equals(login)) {
                // Действия при нахождении.
                return true;
            }
        }
        return false;
    }
    private boolean isPasswordValid(String login, String password) {
        for (UserEntity user : users) {
            if (user.getLogin().equals(login)) {
                // Действия при нахождении.
                if (user.getPassword().equals(password)) {
                    return true;
                } else {
                    return false;
                }

            }
        }
        return false;
    }

}
