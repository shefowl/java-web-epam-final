package by.epam.buber.service;

import by.epam.buber.entity.User;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;

public class UserService {
    UserCrudRepositoryImpl repository = new UserCrudRepositoryImpl();

    public User login(String name, String password){
        User user = repository.getByName(name);
        if(user != null){
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        else{
            return null;
        }
        return null;
    }

    public User signUp(String name, String password){
        User user = repository.getByName(name);
        if(user == null){
            user = new User(name, password);
            repository.save(user);
        }
        return user;
    }

    public User changeName(int id, String name) {
        User user = repository.getById(id);
        if (user != null) {
            user.setName(name);
            repository.save(id, user);
            return user;
        }
        return null;
    }

    public User changePassword(int id, String current, String newPassword, String repeatNewPassword) {
        User user = repository.getById(id);
        if (user != null) {
            if(user.getPassword().equals(current)){
                if(newPassword.equals(repeatNewPassword)) {
                    user.setPassword(newPassword);
                    repository.save(id, user);
                    return user;
                }
            }
        }
        return null;
    }
}
