package com;

import app.LocaleExplorer;

/**
 * This command can be used to set
 * the application current locale
 * Format: 'set-locale tag'
 *
 * @author Ioan Sava
 */
public class SetLocale extends Command {
    public SetLocale(String command, String arguments) {
        super(command, arguments);
    }

    @Override
    public String execute(Object... arguments) {
        if (arguments.length != 2) {
            return "Format: set-locale tag";
        }

        LocaleExplorer localeExplorer = (LocaleExplorer) arguments[0];
        String tag = (String) arguments[1];
        if (tag.equals("en") || tag.equals("ro")) {
            localeExplorer.setLocale(tag);
            return localeExplorer.getMessage("locale.set", localeExplorer.getLocale().toString());
        }

        return "Invalid locale tag";
    }
}
