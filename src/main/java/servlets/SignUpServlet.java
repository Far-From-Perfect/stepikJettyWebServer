package servlets;

import dbService.DBException;
import dbService.DBService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Сервлет отвечает за получение POST запроса и добавление нового юзера в БД.
 * Данный сервлет не проверяет на наличие уже существующего пользователя также отсутствует ряд других важных проверок.
 * Сервлет создан для выполнения конкретной задачи.
 */
public class SignUpServlet extends HttpServlet {

    private final DBService service;

    public SignUpServlet(DBService service) {
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

        try {
            long id = service.addUser(login, pass);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
