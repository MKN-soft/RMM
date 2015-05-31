package pz2015.habits.rmm.model;

import java.io.Serializable;

/**
 * Created by ASUS on 2015-05-31.
 */
public class Statistic implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    String date;
    String frequency;

    public Statistic(Habit habit) {
        this.id = habit.getId();
        this.date = habit.getDate();
        this.frequency = habit.getFrequency();
    }

    public String getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public String getFrequency() {
        return this.frequency;
    }

}
