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
    String frequency;
    int image;

    public Habit() { }

    public Habit(String title, String description, String frequency) {
        this.title = title;
        this.description = description;
        this.frequency = frequency;
        //this.image = image;
    }

    public String getId() { return this.id; }

    public String getTitle() { return this.title; }

    public String getDescription() { return this.description; }

    public String getFrequency() { return this.frequency; }

    public String getDate() { return date; }

    public int getIcon() { return this.image; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFrequency(String description) {
        this.frequency = frequency;
    }

    public void setIcon(int icon) {this.image = icon;}

}
