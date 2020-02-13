package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.service.locale.LocaleResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

import static by.epam.buber.controller.util.PostFormRedirection.MAIN_REDIRECT;

public class GetChangeLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        final Locale ru = new Locale("ru", "RU");
        final Locale en = new Locale("en", "US");
        LocaleResourceManager resourceManager = LocaleResourceManager.getInstance();

        HttpSession session = request.getSession();
        Locale currentLocale = resourceManager.getCurrentLocale();
        if(session.getAttribute("locale").equals(en)){
            resourceManager.changeResource(ru);
            session.setAttribute("locale", ru);
        }
        else {
            resourceManager.changeResource(en);
            session.setAttribute("locale", en);
        }
        response.sendRedirect(MAIN_REDIRECT);
        //request.getRequestDispatcher(MAIN);
    }
}
