package visualisation;

import db.DBRepository;
import db.models.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;


public class MainWindow {

    public static void main(String[] args) {
        // Подключаемся к базе данных
        DBRepository.connect();

        try {
            // Получаем студентов из базы данных
            List<Student> studentsFromDB = DBRepository.getStudents();

            // Фильтруем студентов, у которых указан возраст
            List<Student> studentsWithAge = studentsFromDB.stream()
                    .filter(student -> !student.getAge().isEmpty()
                            && !student.getAge().equals("дата рождения не указана")
                            && !student.getAge().equals("пользователь не найден или не зарегистрирован в вк"))
                    .collect(Collectors.toList());

            // Проверяем, сколько студентов с указанным возрастом
            System.out.println("Количество студентов с указанным возрастом: " + studentsWithAge.size());

            // Создаем главное окно
            JFrame mainFrame = new JFrame("Главное окно");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(400, 200);
            mainFrame.setLayout(new BorderLayout());

            // Добавляем заголовок
            JLabel titleLabel = new JLabel("Выберите тип графика для отображения", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            mainFrame.add(titleLabel, BorderLayout.NORTH);

            // Создаем панель для кнопок
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            // Создаем кнопки
            JButton pieChartButton = new JButton("Круговая диаграмма");
            JButton lineChartButton = new JButton("Линейный график");

            // Добавляем действия для кнопок
            pieChartButton.addActionListener(e -> {
                // Открываем круговую диаграмму
                PieChartDrawer.drawPieChart(studentsFromDB);
            });

            lineChartButton.addActionListener(e -> {
                // Открываем 4 графика
                LineChartDrawer.drawLineCharts(studentsWithAge);
            });

            // Добавляем кнопки на панель
            buttonPanel.add(pieChartButton);
            buttonPanel.add(lineChartButton);

            // Добавляем панель с кнопками в главное окно
            mainFrame.add(buttonPanel, BorderLayout.CENTER);

            // Устанавливаем окно по центру экрана
            mainFrame.setLocationRelativeTo(null);

            // Делаем окно видимым
            mainFrame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Произошла ошибка: " + e.getMessage());
        } finally {
            // Закрываем соединение с базой данных
            DBRepository.disconnect();
        }
    }
}
