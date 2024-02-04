package app.prod.model;

import app.prod.enumeration.Status;
import app.prod.exception.entityInitializationException;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a task within a project, detailing its description, deadline, status,
 * assignee, and the project it belongs to. This class is intended to be instantiated
 * through its Builder to ensure the integrity of required fields.
 */
public class Task extends Entity {

    //Možda napraviti sučelje koje povezuje ownere taskova sa taskovima
    private String description;
    private LocalDate deadline;
    private Status status;

    /**
     * Private constructor to enforce instantiation through Builder
     */
    private Task() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return Objects.equals(getDescription(), task.getDescription()) && Objects.equals(getDeadline(), task.getDeadline()) && getStatus() == task.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDescription(), getDeadline(), getStatus());
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                "} " + super.toString();
    }

    /**
     * Builder class for {@link Task}. Facilitates the construction of Task instances,
     * ensuring required fields are set before the object is instantiated.
     */
    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private LocalDate deadline;
        private Status status;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDeadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }


        /**
         * Builds and returns a {@link Task} instance with the parameters provided to this builder.
         * Ensures that the task has a non-null ID and a non-empty name before construction.
         *
         * @return A fully constructed {@link Task} instance.
         * @throws entityInitializationException if either the ID is null or the name is empty.
         */
        public Task build() throws entityInitializationException {
            //Pri korištenju tapraviti while petlju sa try-catch
            if (id == null || name == null || name.isEmpty()) {
                throw new entityInitializationException("Task must have a non-null id and a non-empty name.");
            }
            Task task = new Task();
            //Ako dodam validaciju unosa koristiti settere
            task.id = this.id;
            task.name = this.name;
            //Ako dodam validaciju unosa koristiti settere
            task.description = this.description;
            task.deadline = this.deadline;
            task.status = this.status;
            return task;
        }
    }
}
