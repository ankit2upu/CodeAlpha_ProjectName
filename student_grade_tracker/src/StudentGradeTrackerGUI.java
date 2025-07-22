import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame {
    private JTextField nameField;
    private JTextField[] markFields;
    private DefaultTableModel tableModel;
    private ArrayList<Student> students;

    public StudentGradeTrackerGUI() {
        students = new ArrayList<>();

        setTitle("STUDENT GRADE TRACKER");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel (Form)
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        nameField = new JTextField(15);
        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(nameField);

        markFields = new JTextField[3]; // You can change the number of subjects
        for (int i = 0; i < markFields.length; i++) {
            markFields[i] = new JTextField();
            inputPanel.add(new JLabel("Marks for Subject " + (i + 1) + ":"));
            inputPanel.add(markFields[i]);
        }

        JButton addButton = new JButton("Add Student");
        inputPanel.add(addButton);

        // Table for Results
        String[] columns = {"Name", "Average", "Highest", "Lowest"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        // Add listeners
        addButton.addActionListener(e -> addStudent());

        add(inputPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        int[] marks = new int[markFields.length];

        try {
            for (int i = 0; i < markFields.length; i++) {
                marks[i] = Integer.parseInt(markFields[i].getText().trim());
            }

            Student student = new Student(name, marks);
            students.add(student);

            tableModel.addRow(new Object[]{
                    student.getName(),
                    String.format("%.2f", student.getAverage()),
                    student.getHighest(),
                    student.getLowest()
            });

            // Clear fields
            nameField.setText("");
            for (JTextField field : markFields) {
                field.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid marks (numbers only).");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGradeTrackerGUI().setVisible(true);
        });
    }
}
