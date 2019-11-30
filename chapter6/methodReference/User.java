package chapter6.methodReference;

public class User {
    private int id;
    private String name;
    private String ID;

    public User(int id, String name, String ID) {
        this.id = id;
        this.name = name;
        this.ID = ID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
