# **Internationalization and Localization**

:white_check_mark: Compulsory - all bullets

:white_check_mark:  Optional - all bullets


## Tasks regarding Compulsory part :star:

:heavy_check_mark: Resource bundles (Default and ro)

 - **src/main/resources/Messages.properties**
 - **src/main/resources/Messages_ro.properties**

:heavy_check_mark: Locale Commands

 - **src/main/java/com/DisplayLocales**
 - **src/main/java/com/SetLocale**
 - **src/main/java/com/Info**

:heavy_check_mark: Create the package _app_ and the main class _LocaleExplore_. Inside this class, read commands from the keyboard and execute them

 **- src/main/java/app/LocaleExplorer**

:heavy_check_mark: All the **locale-sensitive** information should be **translated** in at least two languages (english-default and romanian), using the resource files.

 **- src/main/java/app/LocaleExplorer**

## Tasks regarding Optional part :star: :star:

:heavy_check_mark: Map the command names to corresponding classes using an external file  _Commands.properties_.   The names of the commands should also be configurable

 - **src/main/resources/Commands.properties**
 - **src/main/java/app/LocaleExplorer/buildCommandClass**
 - **src/main/java/helpers/CommandsUtil**

:heavy_check_mark: Invoke a REST Web Service to obtain additional information about a country (capital, continent, etc.), for example [DataFlex Web Service for Country information](http://www.oorsprong.org/websamples.countryinfo/CountryInfoService.wso)

 - **src/main/java/helpers/RestUtil**
 - **src/main/java/com/Info**