import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description, int id) {
        super(name, description, id, Status.NEW);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtask(int subtaskId) {
        subtaskIds.add(subtaskId);
    }

    public void removeSubtask(int subtaskId) {
        subtaskIds.remove((Integer) subtaskId);
    }

    public void clearSubtasks() {
        subtaskIds.clear();
    }

    @Override
    public String toString() {
        return String.format("Epic{id=%d, name='%s', status=%s, subtaskIds=%s}", id, name, status, subtaskIds);
    }
}
