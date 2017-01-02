package eu.needtocode;


public final class ApplicationMessages {

    public static final String USER_ACTIVATION = "User activated successfully";
    public static final String USER_CANNOT_BE_ACTIVATED = "User cannot be activated. Check your activation link and click it again.";
    public static final String NEW_USER_ADDED = "New user added successfully. Activation link was send on you email address";

    public static final String CONFIRMATION_EMAIL_SUBJECT = "Confirm you registration!";
    public static final String CONFIRMATION_EMAIL_CONTENT = "Dear, %s! \n\nPlease confirm you registration by clicking in following link: http://localhost:8080/users/activate/%s";

    private ApplicationMessages() {
    }
}
