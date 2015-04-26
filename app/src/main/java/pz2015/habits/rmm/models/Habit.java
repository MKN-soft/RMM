package pz2015.habits.rmm.models;

/**
 * Created by ASUS on 2015-04-26.
 */
public class Habit {

    String id;
    String title;
    String body;

    public Habit() { }

    public Habit(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getId() { return this.id; }

    public String getTitle() { return this.title; }

    public String getBody() { return this.body; }

    public void setTitle(String title){
        this.title = title;
    }

    public void setBody(String body){
        this.body = body;
    }

}
