
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Umar extends JFrame {

    public Umar() {

       setTitle("Teacher Course Material");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Main heading
        JLabel heading = new JLabel("Uploading Material", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        add(heading, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
       /// gbc.anchor = GridBagConstraints.CENTER;///////////
       /// gbc.weightx = 0.5;///////

        //Font labelFont = new Font("Arial", Font.BOLD, 15);

        // Course selection
        JLabel courseLabel = new JLabel("Select Course:");
       // courseLabel.setFont(labelFont);
        courseLabel.setHorizontalAlignment(JLabel.CENTER);

        String[] courses = {
            "Select Course",
            "Computer Science",
            "Software Engineering",
            "Database Systems"
        };

        JComboBox<String> courseCombo = new JComboBox<>(courses);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(courseLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(courseCombo, gbc);

        // Assignment selection
        JLabel assignmentLabel =
            new JLabel("Select Assignment:");

        //assignmentLabel.setFont(labelFont);
        assignmentLabel.setHorizontalAlignment(JLabel.CENTER);

        String[] assignments = {
            "Select Assignment",
            "Assignment 1",
            "Assignment 2",
            "Assignment 3"
        };

        JComboBox<String> assignmentCombo =
            new JComboBox<>(assignments);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(assignmentLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(assignmentCombo, gbc);

        // Student name
        JLabel studentLabel = new JLabel("Student Name:");
        // studentLabel.setFont(labelFont);
        studentLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextField studentField = new JTextField("Ali Ahmed", 20);
        studentField.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(studentLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(studentField, gbc);

        // Assignment submission
        JLabel submissionLabel =
            new JLabel("Assignment Submission:");

       // submissionLabel.setFont(labelFont);
        submissionLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextArea submissionArea = new JTextArea(
            "Student assignment submission will appear here."
        );

        submissionArea.setLineWrap(true);
        submissionArea.setWrapStyleWord(true);
        submissionArea.setEditable(false);

        JScrollPane submissionScroll =
            new JScrollPane(submissionArea);

        submissionScroll.setPreferredSize(
            new Dimension(400, 90)
        );

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(submissionLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(submissionScroll, gbc);

        // Teacher comments
        JLabel feedbackLabel =
            new JLabel("Teacher Comments:");

        //feedbackLabel.setFont(labelFont);
        feedbackLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextArea feedbackArea = new JTextArea(5, 30);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);

        JScrollPane feedbackScroll =
            new JScrollPane(feedbackArea);

        feedbackScroll.setPreferredSize(
            new Dimension(400, 100)
        );

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(feedbackLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(feedbackScroll, gbc);

        // Grade / Points
        JLabel gradeLabel =
            new JLabel("Assign Grade / Points:");

      //  gradeLabel.setFont(labelFont);
        gradeLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextField gradeField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(gradeLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(gradeField, gbc);

        // Submit button
        JPanel buttonPanel =
            new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton submitButton =
            new JButton("Submit Feedback");

        buttonPanel.add(submitButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        // Input validation
        submitButton.addActionListener(e -> {

            // Course selection
            if (courseCombo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please select a course.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );

                courseCombo.requestFocus();
                return;
            }

            // Assignment selection
            if (assignmentCombo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please select an assignment.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );

                assignmentCombo.requestFocus();
                return;
            }

            // Teacher comments
            String comments = feedbackArea.getText().trim();

            if (comments.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Teacher comments cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );

                feedbackArea.requestFocus();
                return;
            }

            // Grade validation
            String gradeInput = gradeField.getText().trim();

            if (gradeInput.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter the grade.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );

                gradeField.requestFocus();
                return;
            }

            double grade;

            try {
                grade = Double.parseDouble(gradeInput);
            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Grade must be a valid number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );

                gradeField.requestFocus();
                return;
            }

            // Grade range test
            if (!Double.isFinite(grade) ||
                grade < 0 || grade > 100) {

                JOptionPane.showMessageDialog(
                    this,
                    "Grade must be between 0 and 100.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );

                gradeField.requestFocus();
                return;
            }

            // Successful submission
            JOptionPane.showMessageDialog(
                this,
                "Feedback submitted successfully!\n\n" +
                "Course: " + courseCombo.getSelectedItem() +
                "\nAssignment: " + assignmentCombo.getSelectedItem() +
                "\nStudent: " + studentField.getText() +
                "\nGrade: " + grade +
                "\nComments: " + comments,
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Umar();
    }
}
