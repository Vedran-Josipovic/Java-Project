package app.prod.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an employee in the system, incorporating personal contact information,
 * position within the company, and associated projects and tasks.
 * Extends the {@code Contact} class to include contact details.
 */
public class Employee extends Contact {
    private String position;
    private Set<Project> projects;
    private Set<Task> tasks;
    private List<Transaction> transactions;

    public Employee(Long id, String name, String email, Address address, String position, Set<Project> projects, Set<Task> tasks, List<Transaction> transactions) {
        super(id, name, email, address);
        this.position = position;
        this.projects = projects;
        this.tasks = tasks;
        this.transactions = transactions;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getPosition(), employee.getPosition()) && Objects.equals(getProjects(), employee.getProjects()) && Objects.equals(getTasks(), employee.getTasks()) && Objects.equals(getTransactions(), employee.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPosition(), getProjects(), getTasks(), getTransactions());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "position='" + position + '\'' +
                ", projects=" + projects +
                ", tasks=" + tasks +
                ", transactions=" + transactions +
                "} " + super.toString();
    }
}
