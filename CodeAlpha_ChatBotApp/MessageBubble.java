import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessageBubble extends JPanel {

    private float alpha = 0f;

    public MessageBubble(String message, boolean isUser) {
        setLayout(new FlowLayout(isUser ? FlowLayout.RIGHT : FlowLayout.LEFT));
        setOpaque(false);

        JTextArea text = new JTextArea(message);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setFocusable(false);
        text.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        text.setForeground(ThemeManager.textColor());
        text.setOpaque(false);
        text.setBorder(new EmptyBorder(8, 12, 8, 12));
        text.setColumns(22);

        JLabel time = new JLabel(
                LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));
        time.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        time.setForeground(ThemeManager.subTextColor());

        RoundedPanel bubble = new RoundedPanel(16, isUser);
        bubble.setLayout(new BorderLayout());
        bubble.add(text, BorderLayout.CENTER);
        bubble.add(time, BorderLayout.SOUTH);

        add(bubble);
        fadeIn();
    }

    private void fadeIn() {
        Timer timer = new Timer(30, e -> {
            alpha += 0.1f;
            if (alpha >= 1f) {
                alpha = 1f;
                ((Timer) e.getSource()).stop();
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha));
        super.paintComponent(g2);
    }
}
