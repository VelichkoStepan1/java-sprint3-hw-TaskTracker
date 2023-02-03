package Model;

public class SubTask extends Task{
private int epicId;

    public SubTask(String title, String description, int id, Status status, int epicId) {
        super(title, description, id, status);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Model.SubTask{" + "title='" + super.getTitle() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", idSubTask=" + super.getId() +
                ", EpicId=" + epicId +
                ", status=" + status +
                '}';
    }

    public int getEpicId() {
        return epicId;
    }
}
