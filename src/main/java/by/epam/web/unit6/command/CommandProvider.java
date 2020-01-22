package by.epam.web.unit6.command;

import by.epam.web.unit6.command.impl.AuthorizationCommand;
import by.epam.web.unit6.command.impl.EditProfileCommand;
import by.epam.web.unit6.command.impl.NoSuchCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.EDIT_PROFILE, new EditProfileCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }
    public Command getCommand (String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        Command command;
        if (null != name) {
            command = commands.get(name);
        }else {
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return command;
    }

}


