package app.prod.model;

import app.prod.enumeration.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a project with defined start and end dates, status, associated client, and a list of tasks.
 * Extends the {@link Entity} class to include common identifier and name properties.
 */
public class Project extends Entity {
    private LocalDate startDate, deadline;
    private Status status;
    private Client client;
    private Set<Task> tasks;
    private List<Transaction> transactions;


    public Project(Long id, String name, LocalDate startDate, LocalDate deadline, Status status, Client client, Set<Task> tasks, List<Transaction> transactions) {
        super(id, name);
        this.startDate = startDate;
        this.deadline = deadline;
        this.status = status;
        this.client = client;
        this.tasks = tasks;
        this.transactions = transactions;
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
        Project project = (Project) o;
        return Objects.equals(getStartDate(), project.getStartDate()) && Objects.equals(getDeadline(), project.getDeadline()) && getStatus() == project.getStatus() && Objects.equals(getClient(), project.getClient()) && Objects.equals(getTasks(), project.getTasks()) && Objects.equals(getTransactions(), project.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStartDate(), getDeadline(), getStatus(), getClient(), getTasks(), getTransactions());
    }

    @Override
    public String toString() {
        return "Project{" +
                "startDate=" + startDate +
                ", deadline=" + deadline +
                ", status=" + status +
                ", client=" + client +
                ", tasks=" + tasks +
                ", transactions=" + transactions +
                "} " + super.toString();
    }
}
