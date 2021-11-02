package models;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table (name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "time")
    public Calendar time;

    @Column(name = "contacts")
    public String contacts;

    public Task(String name, String description, Calendar time, String contacts) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.contacts = contacts;
    }

    public Task(String name, String description, Calendar time) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.contacts = "";
    }

    public Task() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Task [name: " + name
                + ", description: " + description
                + ", time: " + time.getTime()
                + ", contacts: " + contacts + "]";
    }
}
