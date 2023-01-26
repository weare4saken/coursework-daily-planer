package tasks.byTime;

import typesOfTasks.Type;

import java.time.LocalDateTime;

public class OneTimeTask extends Task {

    public OneTimeTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDateTime offerDate) {
        return offerDate.toLocalDate().equals(getDateTime().toLocalDate());
    }

    @Override
    public LocalDateTime getDateRepeatTask(LocalDateTime localDateTime) { return localDateTime; }

}

