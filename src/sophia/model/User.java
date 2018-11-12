package sophia.model;

public class User extends Message {
    private String id;
    private String passwordHash;
    private int age;
    private int love;

    public User() {
        super();
    }

    public User(String id, String passwordHash, int age, int love) {
        super();
        this.id = id;
        this.passwordHash = passwordHash;
        this.age = age;
        this.love = love;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public void addLove(int love){
        this.love += love;
    }

}
