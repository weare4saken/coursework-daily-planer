package task.time;

import types.Type;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {
    private final Integer id;
    private String title;
    private String description;
    private Type type;
    private final LocalDateTime dateTime;
    private static int idGenerator = 1;

    public abstract boolean appearsIn(LocalDateTime localDateTime);
    public abstract LocalDateTime getDateRepeatTask(LocalDateTime localDateTime);


    public Task(String title, String description, Type type, LocalDateTime dateTime) {
        this.id = idGenerator++;
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public LocalDateTime getDateTime() { return dateTime; }

    public int getId() { return id; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                ". \"" + title + "\"" +
                " [" + description + "]" +
                ". Тип: " + type.getType() +
                ". Дата выполнения: " + dateTime;
    }
}

