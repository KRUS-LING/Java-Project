import com.opencsv.exceptions.CsvException;
import models.Student;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;

import static csvParser.CsvParser.CSVToStudents;

public class Main {
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

