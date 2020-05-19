package app;

import com.*;
import helpers.CommandsUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.*;

public class LocaleExplorer {
    private final String baseName = "Messages";
    private Locale locale;
    private ResourceBundle resourceBundle;
    private Shell shell;
    private CommandsUtil commandsUtil;

    public static void main(String[] args) {
        new LocaleExplorer();
    }

    public LocaleExplorer() {
        init();
        boolean running = true;
        while (running) {
            System.out.print(getMessage("prompt"));
            Map<String, Object> commandMap = readCommand();
            String response = executeCommand(commandMap);

            if (response != null) {
                System.out.println(response);
                if (response.equals("Goodbye")) {
                    running = false;
                }
            }
        }
    }

    private void init() {
        this.locale = Locale.getDefault();
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale);
        this.commandsUtil = new CommandsUtil();
        this.shell = getShell();
    }

    /**
     * Load a command class using Reflection API
     */
    private Command buildCommandClass(String commandName, String... arguments) {
        Command commandInstance = null;
        try {
            Class<?> commandClass = Class.forName(commandsUtil.getPropertyName(commandName + ".impl"));

            Class<?>[] signature = (arguments.length == 0 ? new Class[]{String.class} : new Class[]{String.class, String.class});

            Constructor<?> constructor = commandClass.getConstructor(signature);

            if (arguments.length == 0) {
                commandInstance = (Command) constructor.newInstance(commandsUtil.getPropertyName(commandName + ".command"));
            } else {
                commandInstance = (Command) constructor.newInstance(commandsUtil.getPropertyName(commandName + ".command"), arguments[0]);
            }
        } catch (InstantiationException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return commandInstance;
    }

    /**
     * Create a custom shell
     */
    private Shell getShell() {
        Shell shell = new Shell();

        shell.addCommand(buildCommandClass("display-locales"));
        shell.addCommand(buildCommandClass("set-locale", "tag"));
        shell.addCommand(buildCommandClass("info", "tag"));
        shell.addCommand(new ShowCmds("show-cmds"));
        shell.addCommand(new Exit("exit"));

        return shell;
    }

    public String getMessage(String key, String... arguments) {
        String pattern = resourceBundle.getString(key);
        return new MessageFormat(pattern).format(arguments);
    }

    public Map<String, Object> readCommand() {
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();

        String[] commandArguments = request.split(" ", 2);
        Command command = shell.getCommand(commandArguments[0]);

        Map<String, Object> commandMap = new HashMap<>();
        commandMap.put("command", command);
        commandMap.put("arguments", commandArguments);
        return commandMap;
    }

    public String executeCommand(Map<String, Object> commandMap) {
        Command command = (Command) commandMap.get("command");
        if (command == null) {
            return getMessage("invalid");
        } else if (command.getCommand().equals("show-cmds")) {
            return command.execute(this.shell);
        } else if (command.getCommand().equals(commandsUtil.getPropertyName("display-locales.command"))) {
            return command.execute(this);
        } else if (command.getCommand().equals(commandsUtil.getPropertyName("set-locale.command"))) {
            String tag = ((String[]) commandMap.get("arguments"))[1];
            return command.execute(this, tag);
        } else if (command.getCommand().equals(commandsUtil.getPropertyName("info.command"))) {
            String[] arguments = ((String[]) commandMap.get("arguments"));
            if (arguments.length > 1) {
                return command.execute(this, arguments[1]);
            }
            return command.execute(this);
        }

        return command.execute();
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(String tag) {
        this.locale = (Locale.forLanguageTag(tag));
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale);
    }
}
