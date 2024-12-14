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
    private int exerciseScore;
    private int homeworkScore;
    private int quizScore;



    public Student(String name, String ulearnId, String group, int score, int maxTotalScore, String age,
                   int exerciseScore, int homeworkScore, int quizScore) {
        this.ulearnID = ulearnId;
        this.name = name;
        this.group = group;
        this.totalScore = score;
        this.maxTotalScore = maxTotalScore;
        this.age = age;
        this.topics = new ArrayList<>();
        this.exerciseScore = 0;
        this.homeworkScore = 0;
        this.quizScore = 0;
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

    public int getTotalScore() {
        return totalScore;
    }

    public int getMaxTotalScore() {
        return maxTotalScore;
    }

    public String getAge() {
        return age;
    }

    public int getExerciseScore() {
        return exerciseScore;
    }

    public int getHomeworkScore() {
        return homeworkScore;
    }

    public int getQuizScore() {
        return quizScore;
    }

    public void setExerciseScore(int exerciseScore) {
        this.exerciseScore = exerciseScore;
    }

    public void setHomeworkScore(int homeworkScore) {
        this.homeworkScore = homeworkScore;
    }

    public void setQuizScore(int quizScore) {
        this.quizScore = quizScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Студент: ").append(getName())
                .append("\nUlearnID: ").append(getUlearnId())
                .append("\nГруппа: ").append(getGroup())
                .append("\nБалл за курс: ").append(getTotalScore()).append("/").append(getMaxTotalScore())
//                .append("\nБаллы за упражнения: ").append(getExerciseScore())
//                .append("\nБаллы за домашнее задание: ").append(getHomeworkScore())
//                .append("\nБаллы за контрольные вопросы: ").append(getQuizScore())
                .append("\nВозраст: ").append(getAge()).append("\n");
//        sb.append("\nТемы:\n");
//        for (Topic topic : topics) {
//            sb.append("\t").append(topic.toString()).append("\n");
//        }
        return sb.toString();
    }
}
