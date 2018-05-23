package ua.com.guskaya.reflection;

public class Company {
    private String name;
    private int employeesNumber;

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", employeesNumber=" + employeesNumber +
                '}';
    }

    private void tryInvoke() {
        System.out.println("It was invoked");
    }

    @Run
    private void testAnnotation() {
        System.out.println("I have @Run annotation");
    }
}
