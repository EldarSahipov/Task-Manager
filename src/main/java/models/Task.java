package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import static models.Constant.FORMAT_PATTERN;

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
    public LocalDateTime time;

    @Column(name = "contacts")
    public String contacts;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public Status status;


    public Task(String name, String description, LocalDateTime time, String contacts) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.contacts = contacts;
        status = Status.ACTIVE;
    }

    public Task() {

    }

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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nЗадача \nИмя: " + name
                + ",\nОписание: " + description
                + ",\nВремя: " + time.format(DateTimeFormatter.ofPattern(FORMAT_PATTERN))
                + ",\nКонтакты: " + contacts
                + ",\nСтатус: " + status
                + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                name.equals(task.name) &&
                description.equals(task.description) &&
                time.equals(task.time) &&
                contacts.equals(task.contacts) &&
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, time, contacts, status);
    }
}
