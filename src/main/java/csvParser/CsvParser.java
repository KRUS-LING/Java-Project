package csvParser;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import models.Student;
import models.Topic;
import vkAPI.VkApiSearch;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.*;

public class CsvParser {
    public static String Homework;
    public static String Quiz;
    public static String Exercise;

    public static List<Student> CSVToStudents(String filePath) throws IOException, CsvException {
        List<Student> students = new ArrayList<>();
        List<String> topicNames = new ArrayList<>();
        List<Integer> topicIndex = new ArrayList<>();
        Map<String, List<Integer>> topicTask = new HashMap<>();
        Map<Integer, Integer> taskMaxScore = new HashMap<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] topics = reader.readNext();
            String[] tasks = reader.readNext();
            String[] maxScores = reader.readNext();

            int maxTotalScore = Integer.parseInt(maxScores[3]) +
                    Integer.parseInt(maxScores[4]) +
                    Integer.parseInt(maxScores[5]);

            Exercise = tasks[3];
            Homework = tasks[4];
            Quiz = tasks[5];

            List<Integer> currentTask = new ArrayList<>();
            for (int i = 1; i < topics.length; i++) {
                String topic = topics[i].trim();
                if (topic.contains(".")) {
                    if (!topicNames.isEmpty()) {
                        String preTopic = topicNames.getLast();
                        topicTask.put(preTopic, new ArrayList<>(currentTask));
                        currentTask.clear();
                    }
                    topicNames.add(topic);
                    topicIndex.add(i);
                }
                if (i >= 6) {
                    String task = tasks[i].trim();
                    if (Exercise.equals(task) || Homework.equals(task) || Quiz.equals(task)) {
                        currentTask.add(i);
                        if (!maxScores[i].trim().isEmpty() && maxScores[i].matches("\\d+")) {
                            taskMaxScore.put(i, Integer.parseInt(maxScores[i].trim()));
                        } else {
                            taskMaxScore.put(i, 0);
                        }
                    }
                }
            }

            if (!currentTask.isEmpty() && !topicNames.isEmpty()) {
                String lastTopic = topicNames.getLast();
                topicTask.put(lastTopic, new ArrayList<>(currentTask));
            }

            String[] line;
            while ((line = reader.readNext()) != null) {
                students.add(createStudent(line, topicNames, tasks, taskMaxScore, topicTask, maxTotalScore));
            }
        }

        return students;
    }

    private static Student createStudent(String[] studentInfo, List<String> topicNames, String[] tasks,
                                         Map<Integer, Integer> taskMaxScore,
                                         Map<String, List<Integer>> topicTask, int maxTotalScore) {

        String name = studentInfo[0];
        String ulearnID = studentInfo[1];
        String group = studentInfo[2];
        String age = VkApiSearch.getAgeFromStudent(name);


        int totalScore = Integer.parseInt(studentInfo[3]) +
                Integer.parseInt(studentInfo[4]) +
                Integer.parseInt(studentInfo[5]);

        Student student = new Student(name, ulearnID, group, totalScore, maxTotalScore, age);



        for (String topicName : topicNames) {
            int[] scores = new int[3];
            List<Integer> taskInd = topicTask.get(topicName);

            if (taskInd != null) {
                for (Integer index : taskInd) {
                    String taskType = tasks[index].trim();
                    int points = 0;
                    String pointsStr = studentInfo[index];
                    if (!pointsStr.isEmpty() && pointsStr.matches("\\d+")) {
                        points = Integer.parseInt(pointsStr);
                    }

                    if (Exercise.equals(taskType)) {
                        scores[0] += points;
                    } else if (Homework.equals(taskType)) {
                        scores[1] += points;
                    } else if (Quiz.equals(taskType)) {
                        scores[2] += points;
                    }
                }
            }

            int maxExercisePoints = 0;
            int maxHomeworkPoints = 0;
            int maxControlQuestionsPoints = 0;

            if (taskInd != null) {
                for (Integer taskIndex : taskInd) {
                    String taskType = tasks[taskIndex].trim();
                    int maxScore = taskMaxScore.getOrDefault(taskIndex, 0);

                    if (Exercise.equals(taskType)) {
                        maxExercisePoints = maxScore;
                    } else if (Homework.equals(taskType)) {
                        maxHomeworkPoints = maxScore;
                    } else if (Quiz.equals(taskType)) {
                        maxControlQuestionsPoints = maxScore;
                    }
                }
            }

            student.addTopic(new Topic(topicName, scores[0], scores[1], scores[2],
                    maxExercisePoints, maxHomeworkPoints, maxControlQuestionsPoints));
        }

        return student;
    }

    public static void main(String[] args) {
        String filePath = "rawData/java-rtf.csv";
        try {
            System.setOut(new PrintStream(System.out, true, Charset.defaultCharset()));
            List<Student> students = CSVToStudents(filePath);
            for (Student student : students) {
                System.out.println(student.toString());
            }
        } catch (IOException | CsvException e) {
            System.out.println("Ошибка при чтении CSV: " + e.getMessage());
        }
    }
}
