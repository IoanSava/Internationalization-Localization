package com;

import app.LocaleExplorer;
import helpers.RestUtil;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * This command can be used to to display
 * information about the current or a specific locale
 * Format: 'info/ info tag'
 *
 * @author Ioan Sava
 */
public class Info extends Command {
    public Info(String command, String arguments) {
        super(command, arguments);
    }

    private void showInfoForCountry(String tag) {
        Locale locale = new Locale(tag, tag);
        Locale enLocale = Locale.getDefault();

        System.out.println("Country: " + locale.getDisplayCountry(enLocale) + " (" + locale.getDisplayCountry(locale) + ")");
        System.out.println("Language: " + locale.getDisplayLanguage(enLocale) + " (" + locale.getDisplayLanguage(locale) + ")");
        System.out.println("Currency: " + Currency.getInstance(locale).getDisplayName(enLocale) +
                " (" + Currency.getInstance(locale).getCurrencyCode() + ")");
        String[] weekDays = new DateFormatSymbols(locale).getWeekdays();
        System.out.println("Weekdays: " + Arrays.toString(weekDays));
        String[] months = new DateFormatSymbols(locale).getMonths();
        System.out.println("Months: " + Arrays.toString(months));
        System.out.println("Today: " + DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, locale).format(new Date()));
        System.out.println("Capital: " + RestUtil.getCapitalByCountryCode(locale.getISO3Country()));
        System.out.println("Continent: " + RestUtil.getContinentByCountryCode(locale.getISO3Country()));
    }

    @Override
    public String execute(Object... arguments) {
        LocaleExplorer localeExplorer = (LocaleExplorer) arguments[0];
        String tag = localeExplorer.getLocale().getLanguage();
        if (arguments.length > 1) {
            tag = (String) arguments[1];
        }

        System.out.println(localeExplorer.getMessage("info", tag));
        showInfoForCountry(tag);
        return null;
    }
}
