package models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String name;
    private final String ulearnID;
    private final String group;
    private final String age;
    private final int totalScore;
    private final int maxTotalScore;
    private final List<Topic> topics;


    public Student(String name, String ulearnId, String group, int score, int maxTotalScore, String age) {
        this.ulearnID = ulearnId;
        this.name = name;
        this.group = group;
        this.age = age;
        this.totalScore = score;
        this.maxTotalScore = maxTotalScore;
        this.topics = new ArrayList<>();
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }

    public String getName() {
        return name;
    }

    public String getUlearnId() {
        return ulearnID;
    }

    public String getGroup() {
        return group;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getMaxTotalScore() {
        return maxTotalScore;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Студент: ").append(getName())
                .append("\nUlearnID: ").append(getUlearnId())
                .append("\nГруппа: ").append(getGroup())
                .append("\nБалл за курс: ").append(getTotalScore())
                .append("\nМаксимальный балл: ").append(getMaxTotalScore())
                .append("\nВозраст: ").append(getAge());
        sb.append("\nТемы:\n");
        for (Topic topic : topics) {
            sb.append("\t").append(topic.toString()).append("\n");
        }
        return sb.toString();
    }
}
