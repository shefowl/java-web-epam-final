package by.epam.buber.service;

import by.epam.buber.entity.*;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.entity.participant.User;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;

public class UserService {
    private UserCrudRepositoryImpl repository = new UserCrudRepositoryImpl();

    public TaxiParticipant login(String name, String password){
        TaxiParticipant taxiParticipant = repository.getByName(name);
        if(taxiParticipant != null){
            if(taxiParticipant.getPassword().equals(password)){
                return taxiParticipant;
            }
        }
        else{
            return null;
        }
        return null;
    }

    public TaxiParticipant signUp(String name, String password, String email, String phoneNumber){
        User user = (User)repository.getByName(name);
        if(user == null){
            user = new User(name, password, email, phoneNumber);
            repository.save(user);
        }
        return user;
    }

    public TaxiParticipant changeName(int id, String name) {
        TaxiParticipant taxiParticipant = repository.getById(id);
        if (taxiParticipant != null) {
            taxiParticipant.setName(name);
            repository.save(id, taxiParticipant);
            return taxiParticipant;
        }
        return null;
    }

    public TaxiParticipant changePassword(int id, String current, String newPassword, String repeatNewPassword) {
        TaxiParticipant taxiParticipant = repository.getById(id);
        if (taxiParticipant != null) {
            if(taxiParticipant.getPassword().equals(current)){
                if(newPassword.equals(repeatNewPassword)) {
                    taxiParticipant.setPassword(newPassword);
                    repository.save(id, taxiParticipant);
                    return taxiParticipant;
                }
            }
        }
        return null;
    }

    public Order makeOrder(int userId,String address, String carClass, String comment){
        Order order = new Order(userId, address, comment,CarClass.valueOf(carClass.toUpperCase()));
        //List<Driver> ableDrivers = repository.getAbleDrivers(address, carClass);
        //order.setAbleDrivers(ableDrivers);
        return order;
    }
}
