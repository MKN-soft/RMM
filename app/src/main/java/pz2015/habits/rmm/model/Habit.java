package pz2015.habits.rmm.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2015-05-11.
 * Represents habit. It's a habit model
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
    int series;
    int resourceName;

    Context context;

    List<HabitDefinition> definedHabits = new ArrayList<>();


    public Habit() {
    }

    public Habit(String title, String description, String frequency, Drawable image, String notes, String date, int series) {
        this.title = title;
        this.description = description;
        this.frequency = frequency;
        this.image = image;
        this.notes = notes;
        this.date = date;
        this.series = series;
    }

    public Habit(Context context, HabitToFile habitToFile) {
        this.id = habitToFile.getId();
        this.title = habitToFile.getTitle();
        this.description = habitToFile.getDescription();
        this.date = habitToFile.getDate();
        this.frequency = habitToFile.getFrequency();
        this.context = context;
        this.image = habitToFile.getImageToDrawable(context);
        this.notes = habitToFile.getNotes();
        this.series = habitToFile.getSeries();
        this.resourceName = habitToFile.getResourceName();
        this.definedHabits = habitToFile.definedHabits;
    }

    public List<HabitDefinition> getHabitDefinitions() {
        return definedHabits;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Drawable getImage() {
        return this.image;
    }

    public void setImage(Drawable image, int ResourceName) {

        this.image = image;
        this.resourceName = ResourceName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getResourceName() {
        return resourceName;
    }
}
