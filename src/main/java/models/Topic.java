package models;


public class Topic {
    private final String topicName;
    private final int exercisePoints;
    private final int homeworkPoints;
    private final int controlQuestionsPoints;
    private final int maxExercisePoints;
    private final int maxHomeworkPoints;
    private final int maxControlQuestionsPoints;

    public Topic(String topicName, int exercisePoints, int homeworkPoints, int controlQuestionsPoints, int maxExercisePoints, int maxHomeworkPoints, int maxControlQuestionsPoints) {
        this.topicName = topicName;
        this.exercisePoints = exercisePoints;
        this.homeworkPoints = homeworkPoints;
        this.controlQuestionsPoints = controlQuestionsPoints;
        this.maxExercisePoints = maxExercisePoints;
        this.maxHomeworkPoints = maxHomeworkPoints;
        this.maxControlQuestionsPoints = maxControlQuestionsPoints;
    }

    public String getTopicName() {
        return topicName;
    }

    public int getExercisePoints() {
        return exercisePoints;
    }

    public int getHomeworkPoints() {
        return homeworkPoints;
    }

    public int getControlQuestionsPoints() {
        return controlQuestionsPoints;
    }

    public int getMaxExercisePoints() {
        return maxExercisePoints;
    }

    public int getMaxHomeworkPoints() {
        return maxHomeworkPoints;
    }

    public int getMaxControlQuestionsPoints() {
        return maxControlQuestionsPoints;
    }

    @Override
    public String toString() {

        return "Тема: " + topicName +
                ", Баллы за упражнения: " + exercisePoints + "/" + maxExercisePoints +
                ", Баллы за домашние задания: " + homeworkPoints + "/" + maxHomeworkPoints +
                ", Баллы за контрольные вопросы: " + controlQuestionsPoints + "/" + maxControlQuestionsPoints +
                "  ||  Итого: " + (exercisePoints + homeworkPoints + controlQuestionsPoints) + "/" +
                (maxExercisePoints + maxHomeworkPoints + maxControlQuestionsPoints);
    }
}
