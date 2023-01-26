package task.time;

import types.Type;

import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDateTime offerDate) {
        return offerDate.toLocalDate().equals(getDateTime().toLocalDate()) ||
        offerDate.isAfter(getDateTime().toLocalDate().atStartOfDay());
    }

    @Override
    public LocalDateTime getDateRepeatTask(LocalDateTime localDateTime) {
        return localDateTime.plusDays(1);
    }

}
