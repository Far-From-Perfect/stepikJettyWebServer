package servlets;

import accounts.Users;
import com.google.gson.Gson;
import dbService.DBService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SessionServlet extends HttpServlet {
    private final DBService service;

    public SessionServlet(DBService service) {
        this.service = service;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String login = request.getParameter("login");
        Users profile = service.getUserName(login);

        if(profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String login = request.getParameter("login");
//        String pass = request.getParameter("pass");
//
//        if(login == null || pass == null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        Users profile = accountService.getUserByLogin(login);
//        if(profile == null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        accountService.addSession(request.getSession().getId(), profile);
//        Gson gson = new Gson();
//        String json = gson.toJson(profile);
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().println(json);
//        response.setStatus(HttpServletResponse.SC_OK);
//    }
//
//    @Override
//    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String sessionId = request.getSession().getId();
//        Users profile = accountService.getUserBySessionId(sessionId);
//
//        if(profile == null) {
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {
//            accountService.deleteSession(sessionId);
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().println("Goodbye!");
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//    }
}
