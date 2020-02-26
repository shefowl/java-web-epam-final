package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.SessionAttribute;
import by.epam.buber.service.locale.LocaleResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class GetMain implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttribute.LOCALE_ATTRIBUTE) == null){
            LocaleResourceManager resourceManager = LocaleResourceManager.getInstance();
            Locale localeEN = new Locale( "en", "US");
            resourceManager.changeResource(localeEN);
            session.setAttribute(SessionAttribute.LOCALE_ATTRIBUTE, localeEN);
        }
        request.getRequestDispatcher(Page.MAIN).forward(request, response);
    }
}
