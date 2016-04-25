package eus.aiaraldea.lanternatryout;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class HelloWorld {

    private static Panel centerPanel;

    public static void main(String[] args) throws IOException {

        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        // Create panel to hold components
        centerPanel = new Panel();
        centerPanel.setLayoutManager(new GridLayout(2));

        centerPanel.addComponent(new Label("Forename"));
        centerPanel.addComponent(new TextBox());

        centerPanel.addComponent(new Label("Surname"));
        centerPanel.addComponent(new TextBox());
        centerPanel.addComponent(new EmptySpace(new TerminalSize(0, 0))); // Empty space underneath labels
        centerPanel.addComponent(new Button("Submit", () -> {
            // Actions go here
            Table<String> table = new Table<>("Column 1", "Column 2", "Column 3");
            table.getTableModel().addRow("1", "2", "3");
            table.getTableModel().addRow("1", "2", "3");
            table.getTableModel().addRow("1", "2", "3");
            HelloWorld.centerPanel.addComponent(table);
        }));

        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));

        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new BorderLayout());
        centerPanel.setLayoutData(BorderLayout.Location.CENTER);
        mainPanel.addComponent(centerPanel);

        Panel bottomPanel = new Panel();
        Button buttonf1 = new Button("F1 - Inicio", () -> {
            // Actions go here
        });
        Button buttonf2 = new Button("F2 - Movimientos", () -> {
            // Actions go here
        });
        Button buttonf3 = new Button("F3 - Entradas", () -> {
            // Actions go here
        });
        Button buttonf4 = new Button("F4 - Salidas", () -> {
            // Actions go here
        });
        Runnable exit = new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        Button buttonf12 = new Button("F12 - Cerrar", exit);
        bottomPanel.addComponent(buttonf1);
        bottomPanel.addComponent(buttonf2);
        bottomPanel.addComponent(buttonf3);
        bottomPanel.addComponent(buttonf4);
        bottomPanel.addComponent(buttonf12);
        bottomPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
        bottomPanel.setLayoutData(BorderLayout.Location.BOTTOM);

        mainPanel.addComponent(bottomPanel);
        window.setComponent(mainPanel);

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);
        gui.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));
        gui.addListener((TextGUI textGUI, KeyStroke keyStroke) -> {
            if (keyStroke.getKeyType() == KeyType.F1) {
                System.err.println("clicked");
            }
            if (keyStroke.getKeyType() == KeyType.F12) {
                exit.run();
            }
            return false;
        });
        gui.addWindowAndWait(window);

    }
}
