import db.DBRepository;
import db.models.Student;
import visualisation.PieChartDrawer;
import visualisation.LineChartDrawer;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Подключаемся к базе данных
        DBRepository.connect();

        try {
            // Получаем студентов из базы данных
            List<Student> studentsFromDB = DBRepository.getStudents();

            // Выводим студентов, полученных из базы данных, на консоль
            System.out.println("Студенты, полученные из базы данных:");
            for (Student student : studentsFromDB) {
                System.out.println(student.toString());
            }

            // Рисуем круговую диаграмму
            PieChartDrawer.drawPieChart(studentsFromDB);

            // Рисуем график зависимости баллов от возраста
//            LineChartDrawer.drawLineChart(studentsFromDB);

        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        } finally {
            // Закрываем соединение с базой данных
            DBRepository.disconnect();
        }
    }
}
