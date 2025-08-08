package task;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String name, String description, int id, Status status, int epicId) {
        super(name, description, id, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return String.format("Subtask{id=%d, name='%s', status=%s, epicId=%d}",
                getId(), getName(), getStatus(), epicId);
    }
}
