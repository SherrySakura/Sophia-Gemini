package java.model;

/**
 * 任务描述类
 */
public class Task {
    private String taskUID;
    private String taskTpye;
    private String taskDiscrible;
    private String taskDeadline;
    private double taskBase;

    public Task() {
    }

    public Task(String taskUID, String taskTpye, String taskDiscrible, String taskDeadline, double taskBase) {
        this.taskUID = taskUID;
        this.taskTpye = taskTpye;
        this.taskDiscrible = taskDiscrible;
        this.taskDeadline = taskDeadline;
        this.taskBase = taskBase;
    }

    public String getTaskUID() {
        return taskUID;
    }

    public void setTaskUID(String taskUID) {
        this.taskUID = taskUID;
    }

    public String getTaskTpye() {
        return taskTpye;
    }

    public void setTaskTpye(String taskTpye) {
        this.taskTpye = taskTpye;
    }

    public String getTaskDiscrible() {
        return taskDiscrible;
    }

    public void setTaskDiscrible(String taskDiscrible) {
        this.taskDiscrible = taskDiscrible;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public double getTaskBase() {
        return taskBase;
    }

    public void setTaskBase(double taskBase) {
        this.taskBase = taskBase;
    }
}
