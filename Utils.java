import javax.swing.*;
import java.awt.*;

public class Utils {
    public static ImageIcon scaleImage(String relPath, double desiredWidth, double imgNativeWidth, double imgNativeHeight) {
        return new ImageIcon(new ImageIcon(relPath).getImage().getScaledInstance((int) desiredWidth, (int) (desiredWidth * (imgNativeHeight / imgNativeWidth)), Image.SCALE_SMOOTH));
    }
}
