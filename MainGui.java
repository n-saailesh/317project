import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;      // ← add this
import java.util.*;         // keeps ArrayList, Map, HashMap, etc.


/**
 * A simple Swing GUI for the grade processing application.
 * Allows users to select input files and output file location,
 * then runs the existing parsing and grading logic.
 */
public class MainGui extends JFrame implements ActionListener {
    private JTextField nameFileField;
    private JTextField courseFileField;
    private JTextField outputFileField;
    private JButton browseNameBtn;
    private JButton browseCourseBtn;
    private JButton browseOutputBtn;
    private JButton processBtn;

    public MainGui() {
        super("Grade Processor GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel for file selection
        JPanel filePanel = new JPanel();
        filePanel.setLayout(new GridLayout(3, 1, 5, 5));

        // Name file chooser row
        JPanel row1 = new JPanel(new BorderLayout(5, 5));
        row1.add(new JLabel("Name File:"), BorderLayout.WEST);
        nameFileField = new JTextField();
        row1.add(nameFileField, BorderLayout.CENTER);
        browseNameBtn = new JButton("Browse...");
        browseNameBtn.addActionListener(this);
        row1.add(browseNameBtn, BorderLayout.EAST);
        filePanel.add(row1);

        // Course file chooser row
        JPanel row2 = new JPanel(new BorderLayout(5, 5));
        row2.add(new JLabel("Course File:"), BorderLayout.WEST);
        courseFileField = new JTextField();
        row2.add(courseFileField, BorderLayout.CENTER);
        browseCourseBtn = new JButton("Browse...");
        browseCourseBtn.addActionListener(this);
        row2.add(browseCourseBtn, BorderLayout.EAST);
        filePanel.add(row2);

        // Output file chooser row
        JPanel row3 = new JPanel(new BorderLayout(5, 5));
        row3.add(new JLabel("Output File:"), BorderLayout.WEST);
        outputFileField = new JTextField();
        row3.add(outputFileField, BorderLayout.CENTER);
        browseOutputBtn = new JButton("Browse...");
        browseOutputBtn.addActionListener(this);
        row3.add(browseOutputBtn, BorderLayout.EAST);
        filePanel.add(row3);

        add(filePanel, BorderLayout.CENTER);

        // Process button
        processBtn = new JButton("Process Grades");
        processBtn.addActionListener(this);
        JPanel bottom = new JPanel();
        bottom.add(processBtn);
        add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == browseNameBtn) {
            chooseFile(nameFileField, "Select Name File");
        } else if (src == browseCourseBtn) {
            chooseFile(courseFileField, "Select Course File");
        } else if (src == browseOutputBtn) {
            chooseSaveFile(outputFileField, "Choose Output File");
        } else if (src == processBtn) {
            processGrades();
        }
    }

    private void chooseFile(JTextField target, String title) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(title);
        int rc = chooser.showOpenDialog(this);
        if (rc == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            target.setText(f.getAbsolutePath());
        }
    }

    private void chooseSaveFile(JTextField target, String title) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(title);
        int rc = chooser.showSaveDialog(this);
        if (rc == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            target.setText(f.getAbsolutePath());
        }
    }

    private void processGrades() {
        String nameFile = nameFileField.getText().trim();
        String courseFile = courseFileField.getText().trim();
        String outputFile = outputFileField.getText().trim();

        if (nameFile.isEmpty() || courseFile.isEmpty() || outputFile.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please select all three files before processing.",
                "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Parse inputs
            NameRecordReader np = new NameRecordReader(nameFile);
            CourseRecordReader cp = new CourseRecordReader(courseFile);
            np.parse(); cp.parse();

            // Map IDs → names
            Map<String, String> nameMap = new HashMap<>();
            for (Student s : np.getRecords()) {
                nameMap.put(s.getId(), s.getName());
            }

            // Build final records
            List<FinalRecord> finals = new ArrayList<>();
            for (CourseRecord cr : cp.getRecords()) {
                String studentName = nameMap.getOrDefault(cr.getStudentId(), "Unknown");
                double grade = GradeCalculator.calculate(
                    cr.getT1(), cr.getT2(), cr.getT3(), cr.getExam());
                finals.add(new FinalRecord(
                    cr.getStudentId(), studentName, cr.getCourseCode(), grade));
            }

            // Write output
            new OutputFileWriter().write(finals, outputFile);
            JOptionPane.showMessageDialog(this,
                "Grades processed successfully!\nOutput: " + outputFile,
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "An error occurred: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGui gui = new MainGui();
            gui.setVisible(true);
        });
    }
}
