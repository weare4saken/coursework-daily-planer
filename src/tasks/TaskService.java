package tasks;

import tasks.byTime.*;
import possibleExceptions.IncorrectArgumentException;
import possibleExceptions.TaskNotFoundException;
import typesOfTasks.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import static possibleExceptions.CheckCorrectInput.*;

public class TaskService {

    private static final Map<Integer, Task> taskMap = new HashMap<>();
    private static final List<Task> removedTasks = new ArrayList<>();

    public static Scanner scnr = new Scanner(System.in);

    public static void startDailyPlanner() throws IncorrectArgumentException, TaskNotFoundException {

        try {
            while (true) {
                printMenu();
                if (scnr.hasNextInt()) {
                    int menu = scnr.nextInt();
                    switch (menu) {
                        case 1:
                            //Добавить задачу
                            addTask();
                            break;
                        case 2:
                            //Удалить задачу
                            removeTask();
                            break;
                        case 3:
                            //Вывести все задачи на конкретную дату
                            getAllByDate();
                            break;
                        case 4:
                            //Изменить заголовок задачи
                            updateTitle();
                            break;
                        case 5:
                            //Изменить описание задачи
                            updateDescription();
                            break;
                        case 6:
                            //Показать список удаленных задач
                            getRemovedTasks();
                            break;
                        case 7:
                            //Показать список всех задач, сгруппированных по дате
                            getAllGroupByDate();
                            break;
                        case 8:
                            //Показать следующую дату выполнения задачи
                            getDateRepeat();
                            break;
                        case 0:
                            //Выйти из программы
                            System.exit(0);
                        default:
                            throw new IncorrectArgumentException("Выберите из предложенных пунктов в меню\n");
                    }
                }
            }
        } catch (IncorrectArgumentException | TaskNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            startDailyPlanner();
        }
    }

    public static void printMenu() {
        System.out.println("Выберите действие:\n" +
                "1. Добавить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Показать список задач на день\n" +
                "4. Редактировать заголовок задачи\n" +
                "5. Редактировать описание задачи\n" +
                "6. Показать список удалённых задач\n" +
                "7. Показать список задач по датам\n" +
                "8. Показать следующую дату выполнения задачи\n" +
                "0. Выход"
        );
    }

    public static void addTask() throws IncorrectArgumentException {

        try {
            scnr.nextLine();

            //Заголовок
            System.out.println("Введите заголовок задачи:");
            //Проверка отдельным методом корректности ввода заголовка
            String title = checkStringCorrect(scnr.nextLine());

            //Описание
            System.out.println("Введите описание задачи:");
            //Проверка отдельным методом корректности ввода описания
            String description = checkStringCorrect(scnr.nextLine());

            //Тип задачи
            System.out.println("Введите тип задачи:\n" +
                    "1 - Личная\n" +
                    "2 - Рабочая");
            int typeId = scnr.nextInt();
            if (typeId > 2) {
                throw new IncorrectArgumentException("Некорректный ввод");
            }
            //Изначально type - пустой
            Type type = null;
            if (typeId == Type.PERSONAL.getId()) {
                type = Type.PERSONAL;
            } else if (typeId == Type.WORK.getId()) {
                type = Type.WORK;
            }

            //Дата и время
            System.out.println("Введите дату и время выполнения задачи\n" +
                    "Введите день: ");
            int day = checkIntegerCorrect(scnr.nextInt());
            System.out.println("Введите месяц: ");
            int month = checkIntegerCorrect(scnr.nextInt());
            System.out.println("Введите год: ");
            int year = checkIntegerCorrect(scnr.nextInt());
            System.out.println("Введите часы: ");
            int hour = checkIntegerCorrect(scnr.nextInt());
            System.out.println("Введите минуты: ");
            int minute = checkIntegerCorrect(scnr.nextInt());

            LocalDateTime dateTime = null;
            if (year < LocalDate.now().getYear())
                throw new IncorrectArgumentException("Вы ввели год, который уже прошел\n");
            else if (year == LocalDate.now().getYear() || year > LocalDate.now().getYear())
                    if (month < LocalDate.now().getMonthValue())
                        throw new IncorrectArgumentException("Вы ввели месяц, который уже прошел\n");
                    else if (month == LocalDate.now().getMonthValue())
                            if (day < LocalDate.now().getDayOfMonth())
                                throw new IncorrectArgumentException("Вы ввели день, который уже прошел\n");
                            else if (month > LocalDate.now().getMonthValue()) {
                            }
            dateTime = LocalDateTime.of(year, month, day, hour, minute);

            //Повторяемость
            System.out.println("Введите повторяемость задачи:\n" +
                    "1 - Однократная\n" +
                    "2 - Ежедневная\n" +
                    "3 - Еженедельная\n" +
                    "4 - Ежемесячная\n" +
                    "5 - Ежегодная");
            int repeat = scnr.nextInt();
            if (repeat > 5) {
                throw new IncorrectArgumentException("Некорректный ввод");
            }

            //создание экземпляра класса Task
            createTask(repeat, title, description, type, dateTime);
            System.out.println("Задача " + "\"" + title + "\"" + " успешно добавлена\n");

        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //согласно цифре повторяемости - создаем задачу
    public static void createTask(int repeat, String title, String description, Type type, LocalDateTime dateTime)
            throws IncorrectArgumentException {
        switch (repeat) {
            case 1:
                OneTimeTask oneTimeTask = new OneTimeTask(title, description, type, dateTime);
                taskMap.put(oneTimeTask.getId(), oneTimeTask);
                break;
            case 2:
                DailyTask dailyTask = new DailyTask(title, description, type, dateTime);
                taskMap.put(dailyTask.getId(), dailyTask);
                break;
            case 3:
                WeeklyTask weeklyTask = new WeeklyTask(title, description, type, dateTime);
                taskMap.put(weeklyTask.getId(), weeklyTask);
                break;
            case 4:
                MonthlyTask monthlyTask = new MonthlyTask(title, description, type, dateTime);
                taskMap.put(monthlyTask.getId(), monthlyTask);
                break;
            case 5:
                YearlyTask yearlyTask = new YearlyTask(title, description, type, dateTime);
                taskMap.put(yearlyTask.getId(), yearlyTask);
                break;
            default:
                throw new IncorrectArgumentException("Некорректный ввод");
        }
    }

    public static void removeTask() throws TaskNotFoundException {
        try {
            System.out.println("Список текущих задач: ");
            printActualTasks();

            System.out.println("Для удаления задачи введите её id: ");
            int id = scnr.nextInt();
            if (taskMap.containsKey(id)) {
                //когда задача удаляется, она сразу добавляется в removeTasks
                removedTasks.add(getTaskMap().get(id));
                taskMap.remove(id);
                System.out.println("Задача успешно удалена\n");
            } else {
                throw new TaskNotFoundException("Задача с таким id не найдена или была удалена ранее\n");
            }

        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    //метод для отображения списка всех задач, чтоб с ними можно было работать по id
    public static void printActualTasks() {
        for (Task task : taskMap.values()) {
            System.out.println(task);
        }
    }

    public static void getAllByDate() throws IncorrectArgumentException {
        try {
            System.out.println("Введите день:");
            int day = checkIntegerCorrect(scnr.nextInt());
            System.out.println("Введите месяц:");
            int mounth = checkIntegerCorrect(scnr.nextInt());
            System.out.println("Введите год:");
            int year = checkIntegerCorrect(scnr.nextInt());

            LocalDate dateTime = LocalDate.of(year, mounth, day);

            List<Task> tasksByDay = findTasksByDay(dateTime);
            System.out.println("События на " + dateTime + ":");
            for (Task task : tasksByDay) {
                System.out.println(task);
            }

        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Task> findTasksByDay(LocalDate dateTime) {
        List<Task> tasks = new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            if (entry.getValue().appearsIn(dateTime.atStartOfDay())) {
                tasks.add(entry.getValue());
            }
        }
        return tasks;
    }

     /* for (Task task : taskMap.values()) {
            if (task.appearsIn(dateTime.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }*/

    public static void updateTitle() throws IncorrectArgumentException, TaskNotFoundException {
        try {
            printActualTasks();

            System.out.println("Введите id задачи, которую хотите отредактировать: ");
            int idEditTask = scnr.nextInt();
            if (!taskMap.containsKey(idEditTask)) {
                throw new TaskNotFoundException("Задача с таким id не найдена или была удалена ранее\n");
            } else {
                scnr.nextLine();
                System.out.println("Введите новый заголовок задачи: ");
                String newTitle = checkStringCorrect(scnr.nextLine());
                TaskService.getTaskMap().get(idEditTask).setTitle(newTitle);
            }

        } catch (IncorrectArgumentException | TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateDescription() throws IncorrectArgumentException, TaskNotFoundException {
        try {
            printActualTasks();

            System.out.println("Введите id задачи, которую хотите отредактировать: ");
            int idEditTask = scnr.nextInt();
            if (!taskMap.containsKey(idEditTask)) {
                throw new TaskNotFoundException("Задача с таким id не найдена или была удалена ранее");
            } else {
                scnr.nextLine();
                System.out.println("Введите новое описание задачи: ");
                String newDescription = checkStringCorrect(scnr.nextLine());
                TaskService.getTaskMap().get(idEditTask).setDescription(newDescription);
            }

        } catch (IncorrectArgumentException | TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getRemovedTasks() {
        System.out.println(removedTasks);
    }

    public static void getAllGroupByDate() {
        Map<LocalDate, ArrayList<Task>> taskMapGroupedByDate = new HashMap<>();

        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            Task newTask = entry.getValue();
            LocalDate newLocalDate = newTask.getDateTime().toLocalDate();

            if (taskMapGroupedByDate.containsKey(newLocalDate)) {
                ArrayList<Task> tasks = taskMapGroupedByDate.get(newLocalDate);
                tasks.add(newTask);
            } else {
                taskMapGroupedByDate.put(newLocalDate, new ArrayList<>(Collections.singletonList(newTask)));
            }
        }
        for (Map.Entry<LocalDate, ArrayList<Task>> taskEntry : taskMapGroupedByDate.entrySet()) {
            System.out.println(taskEntry.getKey() + " : " + taskEntry.getValue());
        }
    }

    public static void getDateRepeat() {
        printActualTasks();
        System.out.println("Введите айди задачи: ");
        int idTask = scnr.nextInt();
        LocalDateTime dateRepeat = taskMap.get(idTask).getDateRepeatTask(taskMap.get(idTask).getDateTime());
        System.out.println("Следующая дата выполнения задачи: " + dateRepeat);
    }

    public static Map<Integer, Task> getTaskMap() {
            return taskMap;
    }

}
