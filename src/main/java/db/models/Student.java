package db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "students")
public class Student {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField
    private String ulearnID;

    @DatabaseField
    private String group;

    @DatabaseField
    private int totalScore;

    @DatabaseField
    private String age;

    @DatabaseField
    private int exerciseScore;

    @DatabaseField
    private int homeworkScore;

    @DatabaseField
    private int quizScore;

    // Конструктор по умолчанию для ORMLite
    public Student() {
    }

    public Student(String name, String ulearnID, String group, int totalScore, String age,
                   int exerciseScore, int homeworkScore, int quizScore) {
        this.name = name;
        this.ulearnID = ulearnID;
        this.group = group;
        this.totalScore = totalScore;
        this.age = age;
        this.exerciseScore = exerciseScore;
        this.homeworkScore = homeworkScore;
        this.quizScore = quizScore;
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
                .append("\nБалл за курс: ").append(getTotalScore())
                .append("\nВозраст: ").append(getAge())
                .append("\nБалл за упражнения: ").append(getExerciseScore())
                .append("\nБалл за практики: ").append(getHomeworkScore())
                .append("\nБалл за контрольные вопросы: ").append(getQuizScore()).append("\n");

        return sb.toString();
    }
}