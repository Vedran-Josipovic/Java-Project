package app.prod;

import java.time.LocalDate;
import java.util.Objects;

public class Task extends Entity {
    private String description;
    private LocalDate deadline;
    private Status status;
    private Employee assignee;
    private Project project;

    public Task(Long id, String name, String description, LocalDate deadline, Status status, Employee assignee, Project project) {
        super(id, name);
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.assignee = assignee;
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return Objects.equals(getDescription(), task.getDescription()) && Objects.equals(getDeadline(), task.getDeadline()) && getStatus() == task.getStatus() && Objects.equals(getAssignee(), task.getAssignee()) && Objects.equals(getProject(), task.getProject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDescription(), getDeadline(), getStatus(), getAssignee(), getProject());
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                ", assignee=" + assignee +
                ", project=" + project +
                "} " + super.toString();
    }
}
