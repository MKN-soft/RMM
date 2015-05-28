package pz2015.habits.rmm.others;

/**
 * Created by ASUS on 2015-05-28.
 */
public enum Errors {
    // User exists errors
    USER_NOT_EXISTS(2), USER_FOUND(1), USER_JSON_IS_NULL(0), USER_NO_INTERNET_SERVICE(-1);

    private int value;

    private Errors(int value) {
        this.value = value;
    }

    public int getValue() { return this.value;}
}