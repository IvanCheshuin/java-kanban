import manager.TaskManager;
import task.Task;
import task.Epic;
import task.Subtask;
import task.Status;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создаём обычные задачи
        Task task1 = new Task("Переезд", "Собрать вещи", 0, Status.NEW);
        Task task2 = new Task("Покупка мебели", "Выбрать стол", 0, Status.IN_PROGRESS);
        task1 = manager.createTask(task1);
        task2 = manager.createTask(task2);

        // Эпик 1 с двумя подзадачами
        Epic epic1 = new Epic("Организовать праздник", "Юбилей", 0);
        epic1 = manager.createEpic(epic1); // Получаем актуальный id

        Subtask sub1 = new Subtask("Заказать еду", "Выбрать меню", 0, Status.NEW, epic1.getId());
        Subtask sub2 = new Subtask("Арендовать зал", "Выбрать место", 0, Status.NEW, epic1.getId());
        sub1 = manager.createSubtask(sub1);
        sub2 = manager.createSubtask(sub2);

        // Эпик 2 с одной подзадачей
        Epic epic2 = new Epic("Продажа квартиры", "Сделка", 0);
        epic2 = manager.createEpic(epic2);

        Subtask sub3 = new Subtask("Найти покупателя", "Разместить объявление", 0,
                Status.DONE, epic2.getId());
        sub3 = manager.createSubtask(sub3);

        // Печать всех задач
        System.out.println("\n=== Задачи ===");
        manager.getAllTasks().forEach(System.out::println);

        System.out.println("\n=== Эпики ===");
        manager.getAllEpics().forEach(System.out::println);

        System.out.println("\n=== Подзадачи ===");
        manager.getAllSubtasks().forEach(System.out::println);

        // Обновим статус подзадачи
        sub1.setStatus(Status.DONE);
        sub2.setStatus(Status.DONE);
        manager.updateSubtask(sub1);
        manager.updateSubtask(sub2);

        System.out.println("\n=== Эпики после обновления статусов подзадач ===");
        manager.getAllEpics().forEach(System.out::println);

        // Удалим задачу и эпик
        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic2.getId());

        System.out.println("\n=== После удаления задачи и эпика ===");
        manager.getAllTasks().forEach(System.out::println);
        manager.getAllEpics().forEach(System.out::println);
        manager.getAllSubtasks().forEach(System.out::println);
    }
}
