package by.epam.buber.service.impl;

import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.impl.OrderCrudRepositoryImpl;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.impl.util.CoordinatesGenerator;
import by.epam.buber.service.impl.util.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private UserCrudRepository userCrudRepository = new UserCrudRepositoryImpl();
    private OrderCrudRepository orderCrudRepository = new OrderCrudRepositoryImpl();
    private PasswordEncoder passwordEncoder = new PasswordEncoder();


    @Override
    public void ban(Integer participantId) {
        userCrudRepository.ban(participantId,true);
    }

    @Override
    public void unban(Integer participantId) {
        userCrudRepository.ban(participantId,false);
    }

    @Override
    public List<TaxiParticipant> getAllParticipants(){
        return userCrudRepository.getAll();
    }

    @Override
    public List<TaxiParticipant> getUsersByName(String name) {
        List<TaxiParticipant> participants = getAllParticipants();
        List<TaxiParticipant> participantsByName = new ArrayList<>();
        for (TaxiParticipant participant: participants){
            if(participant.getName().contains(name)){
                participantsByName.add(participant);
            }
        }
        return participantsByName;
    }

    @Override
    public List<TaxiParticipant> getUsersForDiscount() {
        List<TaxiParticipant> participants = getAllParticipants();
        List<TaxiParticipant> participantsForDiscount = new ArrayList<>();
        List<Order> orders;
        for (TaxiParticipant participant: participants){
            orders = orderCrudRepository.getByUserId(participant.getId());
            BigDecimal price = new BigDecimal(0);
            for (Order order: orders){
                price = price.add(order.getPrice());
            }
            if(price.doubleValue() > 1000) {
                participantsForDiscount.add(participant);
            }
        }
        return participantsForDiscount;
    }

    @Override
    public void setDiscount(int userId, int discount) {
        userCrudRepository.setDiscount(userId, discount);
    }

    @Override
    public TaxiParticipant getParticipantById(Integer participantId) {
        return userCrudRepository.getById(participantId);
    }

    @Override
    public void registrateDriver(Driver driver) {
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        driver.setCoordinates(CoordinatesGenerator.generate());
        userCrudRepository.save(driver);
        int id = userCrudRepository.getByName(driver.getName()).getId();
        driver.setId(id);
        userCrudRepository.saveDriver(driver);
    }
}
