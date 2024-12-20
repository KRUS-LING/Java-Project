package visualisation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

    private boolean isHovered = false; // Флаг наведения
    private boolean isPressed = false;   // Флаг нажатия
    private final Color defaultColor;  // Исходный цвет
    private final Color hoverColor;    // Цвет при наведении
    private final Color pressedColor;    // Цвет при нажатии

    public RoundedButton(String text, Color defaultColor) {
        super(text);
        this.defaultColor = defaultColor;
        this.hoverColor = defaultColor.darker();
        this.pressedColor = defaultColor.darker().darker(); // Цвет при нажатии (ещё темнее)

        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE); // Цвет текста
        setFont(new Font("Arial", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Добавляем обработку событий для наведения, нажатия и отпускания
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Выбираем цвет фона в зависимости от состояния кнопки
        if (isPressed) {
            g2.setColor(pressedColor);  // Цвет при нажатии
        } else if (isHovered) {
            g2.setColor(hoverColor);    // Цвет при наведении
        } else {
            g2.setColor(defaultColor);  // Исходный цвет
        }



        // Рисуем закруглённый прямоугольник
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        // Рисуем текст
        super.paintComponent(g2);

        g2.dispose();
    }
}
