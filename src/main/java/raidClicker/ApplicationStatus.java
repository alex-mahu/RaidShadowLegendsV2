package raidClicker;

public final class ApplicationStatus {
    private static boolean smart = true;
    private static int numberOfRuns = -1;

    private ApplicationStatus() {
    }

    public static boolean isSmart() {
        return smart;
    }

    public static void setSmart(boolean smart) {
        ApplicationStatus.smart = smart;
    }

    public static void setNumberOfRuns(String numberOfRuns) {
        try {
            ApplicationStatus.numberOfRuns = Integer.parseInt(numberOfRuns);
        } catch (NumberFormatException ignore) {
        }
    }

    public static int getNumberOfRuns() {
        return numberOfRuns;
    }

    public static boolean usesNumberOfRuns() {
        return numberOfRuns > 0;
    }
}
