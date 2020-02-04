package by.epam.buber.controller.util;

import by.epam.buber.controller.util.impl.get.*;
import by.epam.buber.controller.util.impl.post.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.GET_SIGN_IN, new GetSignIn());
        repository.put(CommandName.GET_USER_PAGE, new GetUserPage());
        repository.put(CommandName.GET_SIGN_UP, new GetSignUp());
        repository.put(CommandName.GET_LOGOUT, new GetLogout());
        repository.put(CommandName.GET_MAIN, new GetMain());
        repository.put(CommandName.GET_DRIVER_ORDERS, new GetDriverOrders());
        repository.put(CommandName.GET_CHANGE_DRIVER_NAME, new GetChangeDriverName());
        repository.put(CommandName.GET_CHANGE_DRIVER_PASSWORD, new GetChangeDriverPassword());
        repository.put(CommandName.GET_BUSY, new GetBusy());
        repository.put(CommandName.GET_CURRENT_ORDER, new GetCurrentOrder());
        repository.put(CommandName.GET_DRIVERS, new GetDrivers());
        repository.put(CommandName.GET_CHANGE_USER_NAME, new GetChangeUserName());
        repository.put(CommandName.GET_CHANGE_USER_PASSWORD, new GetChangeUserPassword());
        repository.put(CommandName.GET_NEW_ORDER, new GetNewOrder());
        repository.put(CommandName.GET_USER_ORDER, new GetUserOrder());
        repository.put(CommandName.GET_GET_ALL, new GetGetAll());
        repository.put(CommandName.GET_PARTICIPANT_ID, new GetParticipantId());
        repository.put(CommandName.GET_ADMIN_PAGE, new GetAdminPage());
        repository.put(CommandName.GET_DISCOUNT, new GetDiscount());
        repository.put(CommandName.GET_NEW_DRIVER, new GetNewDriver());

        repository.put(CommandName.POST_SIGN_IN, new PostSignIn());
        repository.put(CommandName.POST_SIGN_UP, new PostSignUp());
        repository.put(CommandName.POST_ACCEPT_ORDER, new PostAcceptOrder());
        repository.put(CommandName.POST_START_TRIP, new PostStartTrip());
        repository.put(CommandName.POST_COMPLETE , new PostComplete());
        repository.put(CommandName.POST_BUSY, new PostBusy());
        repository.put(CommandName.POST_FREE, new PostFree());
        repository.put(CommandName.POST_NEW_ORDER, new PostNewOrder());
        repository.put(CommandName.POST_CHANGE_NAME, new PostChangeName());
        repository.put(CommandName.POST_DRIVERS, new PostDrivers());
        repository.put(CommandName.POST_CHANGE_PASSWORD, new PostChangePassword());
        repository.put(CommandName.POST_CANCEL_ORDER, new PostCancelOrder());
        repository.put(CommandName.POST_BAN, new PostBan());
        repository.put(CommandName.POST_FIND_BY_NAME, new PostFindByName());
        repository.put(CommandName.POST_FIND_BY_ID, new PostFindById());
        repository.put(CommandName.POST_DISCOUNT, new PostDiscount());
        repository.put(CommandName.POST_NEW_DRIVER, new PostNewDriver());
    }

    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        Command command = repository.get(commandName);
        return command;
    }
}
