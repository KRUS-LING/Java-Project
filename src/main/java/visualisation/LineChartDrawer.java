package visualisation;

import db.models.Student;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LineChartDrawer {

    public static void drawLineCharts(List<Student> students) {
        // Создаем 4 отдельных набора данных для каждого графика
        DefaultCategoryDataset exercisesDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset homeworkDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset quizDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset totalScoreDataset = new DefaultCategoryDataset();

        // Сортируем студентов по возрасту (по возрастанию)
        students.sort((s1, s2) -> {
            try {
                int age1 = Integer.parseInt(s1.getAge());
                int age2 = Integer.parseInt(s2.getAge());
                return Integer.compare(age1, age2); // Сравниваем возраста
            } catch (NumberFormatException e) {
                return 0; // В случае ошибки игнорируем студентов с некорректным возрастом
            }
        });

        // Заполняем данные для каждого графика
        for (Student student : students) {
            if (!student.getAge().isEmpty() && !student.getAge().equals("пользователь не найден")) {
                String age = student.getAge();
                exercisesDataset.addValue(student.getExerciseScore(), "Упражнения", age);
                homeworkDataset.addValue(student.getHomeworkScore(), "Домашние задания", age);
                quizDataset.addValue(student.getQuizScore(), "Викторины", age);
                totalScoreDataset.addValue(student.getTotalScore(), "Общий балл", age);
            }
        }

        // Создаем графики для каждого набора данных
        JFreeChart exercisesChart = ChartFactory.createLineChart(
                "Зависимость баллов по упражнениям от возраста студентов",
                "Возраст", "Баллы", exercisesDataset);

        JFreeChart homeworkChart = ChartFactory.createLineChart(
                "Зависимость баллов по домашним заданиям от возраста студентов",
                "Возраст", "Баллы", homeworkDataset);

        JFreeChart quizChart = ChartFactory.createLineChart(
                "Зависимость баллов по викторинам от возраста студентов",
                "Возраст", "Баллы", quizDataset);

        JFreeChart totalScoreChart = ChartFactory.createLineChart(
                "Зависимость общего балла от возраста студентов",
                "Возраст", "Баллы", totalScoreDataset);

        // Создаем основное окно
        JFrame mainFrame = new JFrame("Графики по возрасту студентов");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        // Создаем панель вкладок
        JTabbedPane tabbedPane = new JTabbedPane();

        // Добавляем графики в вкладки
        tabbedPane.addTab("Упражнения", new ChartPanel(exercisesChart));
        tabbedPane.addTab("Домашние задания", new ChartPanel(homeworkChart));
        tabbedPane.addTab("Викторины", new ChartPanel(quizChart));
        tabbedPane.addTab("Общий балл", new ChartPanel(totalScoreChart));

        // Добавляем панель с вкладками на основное окно
        mainFrame.add(tabbedPane, BorderLayout.CENTER);

        // Отображаем окно
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
