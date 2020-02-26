package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Redirect;
import by.epam.buber.controller.util.SessionAttribute;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.service.locale.LocaleResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class GetChangeLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        final Locale ru = new Locale("ru", "RU");
        final Locale en = new Locale("en", "US");
        LocaleResourceManager resourceManager = LocaleResourceManager.getInstance();

        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttribute.LOCALE_ATTRIBUTE).equals(en)){
            resourceManager.changeResource(ru);
            session.setAttribute(SessionAttribute.LOCALE_ATTRIBUTE, ru);
        }
        else {
            resourceManager.changeResource(en);
            session.setAttribute(SessionAttribute.LOCALE_ATTRIBUTE, en);
        }
        response.sendRedirect(Redirect.MAIN_REDIRECT);
    }
}
