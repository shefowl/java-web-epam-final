package by.epam.buber.servlet;

import by.epam.buber.entity.Order;
import by.epam.buber.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app")
public class Controller extends HttpServlet {

    private UserService userService = new UserService();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        switch (action == null ? "info" : action) {
            case "users":
                request.getRequestDispatcher("/userList.jsp").forward(request, response);
                break;
            case "userPage":
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
                break;
            case "changeName":
                request.getRequestDispatcher("/account.jsp").forward(request, response);
                break;
            case "changePassword":
                request.getRequestDispatcher("/password.jsp").forward(request, response);
                break;
            case "newOrder":
                request.getRequestDispatcher("/newOrder.jsp").forward(request, response);
                break;
            case "orders":
                //request.setAttribute("order",userService.getCurrentOrder(session.getAttribute("userName")));
                request.getRequestDispatcher("/orders.jsp").forward(request, response);
                break;
            case "info":
            default:
                request.getRequestDispatcher("/main.jsp").forward(request, response);
                break;
        }
    }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            HttpSession session = request.getSession();
            request.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");

            if ("newOrder".equals(action)) {
                Order order = userService.makeOrder((Integer) session.getAttribute("id"),
                        request.getParameter("address"),
                        request.getParameter("class"),
                        request.getParameter("comment"));
                request.getRequestDispatcher("/main.jsp").forward(request, response);
            }

            if(action.equals("changeName")){
                String newName;
                newName = userService.changeName((Integer) session.getAttribute("userId"),
                        request.getParameter("name")).getName();
                session.setAttribute("userName", newName);
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }

            if(action.equals("changePassword")){
                userService.changePassword((Integer) session.getAttribute("userId"),
                        request.getParameter("current"), request.getParameter("new"),
                        request.getParameter("repeatNew"));
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }

        }



}
