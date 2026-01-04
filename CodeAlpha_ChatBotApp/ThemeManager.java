import java.awt.*;

public class ThemeManager {

    public static boolean isDark = true;

    // Backgrounds
    public static Color chatBackground() {
        return isDark ? new Color(30, 30, 30) : new Color(245, 245, 245);
    }

    public static Color inputBackground() {
        return isDark ? new Color(45, 45, 45) : Color.WHITE;
    }

    // Text
    public static Color textColor() {
        return isDark ? Color.WHITE : Color.BLACK;
    }

    public static Color subTextColor() {
        return isDark ? Color.LIGHT_GRAY : Color.DARK_GRAY;
    }

    // Bubbles
    public static Color userBubble() {
        return isDark ? new Color(70,130,180) : new Color(100,149,237);
    }

    public static Color botBubble() {
        return isDark ? new Color(255,140,0) : new Color(255,165,0);
    }
}
