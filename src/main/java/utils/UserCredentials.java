package utils;

public class UserCredentials {

    private static String user;

    public static void setUser(final String loggedinUser) {
        if (user != null) {
            throw new RuntimeException("User may not be reset after login");
        }
        user = loggedinUser;
    }

    public static String getUser(){
        return user;
    }

    public static boolean mayLockAndUnlock() {
        switch (user) {
            case "admin":
                return true;
            case "user":
                return false;
            default:
                return false;
        }
    }

    public static boolean maySeeLockedElements() {
        switch (user) {
            case "admin":
                return true;
            case "user":
                return false;
            default:
                return false;
        }
    }

    public static boolean mayDeleteElements() {
        switch (user) {
            case "admin":
                return true;
            case "user":
                return false;
            default:
                return false;
        }
    }

    public static boolean mayCreateCaregivers() {
        switch (user) {
            case "admin":
                return true;
            case "user":
                return false;
            default:
                return false;
        }
    }

    public static boolean mayModifyCaregivers() {
        switch (user) {
            case "admin":
                return true;
            case "user":
                return false;
            default:
                return false;
        }
    }
}
