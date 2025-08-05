public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected Status status;

    public Task(String name, String description, int id, Status status) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getId() { return id; }
    public Status getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, name='%s', status=%s}", id, name, status);
    }
}
