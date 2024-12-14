package db;

import db.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBRepository {

    private static final String DB_URL = "jdbc:sqlite:students.db";
    private static Connection connection = null;

    // Подключение к базе данных
    public static void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Соединение с базой данных установлено.");
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    // Отключение от базы данных
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение с базой данных закрыто.");
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
            }
        }
    }

    // Создание таблицы студентов
    public static void createTableStudents() {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    ulearnID TEXT,
                    studentGroup TEXT,
                    totalScore INTEGER,
                    age TEXT,
                    exerciseScore INTEGER,
                    homeworkScore INTEGER,
                    quizScore INTEGER
                )
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица students проверена/создана.");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы students: " + e.getMessage());
        }
    }

    // Сохранение студентов в базу данных
    public static void saveStudent(List<Student> students) {
        String sql = """
            INSERT INTO students (name, ulearnID, studentGroup, totalScore, age, exerciseScore, homeworkScore, quizScore)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;


        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Student student : students) {
                pstmt.setString(1, student.getName());
                pstmt.setString(2, student.getUlearnId());
                pstmt.setString(3, student.getGroup());
                pstmt.setInt(4, student.getTotalScore());
                pstmt.setString(5, student.getAge());
                pstmt.setInt(6, student.getExerciseScore());
                pstmt.setInt(7, student.getHomeworkScore());
                pstmt.setInt(8, student.getQuizScore());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("Данные студентов сохранены в базе данных.");
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении студентов: " + e.getMessage());
        }
    }

    // Метод для получения студентов из базы данных
    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students"; // SQL-запрос для получения всех студентов

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Получаем данные из ResultSet
                String name = rs.getString("name");
                String ulearnID = rs.getString("ulearnID");
                String studentGroup = rs.getString("studentGroup");
                int totalScore = rs.getInt("totalScore");
                String age = rs.getString("age");
                int exerciseScore = rs.getInt("exerciseScore");
                int homeworkScore = rs.getInt("homeworkScore");
                int quizScore = rs.getInt("quizScore");

                // Создаем объект Student и добавляем его в список
                Student student = new Student(name, ulearnID, studentGroup, totalScore, age, exerciseScore, homeworkScore, quizScore);
                students.add(student);

            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении студентов из базы данных: " + e.getMessage());
        }

        return students;
    }
}
