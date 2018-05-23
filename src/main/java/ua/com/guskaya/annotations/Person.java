package ua.com.guskaya.annotations;

@Table(name = "person")
public class Person {
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
}
