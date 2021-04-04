package fr.romdhani.scaridae.utils.window;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class WindowUtil {
    private WindowUtil() {
        throw new IllegalAccessError("Utility class");
    }

    private static Component findFirstParent(Component component, Predicate<Component> predicate) {
        Component parent = component.getParent();
        while (parent != null && !predicate.test(parent)) {
            parent = parent.getParent();
        }

        return parent;
    }

    public static Frame findFrame(Component component) {
        return (Frame) findFirstParent(component, parent -> parent instanceof Frame);
    }

    public static JDialog findJDialog(Component component) {
        return (JDialog) findFirstParent(component, parent -> parent instanceof JDialog);
    }

    public static Window findWindow(Component component) {
        return (Window) findFirstParent(component, parent -> parent instanceof Window);
    }

    /**
     * Finds best parent, used center message dialogs on top of it.
     * The active one is the best. The second best would be any JFrame. Finally, if nothing match, any window will do.
     */
    public static Window findParentWindow() {
        Function<Window, Integer> score = (Window w) -> {
            if (w.isActive())
                return 10;
            if (w instanceof JFrame)
                return 1;
            return 0;
        };

        return Arrays.stream(Window.getWindows())
                .filter(Window::isShowing)
                .max(Comparator.comparing(score))
                .orElse(null);
    }
}
