package models;

public class Performance {
    private Student student;  // Студент, выполняющий задание
    private Task task;        // Задание, которое выполняет студент
    private int score;        // Баллы, полученные студентом за выполнение задания

    public Performance(Student student, Task task, int score) {
        this.student = student;
        this.task = task;
        this.score = score;
    }

    // Геттеры и сеттеры
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}