package app.prod.model;

import app.prod.enumeration.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Represents a project with defined start and end dates, status, associated client, and a list of tasks.
 * Extends the {@link Entity} class to include common identifier and name properties.
 */
public class Project extends Entity{
    private LocalDate startDate, deadline;
    private Status status;
    private Client client;
    private List<Task> tasks;

    /**
     * Constructs a new Project instance with specified details.
     *
     * @param id        The unique identifier of the project.
     * @param name      The name of the project.
     * @param startDate The start date of the project.
     * @param deadline  The projected end date of the project.
     * @param status    The current status of the project.
     * @param client    The client associated with the project.
     * @param tasks     The list of tasks associated with the project.
     */
    public Project(Long id, String name, LocalDate startDate, LocalDate deadline, Status status, Client client, List<Task> tasks) {
        super(id, name);
        this.startDate = startDate;
        this.deadline = deadline;
        this.status = status;
        this.client = client;
        this.tasks = tasks;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Project project = (Project) o;
        return Objects.equals(getStartDate(), project.getStartDate()) && Objects.equals(getDeadline(), project.getDeadline()) && getStatus() == project.getStatus() && Objects.equals(getClient(), project.getClient()) && Objects.equals(getTasks(), project.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStartDate(), getDeadline(), getStatus(), getClient(), getTasks());
    }

    @Override
    public String toString() {
        return "Project{" +
                "startDate=" + startDate +
                ", deadline=" + deadline +
                ", status=" + status +
                ", client=" + client +
                ", tasks=" + tasks +
                "} " + super.toString();
    }
}
