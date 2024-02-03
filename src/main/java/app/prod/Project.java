package app.prod;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Project extends Entity{
    private LocalDate startDate, endDate;
    private Status status;
    private Client client;
    private List<Task> tasks;

    public Project(Long id, String name, LocalDate startDate, LocalDate endDate, Status status, Client client, List<Task> tasks) {
        super(id, name);
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
        return Objects.equals(getStartDate(), project.getStartDate()) && Objects.equals(getEndDate(), project.getEndDate()) && getStatus() == project.getStatus() && Objects.equals(getClient(), project.getClient()) && Objects.equals(getTasks(), project.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStartDate(), getEndDate(), getStatus(), getClient(), getTasks());
    }

    @Override
    public String toString() {
        return "Project{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", client=" + client +
                ", tasks=" + tasks +
                "} " + super.toString();
    }
}
