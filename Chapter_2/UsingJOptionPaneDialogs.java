package Chapter_2;

import javax.swing.JOptionPane;

public class UsingJOptionPaneDialogs {
    public static void main(String[] args) {
        String name;
        int choice;
        do {
            name = JOptionPane.showInputDialog("What is your name?");
            choice = JOptionPane.showConfirmDialog(null, "Do you want your name to be shown?");
        } while (choice != JOptionPane.YES_OPTION);

        JOptionPane.showMessageDialog(null, "Hello, " + name + "!");
    }
}
