import com.opencsv.exceptions.CsvException;
import db.DBRepository;
import models.Student;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;
import static csvParser.CsvParser.CSVToStudents;


public class Main {

    public static void main(String[] args) {

//        String filePath = "rawData/java-rtf.csv";

        try {
            System.setOut(new PrintStream(System.out, true, Charset.defaultCharset()));

            // Подключаемся к базе данных
            DBRepository.connect();

//            // Создаём таблицы, если их ещё нет
//            DBRepository.createTableStudents();
//
//            // Парсим студентов из CSV
//            List<Student> students = CSVToStudents(filePath);
//
//            // Выводим студентов, полученных из CSV, на консоль
//            System.out.println("Студенты, полученные из CSV:");
//            for (Student student : students) {
//                System.out.println(student.toString());
//            }
//
//            // Сохраняем студентов в базу данных
//            DBRepository.saveStudent(students);

            // Получаем студентов из базы данных
            List<Student> studentsFromDB = DBRepository.getStudents();

            // Выводим студентов, полученных из базы данных, на консоль
            for (Student student : studentsFromDB) {
                System.out.println(student.toString());
            }

//        } catch (IOException | CsvException e) {
//            System.out.println("Ошибка при чтении CSV: " + e.getMessage());
        } finally {
            // Закрываем соединение с базой данных
            DBRepository.disconnect();
        }
    }
}
