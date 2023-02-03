import Model.Epic;
import Model.Status;
import Model.SubTask;
import Model.Task;
import Service.Manager;

public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();

        Task task0 = new Task("Купить заварку", " Купить ИванЧай в магазине",
                manager.assignId(), Status.NEW);
        manager.creatTask(task0);
        Task task1 = new Task("Купить печеньки", "Купить шоколадные печеньки в магазине",
                manager.assignId(), Status.NEW);
        manager.creatTask(task1);

        Epic epic0 = new Epic("Вскипятить воду", "Вскипятить воду для чая",
                manager.assignId(), Status.NEW);
        manager.creatEpic(epic0);
        SubTask subTask00 = new SubTask("Налить воду в чайник",
                "налить отфильтрованную воду в чайник чтобы было меньше накипи",
                manager.assignId(), Status.NEW, epic0.getId());
        manager.creatSubTask(subTask00);
        SubTask subTask01 = new SubTask("Поставить чайник на платформу",
                "Поставить чайник на платформу и нажать кнопку вкл",
                manager.assignId(), Status.NEW, epic0.getId());
        manager.creatSubTask(subTask01);

        Epic epic1 = new Epic("Попить чай с печеньками", "Накрыть на стол",
                manager.assignId(), Status.NEW);
        manager.creatEpic(epic1);
        SubTask subTask10 = new SubTask("Поставить печеньки на стол",
                "Выложить печеньки на блюдце и поставить на стол",
                manager.assignId(), Status.NEW, epic1.getId());
        manager.creatSubTask(subTask10);

        System.out.println(task0);
        System.out.println(task1);
        System.out.println(epic0);
        System.out.println(subTask00);
        System.out.println(subTask01);
        System.out.println(epic1);
        System.out.println(subTask10);

        task0.setStatus(Status.IN_PROGRESS);
        task1.setStatus(Status.IN_PROGRESS);

        subTask01.setStatus(Status.IN_PROGRESS);
        subTask10.setStatus(Status.DONE);

        manager.checkAndChangeOfStatusEpic(epic0);
        manager.checkAndChangeOfStatusEpic(epic1);

        System.out.println(task0);
        System.out.println(task1);

        System.out.println(epic0);
        System.out.println(subTask01);

        System.out.println(epic1);
        System.out.println(subTask10);

        manager.printlistOfAllTasks();
        manager.printlistOfAllEpic();

        manager.removeByIdFromTaskMap(task0.getId());
        manager.removeByIdFromEpicMap(epic0.getId());

        manager.printlistOfAllTasks();
        manager.printlistOfAllEpic();

    }
}