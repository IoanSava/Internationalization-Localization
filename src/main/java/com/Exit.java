package com;

/**
 * This command can be used to exit
 * Format: 'exit'
 *
 * @author Ioan Sava
 */
public class Exit extends Command {
    public Exit(String command) {
        super(command);
    }

    @Override
    public String execute(Object... arguments) {
        return "Goodbye";
    }
}
