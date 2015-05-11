package pz2015.habits.rmm.model;

import java.io.Serializable;

/**
 * Created by ASUS on 2015-05-11.
 */
public class Habit implements Serializable {

    //TODO think about data needed in object.
    //TODO implement all setters
    //TODO date-done pairs

    private static final long serialVersionUID = 1L;

    String id;
    String title;
    String description;
    String date;

    public Habit() { }

    public Habit(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getId() { return this.id; }

    public String getTitle() { return this.title; }

    public String getDescription() { return this.description; }

    public String getDate() { return date; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
