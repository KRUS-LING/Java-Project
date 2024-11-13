package models;

public record Topic(String topicName, int exercisePoints, int homeworkPoints, int controlQuestionsPoints,
                    int maxExercisePoints, int maxHomeworkPoints, int maxControlQuestionsPoints) {

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
