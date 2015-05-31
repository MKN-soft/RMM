package pz2015.habits.rmm.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    Drawable image;
    String notes;

    List<HabitDefinition> definedHabits = new ArrayList<HabitDefinition>();

    public Habit() { }

    public Habit(String title, String description, String frequency, Drawable image, String notes, String date) {
        this.title = title;
        this.description = description;
        this.frequency = frequency;
        this.image = image;
        this.notes = notes;
        this.date = date;
    }

    public List<HabitDefinition> getHabitDefinitions(){
        return definedHabits;
    }

    public String getId() { return this.id; }

    public String getTitle() { return this.title; }

    public String getDescription() { return this.description; }

    public String getFrequency() { return this.frequency; }

    public String getDate() { return date; }

    public Drawable getImage() { return this.image; }

    public String getNotes() { return notes; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setDate(String date) { this.date = date; }

    public void setImage(Drawable image) {this.image = image;}

    public void setNotes(String notes) { this.notes = notes; }

}
