package db.models;


public class Student {
    private final String name;
    private final String ulearnID;
    private final String group;
    private final String age;
    private final int totalScore;
    private final int exerciseScore;
    private final int homeworkScore;
    private final int quizScore;



    public Student(String name, String ulearnId, String group, int score, String age,
                   int exerciseScore, int homeworkScore, int quizScore) {
        this.ulearnID = ulearnId;
        this.name = name;
        this.group = group;
        this.totalScore = score;
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
