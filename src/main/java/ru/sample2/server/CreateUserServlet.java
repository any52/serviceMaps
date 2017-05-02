package ru.sample2.server;

import ru.sample2.server.DAO.entity.UserEntity;
import ru.sample2.server.DAO.Impl.UsersDAOImpl;

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
    private static String userLogin;

    public static String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setHeader("Cache Control", "no cache");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (users == null) {
            users = new ArrayList<>();
        }
        UsersDAOImpl repository = new UsersDAOImpl();
        if (users.isEmpty()) {
            users = repository.getUsers();
        }
        boolean isExist = false;
        for (UserEntity user : users) {
            if (user.getLogin().equals(login)) {
                req.getRequestDispatcher("errorLogin.html").forward(req, resp);
                isExist = true;
                break;
            }

        }

        if (!(isExist)) {
            repository.addUser(login, password);
            req.getRequestDispatcher("SuggestBoxModule.html").forward(req, resp);
            userLogin = login;
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
