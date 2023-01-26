package types;

public enum Type {
    PERSONAL(1, "Личная"),
    WORK(2,"Рабочая");

    private final String type;
    private final int id;

    Type(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {return type; }
    public int getId() {
        return id;
    }
}
