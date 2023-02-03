package Service;

import Model.Epic;
import Model.Status;
import Model.SubTask;
import Model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manager {
    public Map<Integer,Task> taskMap = new HashMap<>();
    public Map<Integer,Epic> epicMap = new HashMap<>();
    public Map<Integer,SubTask> subTaskMap = new HashMap<>();
    private int idForAssignment;

    public int assignId(){
        idForAssignment++;
        return idForAssignment;
    }

    public void checkAndChangeOfStatusEpic(Epic epic){
        int countDONEs = 0;
        int countNEW = 0;
        if (epic.getSubTaskList().isEmpty()){
            epic.setStatus(Status.NEW);
        } else {
            for (SubTask subTask : epic.getSubTaskList()) {
                if (subTask.getStatus() == Status.DONE) {
                    countDONEs++;
                } else if (subTask.getStatus() == Status.NEW) {
                    countNEW++;
                }
            }
            if (countDONEs == epic.getSubTaskList().size()){
                epic.setStatus(Status.DONE);
            } else if (countNEW == epic.getSubTaskList().size()){
                epic.setStatus(Status.NEW);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }
        }

    }


    public void printlistOfAllTasks(){
        System.out.println(taskMap);
    }

    public void printlistOfAllEpic(){
        System.out.println(epicMap);
    }

    public void printlistOfAllSubTask(){
        System.out.println(subTaskMap);
    }


    public void clearTaskList(){
        taskMap.clear();
    }

    public void clearEpicList(){
        for (Epic value : epicMap.values()) {
            for (SubTask subTask : value.getSubTaskList()) {
                subTaskMap.remove(subTask.getId());
            }
        }
        epicMap.clear();
    }

    public void clearSubTaskList(){
        subTaskMap.clear();
    }

    public Task gettingTaskById (int id){
        return taskMap.get(id);
    }

    public Epic gettingEpicById (int id){
        return epicMap.get(id);
    }

    public SubTask gettingSubTaskById (int id){
        return subTaskMap.get(id);
    }

    public void creatTask (Task task){
        taskMap.put(task.getId(), task);
    }

    public void creatEpic (Epic epic){
        epicMap.put(epic.getId(), epic);
    }

    public void creatSubTask (SubTask subTask){
        subTaskMap.put(subTask.getId(), subTask);
        epicMap.get(subTask.getEpicId()).getSubTaskList().add(subTask);
    }

    public void updateTask(Task task){
        taskMap.get(task.getId()).setTitle(task.getTitle());
        taskMap.get(task.getId()).setDescription(task.getDescription());
        taskMap.get(task.getId()).setStatus(task.getStatus());
    }

    public void updateEpic (Epic epic){
        epicMap.get(epic.getId()).setTitle(epic.getTitle());
        epicMap.get(epic.getId()).setDescription(epic.getDescription());
        checkAndChangeOfStatusEpic(epic);
    }

    public void updateSubTask(SubTask subTask){
        for (SubTask task : epicMap.get(subTask.getEpicId()).getSubTaskList()) {
            if(task.getId() == subTask.getId()){
                task.setTitle(subTask.getTitle());
                task.setDescription(subTask.getDescription());
                task.setStatus(subTask.getStatus());
            }
        }
        subTaskMap.get(subTask.getId()).setTitle(subTask.getTitle());
        subTaskMap.get(subTask.getId()).setDescription(subTask.getDescription());
        subTaskMap.get(subTask.getId()).setStatus(subTask.getStatus());
        checkAndChangeOfStatusEpic(epicMap.get(subTask.getEpicId()));
    }

    public void removeByIdFromTaskMap (int id){
        taskMap.remove(id);
    }
    
    public void removeByIdFromEpicMap (int id){
        ArrayList<Integer> listToRemoveFromSubTaskMap = new ArrayList<>(); // конструкция для обхода
                                                                          // ConcurrentModificationException
        for (SubTask subTask : subTaskMap.values()) {
            if (subTask.getEpicId() == id){
                listToRemoveFromSubTaskMap.add(subTask.getId());
            }
        }
        for (Integer integer : listToRemoveFromSubTaskMap) {
            subTaskMap.remove(integer);
        }
        epicMap.remove(id);
    }
    
    public void removeByIdFromSubTaskMap (int id) {
        epicMap.get(subTaskMap.get(id).getEpicId()).getSubTaskList().remove(id);
        subTaskMap.remove(id);
        checkAndChangeOfStatusEpic(epicMap.get(subTaskMap.get(id).getEpicId()));
    }

    public String getAListOfAllSubtasksOfEpic(Epic epic){
        return epic.getSubTaskList().toString();
    }

}
