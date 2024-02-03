package app.prod;

public enum Status {
    TO_DO, IN_PROGRESS, ON_HOLD, DONE;

    public boolean isDone() {
        return this.equals(DONE);
    }

    public boolean isInProgress() {
        return this.equals(IN_PROGRESS);
    }

}
