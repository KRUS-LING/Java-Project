package visualisation;

import db.models.Student;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LineChartDrawer {

    private static final Color buttonColor = new Color(151, 172, 209);
    private static final Color hoverColor = buttonColor.darker();
    private static final Color pressedColor = hoverColor.darker();

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
                homeworkDataset.addValue(student.getHomeworkScore(), "Практики", age);
                quizDataset.addValue(student.getQuizScore(), "Контрольные вопросы", age);
                totalScoreDataset.addValue(student.getTotalScore(), "Общий балл", age);
            }
        }

        // Создаем графики для каждого набора данных
        JFreeChart exercisesChart = ChartFactory.createLineChart(
                "Зависимость баллов по упражнениям от возраста студентов",
                "Возраст", "Баллы", exercisesDataset);

        JFreeChart homeworkChart = ChartFactory.createLineChart(
                "Зависимость баллов по практикам от возраста студентов",
                "Возраст", "Баллы", homeworkDataset);

        JFreeChart quizChart = ChartFactory.createLineChart(
                "Зависимость баллов по контрольным вопросам от возраста студентов",
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

        // Добавляем графики во вкладки
        tabbedPane.addTab("Упражнения", new ChartPanel(exercisesChart));
        tabbedPane.addTab("Практики", new ChartPanel(homeworkChart));
        tabbedPane.addTab("Контрольные вопросы", new ChartPanel(quizChart));
        tabbedPane.addTab("Общий балл", new ChartPanel(totalScoreChart));

        // Создаем панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Создаем кнопки
        JButton closeButton = new RoundedButton("Закрыть", buttonColor);

        // Устанавливаем одинаковый размер для всех кнопок
        Dimension buttonSize = new Dimension(150, 40); // Ширина = 150, Высота = 40
        closeButton.setPreferredSize(buttonSize);

        // Добавляем действия для кнопки
        closeButton.addActionListener(e -> mainFrame.dispose());

        // Добавляем кнопки на панель
        buttonPanel.add(closeButton);

        // Добавляем панель с кнопками в основное окно
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Добавляем панель с вкладками на основное окно
        mainFrame.add(tabbedPane, BorderLayout.CENTER);

        // Отображаем окно
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
