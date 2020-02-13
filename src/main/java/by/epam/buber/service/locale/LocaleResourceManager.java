package by.epam.buber.service.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleResourceManager {
    private final static LocaleResourceManager instance = new LocaleResourceManager();
    private final String resourcePath = "locale";
    private ResourceBundle resourceBundle;

    private LocaleResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourcePath, Locale.getDefault());
    }

    public static LocaleResourceManager getInstance() {
        return instance;
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourcePath, locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public Locale getCurrentLocale() {
        return resourceBundle.getLocale();
    }
}

