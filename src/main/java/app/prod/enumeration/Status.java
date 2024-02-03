package app.prod.enumeration;

/**
 * Represents the possible states of a task or project in the system.
 */
public enum Status {

    /**
     * Indicates that the task is yet to be started.
     */
    TO_DO,

    /**
     * Indicates that the task is currently being worked on.
     */
    IN_PROGRESS,

    /**
     * Indicates that the task is temporarily paused.
     */
    ON_HOLD,

    /**
     * Indicates that the task has been completed.
     */
    DONE;

    /**
     * Checks if the task is in the DONE state.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.equals(DONE);
    }

    /**
     * Checks if the task is in the IN_PROGRESS state.
     *
     * @return {@code true} if the task is in progress, {@code false} otherwise.
     */
    public boolean isInProgress() {
        return this.equals(IN_PROGRESS);
    }
}
