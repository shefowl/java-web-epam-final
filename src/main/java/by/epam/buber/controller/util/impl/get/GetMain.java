package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.service.locale.LocaleResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

import static by.epam.buber.controller.util.Pages.MAIN;

public class GetMain implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("locale") == null){
            LocaleResourceManager resourceManager = LocaleResourceManager.getInstance();
            Locale localeEN = new Locale( "en", "US");
            resourceManager.changeResource(localeEN);
            session.setAttribute("locale", localeEN);
        }
        request.getRequestDispatcher(MAIN).forward(request, response);
    }
}
