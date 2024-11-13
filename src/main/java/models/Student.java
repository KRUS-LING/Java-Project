package models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String name;
    private final String ulearnID;
    private final String group;
    //    private final int age;
    private final int totalScore;
    private final int maxTotalScore;
    private final List<Topic> topics;


    public Student(String name, String ulearnId, String group, int score, int maxTotalScore) {
        this.ulearnID = ulearnId;
        this.name = name;
        this.group = group;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Студент: ").append(name)
                .append("\nUlearnID: ").append(ulearnID)
                .append("\nГруппа: ").append(group)
                .append("\nБалл за курс: ").append(totalScore)
                .append("\nМаксимальный балл: ").append(maxTotalScore);
        sb.append("\nВозраст типо ... ;)");
        sb.append("\nТемы:\n");
        for (Topic topic : topics) {
            sb.append("\t").append(topic.toString()).append("\n");
        }
        return sb.toString();
    }
}
