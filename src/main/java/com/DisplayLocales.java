package com;

import app.LocaleExplorer;

import java.util.Locale;

/**
 * This command can be used to display all available locales
 * Format: 'display-locales'
 *
 * @author Ioan Sava
 */
public class DisplayLocales extends Command {
    public DisplayLocales(String command) {
        super(command);
    }

    public String execute(Object... arguments) {
        LocaleExplorer localeExplorer = (LocaleExplorer) arguments[0];
        System.out.println(localeExplorer.getMessage("locales"));
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale currentLocale : availableLocales) {
            System.out.println(currentLocale.getDisplayCountry() + "\t" +
                    currentLocale.getDisplayLanguage(currentLocale));
        }

        return null;
    }
}
