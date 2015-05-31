package pz2015.habits.rmm.model;

/**
 * Created by Natka on 2015-05-30.
 */
public class HabitDefinition {
    private int year;
    private int monthOfYear; // 0 - 11
    private int dayOfMonth; // 1 - 31
    private boolean done;

    public HabitDefinition(int y, int m, int d) {
        year = y;
        monthOfYear = m;
        dayOfMonth = d;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return monthOfYear;
    }

    public int getDay() {
        return dayOfMonth;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean val) {
        done = val;
    }
}
