package by.epam.buber.controller;

import by.epam.buber.controller.util.CommandProvider;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/hello")
public class UserController extends HttpServlet {
    private CommandProvider commandProvider = new CommandProvider();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        commandProvider.getCommand("GET_" + action).execute(request, response);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        commandProvider.getCommand("POST_" + action).execute(request, response);
    }

}
