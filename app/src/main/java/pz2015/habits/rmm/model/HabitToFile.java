package pz2015.habits.rmm.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2015-06-01.
 */
public class HabitToFile implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;
    String title;
    String description;
    String date;
    String frequency;
    byte[] image;
    String notes;
    int series;
    int resourceName;
    List<HabitDefinition> definedHabits = new ArrayList<>();

    public HabitToFile() {

    }

    public HabitToFile(Context context, Habit habit) {
        this.id = habit.getId();
        this.title = habit.getTitle();
        this.description = habit.getDescription();
        this.date = habit.getDate();
        this.frequency = habit.getFrequency();

        // Save Image as Byte Array
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), habit.getResourceName());
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        this.image = baos.toByteArray();

        Bitmap bitmap = Bitmap.createBitmap(habit.getImage().getIntrinsicWidth(), habit.getImage().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        this.image = baos.toByteArray();


        this.notes = habit.getNotes();
        this.series = habit.getSeries();
        this.resourceName = habit.getResourceName();
        this.definedHabits = habit.getHabitDefinitions();
    }

    public Drawable getImageToDrawable(Context context) {
        Bitmap bmp = BitmapFactory.decodeByteArray(this.image, 0, this.image.length);
        Drawable drawable = new BitmapDrawable(context.getResources(), bmp);

        return drawable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getNotes() {
        return notes;
    }

    public int getSeries() {
        return series;
    }

    public int getResourceName() {
        return resourceName;
    }
}
