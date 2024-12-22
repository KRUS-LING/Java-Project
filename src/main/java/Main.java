import db.DBRepository;
import db.DbOrmRepository;
import db.models.Student;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;

import static db.models.CsvParser.CSVToStudents;

public class Main {

    public static void main(String[] args) throws Exception {

        String filePath = "rawData/java-rtf.csv";
        var dbOrm = new DbOrmRepository();
        dbOrm.connect();
//        dbOrm.createTableStudents();
//        List<Student> students = CSVToStudents(filePath);
//        dbOrm.saveStudent(students);
        for (Student student : dbOrm.getStudents()) {
            System.out.println(student);
        }
        dbOrm.disconnect();

//        try {
//            System.setOut(new PrintStream(System.out, true, Charset.defaultCharset()));
//
//            // Подключаемся к базе данных
//            DBRepository.connect();
//
//            // Создаём таблицы, если их ещё нет
//            DBRepository.createTableStudents();
//
//            // Парсим студентов из CSV
//            List<Student> students = CSVToStudents(filePath);
//
//            // Сохраняем студентов в базу данных
//            DBRepository.saveStudent(students);
//
//            // Получаем студентов из базы данных
//            List<Student> studentsFromDB = DBRepository.getStudents();
//
//            // Выводим студентов, полученных из базы данных, на консоль
//            for (Student student : studentsFromDB) {
//                System.out.println(student.toString());
//            }
//
//        } catch (Exception e) {
//            System.out.println("Произошла ошибка: " + e.getMessage());
//        } finally {
//            // Закрываем соединение с базой данных
//            DBRepository.disconnect();
//        }
    }
}