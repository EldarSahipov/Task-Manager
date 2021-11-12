package models;

import java.time.LocalDateTime;

public class TaskBuilderImpl implements TaskBuilder {
    Task task = new Task();

    @Override
    public TaskBuilder setId(int id) {
        task.id = id;
        return this;
    }

    @Override
    public TaskBuilder setName(String name) {
        task.name = name;
        return this;
    }

    @Override
    public TaskBuilder setDescription(String description) {
        task.description = description;
        return this;
    }

    @Override
    public TaskBuilder setTime(LocalDateTime time) {
        task.time = time;
        return this;
    }

    @Override
    public TaskBuilder setContacts(String contacts) {
        task.contacts = contacts;
        return this;
    }

    @Override
    public TaskBuilder setStatus(String status) {
        task.status = Status.valueOf(status);
        return this;
    }

    @Override
    public Task build() {
        return task;
    }


}
