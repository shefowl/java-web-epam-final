package by.epam.buber.controller;

import by.epam.buber.controller.util.CommandProvider;
import by.epam.buber.controller.util.impl.post.PostSignIn;
import by.epam.buber.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private CommandProvider commandProvider = new CommandProvider();
    private final String GET = "GET_";
    private final String POST = "POST_";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            String action = request.getParameter("action");
            commandProvider.getCommand(GET + action).execute(request, response);
        }catch (ControllerException e){
            logger.error(e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        commandProvider.getCommand(POST + action).execute(request, response);
        }catch (ControllerException e){
            logger.error(e);
            e.printStackTrace();
        }
    }

}
