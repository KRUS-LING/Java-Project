package models;

import java.util.List;

public class Course {
    private String courseName;    // Название курса
    private List<Topic> topics;   // Список тем, охватываемых курсом
    private List<Task> tasks;     // Список заданий курса

    public Course(String courseName, List<Topic> topics, List<Task> tasks) {
        this.courseName = courseName;
        this.topics = topics;
        this.tasks = tasks;
    }

    // Геттеры и сеттеры
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public List<Topic> getTopics() { return topics; }
    public void setTopics(List<Topic> topics) { this.topics = topics; }

    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
}