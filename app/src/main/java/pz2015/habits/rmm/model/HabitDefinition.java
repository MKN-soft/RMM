package pz2015.habits.rmm.model;

import java.io.Serializable;

/**
 * Class define day of habit, have data and variable, which points on habit done or to do.
 *
 */
public class HabitDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public void setDone(boolean val) {
        done = val;
    }

    public boolean getDone() { return done; }
}
