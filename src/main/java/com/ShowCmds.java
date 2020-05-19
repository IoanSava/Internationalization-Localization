package com;

/**
 * This command can be used to see the
 * available commands
 * Format: 'show-cmds'
 *
 * @author Ioan Sava
 */
public class ShowCmds extends Command {

    public ShowCmds(String command) {
        super(command);
    }

    @Override
    public String execute(Object... arguments) {
        return "Commands: " + arguments[0];
    }
}
