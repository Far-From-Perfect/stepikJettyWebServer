package servlets;

import accounts.Users;
import dbService.DBService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Сервлет отвечает за получение POST запроса и возвращение ответа Авторизован/Не авторизован.
 * Он проверяет наличие пользователя в БД и возвращает ответ.
 * Здесь реализован минимум проверок для сдачи задания.
 */
public class SignInServlet extends HttpServlet {
    private final DBService service;

    public SignInServlet(DBService service) {
        this.service = service;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if(login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Users profile = service.getUserName(login);

        response.setContentType("text/html;charset=utf-8");
        if(profile == null) {
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            response.getWriter().println("Authorized: " + login);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
