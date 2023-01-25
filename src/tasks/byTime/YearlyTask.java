package tasks.byTime;

import typesOfTasks.Type;

import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDateTime offerDate) {
        return offerDate.toLocalDate().equals(getDateTime().toLocalDate()) ||
                offerDate.toLocalDate().isAfter(getDateTime().toLocalDate()) &&
                offerDate.getDayOfYear() == getDateTime().getDayOfYear();
    }

    @Override
    public LocalDateTime getDateRepeatTask(LocalDateTime localDateTime) {
        return localDateTime.plusYears(1);
    }
}
