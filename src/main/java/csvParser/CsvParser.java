package csvParser;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import models.Performance;
import models.Student;
import models.Topic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class CsvParser {

    public static List<String[]> readCSVFile(String file){
        var parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        try (var reader = new CSVReaderBuilder(
                new InputStreamReader(new FileInputStream(file)))
                .withCSVParser(parser)
                .build()) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Student> parseToStudents(List<String[]> values){
        var students = new ArrayList<Student>();

        for(int i = 3; i < values.size(); i++){
            String name = values.get(i)[0]; // Имя и фамилия студента
            String group = values.get(i)[1]; // Группа студента
            int age = 0; // Пока 0, т.к. в таблице нет возраста
            int maxScore = Integer.parseInt(values.get(2)[2]) // Максимальные баллы
                    + Integer.parseInt(values.get(2)[3])
                    + Integer.parseInt(values.get(2)[4])
                    + Integer.parseInt(values.get(2)[5]);
            var score = Integer.parseInt(values.get(i)[2]) // Баллы студента за весь курс
                    + Integer.parseInt(values.get(i)[3])
                    + Integer.parseInt(values.get(i)[4])
                    + Integer.parseInt(values.get(i)[5]);
            var student = new Student(name, group, age, score, maxScore);
            students.add(student);
        }
        return students;
    }

    public static ArrayList<Topic> parseToTopic(List<String[]> values) {
        var topics = new ArrayList<Topic>();

        // Проверяем, что список не пуст и есть хотя бы одна строка
        if (!values.isEmpty()) {
            String[] firstRow = values.getFirst();  // Первая строка

            // Начинаем с 7-го элемента, хз там строки пустые были и ненужная инфа
            for (int i = 7; i < firstRow.length; i++) {
                String topicName = firstRow[i];
                if (topicName != null && !topicName.isEmpty()) {  // Проверяем, что тема не пуста
                    var topic = new Topic(topicName);
                    topics.add(topic);
                }
            }
        }

        return topics;
    }
}
