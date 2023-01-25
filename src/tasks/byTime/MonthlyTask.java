package tasks.byTime;

import typesOfTasks.Type;

import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDateTime offerDate) {
        return offerDate.toLocalDate().equals(getDateTime().toLocalDate()) ||
                offerDate.toLocalDate().isAfter(getDateTime().toLocalDate()) &&
                offerDate.getDayOfMonth() == getDateTime().getDayOfMonth();
    }

    @Override
    public LocalDateTime getDateRepeatTask(LocalDateTime localDateTime) {
        return localDateTime.plusMonths(1);
    }

}
