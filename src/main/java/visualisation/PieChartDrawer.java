package visualisation;

import db.models.Student;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;

public class PieChartDrawer {

    public static void drawPieChart(List<Student> students) {

        // Создаем набор данных для круговой диаграммы
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Дата указана
        long withAge = students
                .stream()
                .filter(s -> !s.getAge().isEmpty()
                        && !s.getAge().equals("дата рождения не указана")
                        && !s.getAge().equals("пользователь не найден или не зарегистрирован в вк"))
                .count();
        // Дата не указана или без года
        long withoutAge = students.stream().filter(s -> s.getAge().equals("дата рождения не указана")).count();
        // Не найден в вк
        long notFound = students.stream().filter(s -> s.getAge().equals("пользователь не найден или не зарегистрирован в вк")).count();

        // Добавляем данные в диаграмму
        dataset.setValue("Дата рождения указана", withAge);
        dataset.setValue("Дата рождения не указана или указана без года", withoutAge);
        dataset.setValue("Пользователь не найден", notFound);

        // Создаем диаграмму
        JFreeChart chart = ChartFactory.createPieChart(
                "Статистика по дате рождения студентов", // Заголовок
                dataset, // Данные
                true, // Легенда
                true, // Инфо при наведении
                false // URL
        );

        // Получаем объект PiePlot для настройки отображения
        PiePlot plot =(PiePlot) chart.getPlot();

        // Настройка отображения только процентов
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
                "{2}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));

        // Располагаем метки внутри сегментов
        plot.setSimpleLabels(true);
        plot.setLabelBackgroundPaint(null); // Убираем фон у меток
        plot.setLabelOutlinePaint(null); // Убираем границу у меток
        plot.setLabelShadowPaint(null); // Убираем тени у меток

        // Изменяем шрифт текста в легенде
        LegendTitle legend = chart.getLegend();
        legend.setItemFont(new Font("Arial", Font.BOLD, 14)); // Шрифт: Arial, полужирный, размер 16

        // Отображаем диаграмму в окне
        JFrame frame = new JFrame("Круговая диаграмма");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();

        // Устанавливаем окно по центру экрана
        frame.setLocationRelativeTo(null);

        // Отображаем окно
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
