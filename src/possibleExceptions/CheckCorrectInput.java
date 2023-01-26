package possibleExceptions;

public class CheckCorrectInput {
    public static String checkStringCorrect(String string) throws IncorrectArgumentException {
        if (string == null || string.isEmpty() || string.isBlank())
            throw new IncorrectArgumentException("Некорректный ввод строки");
        else return string;

    }

    public static Integer checkIntegerCorrect(Integer integer) throws IncorrectArgumentException {
        if (integer == null || integer < 0)
            throw new IncorrectArgumentException("Некорректный ввод числа");
        else return integer;
    }
}
