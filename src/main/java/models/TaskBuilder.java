package models;

import java.time.LocalDateTime;

public interface TaskBuilder {
    TaskBuilder setId(int id);
    TaskBuilder setName(String name);
    TaskBuilder setDescription(String description);
    TaskBuilder setTime(LocalDateTime time);
    TaskBuilder setContacts(String contacts);
    TaskBuilder setStatus(String status);
    Task build();
}
