package shooter.Menu;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

/**
 * Class Panel1_PlayerSettings creates settings for player in the menu.
 */
public class Panel1_PlayerSettings extends JPanel {

    /**
     * Creates the panel with player settings.
     */
    public Panel1_PlayerSettings() {
        setBounds(300, 50, 350, 400);
        setOpaque(false);
        setBackground(Color.white);
        setLayout(null);
        setVisible(false);

        Border outer = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        Border inner = BorderFactory.createTitledBorder("Player Settings");
        Border composed = BorderFactory.createCompoundBorder(outer, inner);
        setBorder(composed);
    }
}
