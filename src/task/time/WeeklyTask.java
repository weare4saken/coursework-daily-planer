package task.time;

import types.Type;

import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDateTime offerDate) {
        return (offerDate.toLocalDate().equals(getDateTime().toLocalDate()) ||
                offerDate.isAfter(getDateTime().toLocalDate().atStartOfDay())) &&
                getDateTime().getDayOfWeek().equals(offerDate.getDayOfWeek());
    }

    @Override
    public LocalDateTime getDateRepeatTask(LocalDateTime localDateTime) {
        return localDateTime.plusWeeks(1);
    }

}
