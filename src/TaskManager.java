import java.util.*;

public class TaskManager {
    private int nextId = 1;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    private int generateId() {
        return nextId++;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void clearTasks() {
        tasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void createTask(Task task) {
        task.id = generateId();
        tasks.put(task.id, task);
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void clearEpics() {
        epics.clear();
        subtasks.clear();
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public void createEpic(Epic epic) {
        epic.id = generateId();
        epics.put(epic.id, epic);
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epic.status = calculateEpicStatus(epic);
            epics.put(epic.getId(), epic);
        }
    }

    public void deleteEpicById(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (int subId : epic.getSubtaskIds()) {
                subtasks.remove(subId);
            }
        }
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public List<Subtask> getSubtasksByEpic(int epicId) {
        List<Subtask> result = new ArrayList<>();
        Epic epic = epics.get(epicId);
        if (epic != null) {
            for (int subId : epic.getSubtaskIds()) {
                result.add(subtasks.get(subId));
            }
        }
        return result;
    }

    public void clearSubtasks() {
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
        }
        subtasks.clear();
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public void createSubtask(Subtask subtask) {
        if (epics.containsKey(subtask.getEpicId())) {
            subtask.id = generateId();
            subtasks.put(subtask.id, subtask);
            Epic epic = epics.get(subtask.getEpicId());
            epic.addSubtask(subtask.getId());
            updateEpicStatus(epic);
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            updateEpicStatus(epic);
        }
    }

    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            epic.removeSubtask(id);
            updateEpicStatus(epic);
        }
    }

    private void updateEpicStatus(Epic epic) {
        epic.status = calculateEpicStatus(epic);
    }

    private Status calculateEpicStatus(Epic epic) {
        List<Integer> subIds = epic.getSubtaskIds();
        if (subIds.isEmpty()) return Status.NEW;

        boolean allNew = true;
        boolean allDone = true;

        for (int subId : subIds) {
            Status status = subtasks.get(subId).getStatus();
            if (status != Status.NEW) allNew = false;
            if (status != Status.DONE) allDone = false;
        }

        if (allDone) return Status.DONE;
        if (allNew) return Status.NEW;
        return Status.IN_PROGRESS;
    }
}
