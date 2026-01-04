import javax.swing.*;
import java.awt.*;

public class ChatUI extends JFrame {

    private JPanel chatPanel;
    private JScrollPane scrollPane;
    private JTextField input;
    private JLabel typingLabel;
    private JButton themeButton;
    private ChatBot bot;

    public ChatUI() {
        bot = new ChatBot();

        setTitle("Java Swing Chat");
        setSize(420, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(ThemeManager.chatBackground());

        // Load chat history
for (String line : ChatHistory.load()) {
    boolean isUser = line.startsWith("You:");
    chatPanel.add(
        new MessageBubble(
            line.replaceFirst(".*?: ", ""),
            isUser
        )
    );
    chatPanel.add(Box.createVerticalStrut(8));
}

        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setBorder(null);

        typingLabel = new JLabel(" ");
        typingLabel.setForeground(ThemeManager.subTextColor());

        input = new JTextField();
        input.setBackground(ThemeManager.inputBackground());
        input.setForeground(ThemeManager.textColor());

        JButton send = new JButton("Send");

        themeButton = new JButton("Light Mode");
        themeButton.addActionListener(e -> toggleTheme());

        JPanel top = new JPanel(new BorderLayout());
        top.add(typingLabel, BorderLayout.WEST);
        top.add(themeButton, BorderLayout.EAST);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(input, BorderLayout.CENTER);
        bottom.add(send, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        send.addActionListener(e -> sendMessage());
        input.addActionListener(e -> sendMessage());
    }



    private void toggleTheme() {
        ThemeManager.isDark = !ThemeManager.isDark;
        themeButton.setText(ThemeManager.isDark ? "Light Mode" : "Dark Mode");

        chatPanel.setBackground(ThemeManager.chatBackground());
        input.setBackground(ThemeManager.inputBackground());
        input.setForeground(ThemeManager.textColor());
        typingLabel.setForeground(ThemeManager.subTextColor());

        chatPanel.repaint();
        chatPanel.revalidate();
    }

    private void sendMessage() {
    String text = input.getText().trim();
    if (text.isEmpty()) return;

    addMessage(text, true);
    ChatHistory.save("You", text);
    input.setText("");

    typingLabel.setText("Bot is typing...");

    Timer t = new Timer(800, e -> {
        String reply = bot.getResponse(text);
        typingLabel.setText(" ");
        addMessage(reply, false);
        ChatHistory.save("Bot", reply);
    });
    t.setRepeats(false);
    t.start();
}


    private void addMessage(String msg, boolean isUser) {
        chatPanel.add(new MessageBubble(msg, isUser));
        chatPanel.add(Box.createVerticalStrut(8));
        chatPanel.revalidate();

        SwingUtilities.invokeLater(() ->
                scrollPane.getVerticalScrollBar()
                        .setValue(scrollPane.getVerticalScrollBar().getMaximum()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatUI().setVisible(true));
    }
}
