package raidClicker.helpers;

public final class StringHelpers {

    public static Integer tryParseOrDefault(String textToParse, Integer defaultValue) {
        try {
            return Integer.parseInt(textToParse);
        } catch (NumberFormatException ignore) {
            return defaultValue;
        }
    }

}
