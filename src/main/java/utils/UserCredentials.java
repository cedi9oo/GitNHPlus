package utils;

/**
 * This class determines which rights, which user has
 */
public class UserCredentials {

    private static String user;

    /**
     * This method is a setter method, this is used to be able to use the logged in user.
     * @param loggedinUser
     */
    public static void setUser(final String loggedinUser) {
        if (user != null) {
            throw new RuntimeException("User may not be reset after login");
        }
        user = loggedinUser;
    }

    /**
     * This method is a getter method to get the logged in user
     * @return
     */
    public static String getUser(){
        return user;
    }

    /**
     * This method determines which user is allowed to lock and unlock data
     * @return True or False which user allowed to lock ond unlock
     */
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

    /**
     * This method determines which user can see locked data
     * @return True or False which user allowed to lock ond unlock
     */
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

    /**
     * This method determines which user may delete data
     * @return True or False which user allowed to lock ond unlock
     */
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

    /**
     * This method determines which user may create caregivers
     * @return True or False which user allowed to lock ond unlock
     */
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

    /**
     * This method determines which user may modify caregivers
     * @return True or False which user allowed to lock ond unlock
     */
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
