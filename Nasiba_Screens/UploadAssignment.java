import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UploadAssignment extends JFrame {

    File selectedFile = null;

    JLabel fileLabel;
    JLabel statusLabel;

    JTextArea commentArea;
    JTextArea instructionArea;

    LocalDateTime deadline =
            LocalDateTime.of(2026, 11, 30, 23, 59);

    String submittedFile = "";

    public UploadAssignment() {

        setTitle("Upload Assignment");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Main Heading
        JLabel heading = new JLabel("Upload Assignment", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        add(heading, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Course Selection
        JLabel courseLabel = new JLabel("Select Course:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(courseLabel, gbc);

        String[] courses = {
                "Object Oriented Programming",
                "Database Systems",
                "Artificial Intelligence",
                "Operating Systems"
        };

        JComboBox<String> courseBox = new JComboBox<>(courses);

        gbc.gridx = 1;
        mainPanel.add(courseBox, gbc);

        // Assignment Selection
        JLabel assignmentLabel = new JLabel("Select Assignment:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(assignmentLabel, gbc);

        String[] assignments = {
                "Assignment 1",
                "Assignment 2",
                "Assignment 3"
        };

        JComboBox<String> assignmentBox =
                new JComboBox<>(assignments);

        gbc.gridx = 1;
        mainPanel.add(assignmentBox, gbc);

        // Assignment Instructions
        JLabel instructionLabel =
                new JLabel("Assignment Instructions:");

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(instructionLabel, gbc);

        instructionArea = new JTextArea(3, 25);
        instructionArea.setText(
                "Complete the given assignment and upload your file."
        );

        instructionArea.setEditable(false);
        instructionArea.setLineWrap(true);
        instructionArea.setWrapStyleWord(true);

        JScrollPane instructionScroll =
                new JScrollPane(instructionArea);

        gbc.gridx = 1;
        mainPanel.add(instructionScroll, gbc);

        // Deadline
        JLabel deadlineLabel = new JLabel("Deadline:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(deadlineLabel, gbc);

        JLabel deadlineValue =
                new JLabel("30 November 2026, 11:59 PM");

        gbc.gridx = 1;
        mainPanel.add(deadlineValue, gbc);

        // Allowed File Types
        JLabel fileTypeLabel =
                new JLabel("Allowed File Types:");

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(fileTypeLabel, gbc);

        JLabel fileTypeValue =
                new JLabel("PDF, DOC, DOCX, ZIP (Maximum 10 MB)");

        gbc.gridx = 1;
        mainPanel.add(fileTypeValue, gbc);

        // Upload File
        JLabel uploadLabel = new JLabel("Upload Assignment:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(uploadLabel, gbc);

        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton uploadButton = new JButton("Choose File");

        fileLabel = new JLabel("No file selected");

        filePanel.add(uploadButton);
        filePanel.add(fileLabel);

        gbc.gridx = 1;
        mainPanel.add(filePanel, gbc);

        // Optional Comment
        JLabel commentLabel =
                new JLabel("Comment for Teacher:");

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(commentLabel, gbc);

        commentArea = new JTextArea(3, 25);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);

        JScrollPane commentScroll =
                new JScrollPane(commentArea);

        gbc.gridx = 1;
        mainPanel.add(commentScroll, gbc);

        // Submission Status
        JLabel statusHeading =
                new JLabel("Submission Status:");

        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(statusHeading, gbc);

        statusLabel = new JLabel("Not Submitted");

        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridx = 1;
        mainPanel.add(statusLabel, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JButton replaceButton = new JButton("Replace File");

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(replaceButton);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;

        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // UPLOAD FILE
        uploadButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();

                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {

                    File file = fileChooser.getSelectedFile();

                    String name = file.getName().toLowerCase();

                    if (!(name.endsWith(".pdf")
                            || name.endsWith(".doc")
                            || name.endsWith(".docx")
                            || name.endsWith(".zip"))) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Invalid file type. Select PDF, DOC, DOCX or ZIP."
                        );

                        return;
                    }

                    if (file.length() > 10 * 1024 * 1024) {

                        JOptionPane.showMessageDialog(
                                null,
                                "File size must not exceed 10 MB."
                        );

                        return;
                    }

                    selectedFile = file;

                    fileLabel.setText(file.getName());

                    JOptionPane.showMessageDialog(
                            null,
                            "File selected successfully."
                    );
                }
            }
        });

        // SUBMIT ASSIGNMENT
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedFile == null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please select an assignment file first."
                    );

                    return;
                }

                if (LocalDateTime.now().isAfter(deadline)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Deadline has passed. Submission is not allowed."
                    );

                    return;
                }

                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to submit this assignment?",
                        "Confirm Submission",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {

                    saveAssignment();

                }
            }
        });

        // CANCEL SUBMISSION
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedFile = null;

                fileLabel.setText("No file selected");

                commentArea.setText("");

                JOptionPane.showMessageDialog(
                        null,
                        "Submission cancelled. No new file was saved."
                );
            }
        });

        // REPLACE FILE
        replaceButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (submittedFile.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "No submitted assignment is available to replace."
                    );

                    return;
                }

                if (LocalDateTime.now().isAfter(deadline)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Deadline has passed. File cannot be replaced."
                    );

                    return;
                }

                if (selectedFile == null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please choose a new file first."
                    );

                    return;
                }

                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to replace the previous assignment?",
                        "Replace Assignment",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {

                    saveAssignment();

                }
            }
        });

        setVisible(true);
    }

    // SAVE ASSIGNMENT
    private void saveAssignment() {

        try {

            File folder = new File("SubmittedAssignments");

            if (!folder.exists()) {
                folder.mkdir();
            }

            String destination =
                    "SubmittedAssignments/" + selectedFile.getName();

            Files.copy(
                    selectedFile.toPath(),
                    new File(destination).toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            submittedFile = destination;

            // Submission Date and Time
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "dd-MM-yyyy HH:mm:ss"
                    );

            String dateTime = now.format(formatter);

            // Update Status
            statusLabel.setText("Submitted - " + dateTime);

            // Confirmation Message
            JOptionPane.showMessageDialog(
                    null,
                    "Assignment Submitted Successfully!\n"
                    + "Status: Submitted\n"
                    + "Date and Time: " + dateTime
            );

            selectedFile = null;

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Upload failed. Nothing was saved.\nPlease try again."
            );
        }
    }

    public static void main(String[] args) {

        new UploadAssignment();
    }
}