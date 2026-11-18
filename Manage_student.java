
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Manage_student extends JFrame {

    DefaultListModel<Student> studentModel = new DefaultListModel<>();
    JList<Student> studentList = new JList<>(studentModel);

    JTextField idField = new JTextField(20);
    JTextField nameField = new JTextField(20);

    JComboBox<String> semesterBox;
    JComboBox<String> departmentBox;

    public Manage_student() {

        setTitle("Manage Department Students");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Main heading
        JLabel heading = new JLabel(
            "Manage Students in Department",
            JLabel.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        add(heading, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Semester selection
        JLabel semesterLabel = new JLabel("Select Semester:");

        String semesters[] = {
            "1st Semester",
            "2nd Semester",
            "3rd Semester",
            "4th Semester",
            "5th Semester",
            "6th Semester",
            "7th Semester",
            "8th Semester"
        };

        semesterBox = new JComboBox<>(semesters);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(semesterLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(semesterBox, gbc);

        // Student list heading
        JLabel listLabel = new JLabel("Enrolled Students:");
        listLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(listLabel, gbc);

        // Student list
        studentList.setVisibleRowCount(6);
        JScrollPane scrollPane = new JScrollPane(studentList);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.ipady = 80;
        mainPanel.add(scrollPane, gbc);

        gbc.ipady = 0;

        // Student details heading
        JLabel detailsLabel = new JLabel("Student Details:");
        detailsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(detailsLabel, gbc);

        // Student ID
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(idField, gbc);

        // Student Name
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Student Name:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        // Department dropdown
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(new JLabel("Department:"), gbc);

        String departments[] = {
            "Graduated",
            "Computer Science",
            "Physics",
            "Mathematics",
            "Chemistry",
            "Biotechnology",
            "Electronics",
            "Environmental Sciences",
            "Economics",
            "Management Sciences"
        };

        departmentBox = new JComboBox<>(departments);

        gbc.gridx = 1;
        mainPanel.add(departmentBox, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");
        JButton removeButton = new JButton("Remove Student");
        JButton clearButton = new JButton("Clear Fields");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Sample student records
        studentModel.addElement(
            new Student("1234567890", "Ali Ahmed", "Computer Science")
        );

        studentModel.addElement(
            new Student("1234567891", "Usman Khan", "Physics")
        );

        studentModel.addElement(
            new Student("1234567892", "Ahmad Raza", "Mathematics")
        );

        // Display selected student's details
        studentList.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                Student selected = studentList.getSelectedValue();

                if (selected != null) {
                    idField.setText(selected.id);
                    nameField.setText(selected.name);
                    departmentBox.setSelectedItem(selected.department);
                }
            }
        });

        // Add student
        addButton.addActionListener(e -> {

            if (!validateFields()) {
                return;
            }

            if (findStudent(idField.getText().trim()) != -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Student ID already exists!"
                );

                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to add this student?",
                "Confirm Action",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                studentModel.addElement(
                    new Student(
                        idField.getText().trim(),
                        nameField.getText().trim(),
                        departmentBox.getSelectedItem().toString()
                    )
                );

                JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!"
                );

                clearFields();
            }
        });

        // Update student
        updateButton.addActionListener(e -> {

            int index = studentList.getSelectedIndex();

            if (index == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please select a student first."
                );

                return;
            }

            if (!validateFields()) {
                return;
            }

            String newId = idField.getText().trim();

            int duplicateIndex = findStudent(newId);

            if (duplicateIndex != -1 && duplicateIndex != index) {

                JOptionPane.showMessageDialog(
                    this,
                    "Student ID already exists!"
                );

                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to update this student's record?",
                "Confirm Update",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                Student selected = studentModel.getElementAt(index);

                selected.id = newId;
                selected.name = nameField.getText().trim();
                selected.department =
                    departmentBox.getSelectedItem().toString();

                studentList.repaint();

                JOptionPane.showMessageDialog(
                    this,
                    "Student record updated successfully!"
                );

                clearFields();
            }
        });

        // Remove student
        removeButton.addActionListener(e -> {

            int index = studentList.getSelectedIndex();

            if (index == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please select a student first."
                );

                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to remove this student?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                studentModel.remove(index);

                JOptionPane.showMessageDialog(
                    this,
                    "Student removed successfully!"
                );

                clearFields();
            }
        });

        // Clear fields
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    // Validate student information
    private boolean validateFields() {

        // Check empty fields
        if (idField.getText().trim().isEmpty()
                || nameField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Please fill all student details."
            );

            return false;
        }

        // Validate Student ID: Exactly 10 digits
        String studentId = idField.getText().trim();

        if (!studentId.matches("[0-9]{10}")) {

            JOptionPane.showMessageDialog(
                this,
                "Incorrect Student ID! Please enter exactly 10 digits.",
                "Invalid Student ID",
                JOptionPane.ERROR_MESSAGE
            );

            idField.requestFocus();
            return false;
        }

        // Validate Student Name: Only letters and spaces
        String studentName = nameField.getText().trim();

        if (!studentName.matches("[a-zA-Z ]+")) {

            JOptionPane.showMessageDialog(
                this,
                "Incorrect Student Name! Only letters and spaces are allowed.",
                "Invalid Student Name",
                JOptionPane.ERROR_MESSAGE
            );

            nameField.requestFocus();
            return false;
        }

        // Validate Department
        if (departmentBox.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a department."
            );

            return false;
        }

        return true;
    }

    // Find student using ID
    private int findStudent(String id) {

        for (int i = 0; i < studentModel.size(); i++) {

            if (studentModel.get(i).id.equalsIgnoreCase(id)) {
                return i;
            }
        }

        return -1;
    }

    // Clear input fields
    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        departmentBox.setSelectedIndex(0);

        studentList.clearSelection();
    }

    // Student class
    static class Student {

        String id;
        String name;
        String department;

        Student(String id, String name, String department) {

            this.id = id;
            this.name = name;
            this.department = department;
        }

        @Override
        public String toString() {

            return id + "  |  " + name + "  |  " + department;
        }
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Manage_student());
    }
}
