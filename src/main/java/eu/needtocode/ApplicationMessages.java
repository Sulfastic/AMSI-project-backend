package eu.needtocode;


public final class ApplicationMessages {

    public static final String USER_ACTIVATION = "User activated successfully.";
    public static final String USER_CANNOT_BE_ACTIVATED = "User cannot be activated. Check your activation link and click it again.";
    public static final String NEW_USER_ADDED = "New user added successfully. Activation link was send on you email address";

    public static final String CONFIRMATION_EMAIL_SUBJECT = "Confirm you registration!";
    public static final String CONFIRMATION_EMAIL_CONTENT = "Dear, %s! \n\nPlease confirm you registration by clicking in following link: http://localhost:8080/users/activate/%s";
    public static final String LOGIN_SUCCESSFULLY = "User %s logged successfully!";
    public static final String CANNOT_LOGIN_USER = "Cannot login user: %s. Check your credentials.";
    public static final String USER_IS_NOT_ACTIVE = "Given user: %s is not activated properly. Please check you email and try to active your user.";
    public static final String NO_USER = "There is no user with given nickname: %s";
    public static final String INCORRECT_PASSWORD = "Your password is incorrect. Please check it and put again.";

    private ApplicationMessages() {
    }
}
