package by.epam.buber.repository.pool;

import java.util.ResourceBundle;

public class DatabasePropertiesManager {
    private final static DatabasePropertiesManager instance = new DatabasePropertiesManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("database");

    public static DatabasePropertiesManager getInstance() {
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
