package Model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{
     List<SubTask> getSubTaskList = new ArrayList<>();

     public Epic(String title, String description, int id, Status status) {
          super(title, description, id, status);
     }

     public void changeOfStatusEpic(){
          int countDones = 0;
          for (SubTask subTask : getSubTaskList) {
               if (subTask.status == Status.DONE) {
                    countDones++;
               }
          }
          if (countDones > 0 || countDones < getSubTaskList.size()){
               status = Status.IN_PROGRESS;
          } else if(countDones == getSubTaskList.size()){
               status = Status.DONE;
          }
     }

     @Override
     public String toString() {
          return "Model.Epic{" + "title='" + super.getTitle() + '\'' +
                  ", description='" + super.getDescription() + '\'' +
                  ", idEpic=" + super.getId() +
                  ", status=" + status +
                  '}';
     }

     public List<SubTask> getSubTaskList() {
          return getSubTaskList;
     }

     public void addSubTaskList(SubTask subTask) {
          getSubTaskList.add(subTask);
     }
}
