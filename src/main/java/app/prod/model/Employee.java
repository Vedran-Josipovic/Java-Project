package app.prod.model;

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

    /**
     * Constructs a new Employee instance with specified details including projects and tasks.
     *
     * @param id       The unique identifier for the employee.
     * @param name     The name of the employee.
     * @param email    The email address of the employee.
     * @param address  The {@code Address} object representing the employee's physical address.
     * @param position The position or role of the employee within the company.
     * @param projects A set of projects associated with the employee.
     * @param tasks    A set of tasks assigned to the employee.
     */
    public Employee(Long id, String name, String email, Address address, String position, Set<Project> projects, Set<Task> tasks) {
        super(id, name, email, address); // Correctly pass the Address object
        this.position = position;
        this.projects = projects;
        this.tasks = tasks;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getPosition(), employee.getPosition()) && Objects.equals(getProjects(), employee.getProjects()) && Objects.equals(getTasks(), employee.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPosition(), getProjects(), getTasks());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "position='" + position + '\'' +
                ", projects=" + projects +
                ", tasks=" + tasks +
                "} " + super.toString();
    }
}
