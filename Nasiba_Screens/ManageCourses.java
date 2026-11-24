import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class ManageCourses extends JFrame {

    DefaultListModel<Course> courseModel;
    JList<Course> courseList;

    JTextField codeField;
    JTextField nameField;
    JTextField creditField;
    JTextField semesterField;
    JTextField teacherField;

    int editingIndex = -1;

    HashSet<String> registeredCourses = new HashSet<>();

    // Course Class
    class Course {

        String code;
        String name;
        String credit;
        String semester;
        String teacher;

        Course(String c, String n, String cr, String s, String t) {

            code = c;
            name = n;
            credit = cr;
            semester = s;
            teacher = t;
        }

        public String toString() {

            return code + " | " + name + " | "
                    + credit + " Credits | Semester "
                    + semester + " | " + teacher;
        }
    }

    public ManageCourses() {

        setTitle("Manage Courses");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Main Heading
        JLabel heading = new JLabel(
                "Manage List of Courses Offered",
                JLabel.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 24));

        add(heading, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Course List Heading
        JLabel listLabel = new JLabel("Available Courses:");

        listLabel.setFont(new Font("Arial", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        mainPanel.add(listLabel, gbc);

        // Course List
        courseModel = new DefaultListModel<>();

        courseModel.addElement(new Course(
                "CS101",
                "Introduction to Computer Science",
                "3",
                "1",
                "Ali Khan"
        ));

        courseModel.addElement(new Course(
                "CS201",
                "Object Oriented Programming",
                "4",
                "3",
                "Sara Ahmed"
        ));

        courseModel.addElement(new Course(
                "CS301",
                "Database Systems",
                "3",
                "5",
                "Usman Tariq"
        ));

        // Students registered in CS101
        registeredCourses.add("CS101");

        courseList = new JList<>(courseModel);

        JScrollPane courseScroll =
                new JScrollPane(courseList);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 100;
        gbc.gridwidth = 2;

        mainPanel.add(courseScroll, gbc);

        gbc.ipady = 0;

        // Course Code
        JLabel codeLabel = new JLabel("Course Code:");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        mainPanel.add(codeLabel, gbc);

        codeField = new JTextField(20);

        gbc.gridx = 1;
        mainPanel.add(codeField, gbc);

        // Course Name
        JLabel nameLabel = new JLabel("Course Name:");

        gbc.gridx = 0;
        gbc.gridy = 3;

        mainPanel.add(nameLabel, gbc);

        nameField = new JTextField(20);

        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        // Credit Hours
        JLabel creditLabel = new JLabel("Credit Hours:");

        gbc.gridx = 0;
        gbc.gridy = 4;

        mainPanel.add(creditLabel, gbc);

        creditField = new JTextField(20);

        gbc.gridx = 1;
        mainPanel.add(creditField, gbc);

        // Semester
        JLabel semesterLabel = new JLabel("Semester:");

        gbc.gridx = 0;
        gbc.gridy = 5;

        mainPanel.add(semesterLabel, gbc);

        semesterField = new JTextField(20);

        gbc.gridx = 1;
        mainPanel.add(semesterField, gbc);

        // Teacher
        JLabel teacherLabel = new JLabel("Teacher:");

        gbc.gridx = 0;
        gbc.gridy = 6;

        mainPanel.add(teacherLabel, gbc);

        teacherField = new JTextField(20);

        gbc.gridx = 1;
        mainPanel.add(teacherField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Course");
        JButton editButton = new JButton("Edit Course");
        JButton deleteButton = new JButton("Delete Course");
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;

        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // ADD COURSE
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                editingIndex = -1;

                clearFields();

                JOptionPane.showMessageDialog(
                        null,
                        "Enter the new course details."
                );
            }
        });

        // EDIT COURSE
        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int index = courseList.getSelectedIndex();

                if (index == -1) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Select a course first."
                    );

                    return;
                }

                Course course = courseModel.getElementAt(index);

                editingIndex = index;

                codeField.setText(course.code);
                nameField.setText(course.name);
                creditField.setText(course.credit);
                semesterField.setText(course.semester);
                teacherField.setText(course.teacher);

            }
        });

        // SAVE COURSE
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!validateFields())
                    return;

                // Check Duplicate Course Code
                for (int i = 0; i < courseModel.size(); i++) {

                    if (i != editingIndex
                            && courseModel.getElementAt(i).code
                            .equalsIgnoreCase(codeField.getText().trim())) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Course Code already exists."
                        );

                        return;
                    }
                }

                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to save this course?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {

                    Course course = new Course(
                            codeField.getText().trim(),
                            nameField.getText().trim(),
                            creditField.getText().trim(),
                            semesterField.getText().trim(),
                            teacherField.getText().trim()
                    );

                    if (editingIndex == -1) {

                        courseModel.addElement(course);

                        JOptionPane.showMessageDialog(
                                null,
                                "Course added successfully."
                        );

                    } else {

                        courseModel.set(editingIndex, course);

                        JOptionPane.showMessageDialog(
                                null,
                                "Course updated successfully."
                        );
                    }

                    clearFields();

                    editingIndex = -1;
                }
            }
        });

        // DELETE COURSE
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int index = courseList.getSelectedIndex();

                if (index == -1) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Select a course first."
                    );

                    return;
                }

                Course course = courseModel.getElementAt(index);

                // Check Student Registration
                if (registeredCourses.contains(course.code)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "This course cannot be deleted.\n"
                            + "Students are already registered."
                    );

                    return;
                }

                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this course?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {

                    courseModel.remove(index);

                    JOptionPane.showMessageDialog(
                            null,
                            "Course deleted successfully."
                    );

                    clearFields();
                }
            }
        });

        // CANCEL OPERATION
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                clearFields();

                editingIndex = -1;

                courseList.clearSelection();

                JOptionPane.showMessageDialog(
                        null,
                        "Operation cancelled. No changes saved."
                );
            }
        });

        setVisible(true);
    }

    // VALIDATION CHECKS
    private boolean validateFields() {

        if (codeField.getText().trim().isEmpty()
                || nameField.getText().trim().isEmpty()
                || creditField.getText().trim().isEmpty()
                || semesterField.getText().trim().isEmpty()
                || teacherField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "All fields are required."
            );

            return false;
        }

        try {

            int credit = Integer.parseInt(
                    creditField.getText().trim()
            );

            int semester = Integer.parseInt(
                    semesterField.getText().trim()
            );

            if (credit < 1 || credit > 6) {

                JOptionPane.showMessageDialog(
                        null,
                        "Credit hours must be between 1 and 6."
                );

                return false;
            }

            if (semester < 1 || semester > 8) {

                JOptionPane.showMessageDialog(
                        null,
                        "Semester must be between 1 and 8."
                );

                return false;
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Credit hours and semester must be numbers."
            );

            return false;
        }

        return true;
    }

    // CLEAR FIELDS
    private void clearFields() {

        codeField.setText("");
        nameField.setText("");
        creditField.setText("");
        semesterField.setText("");
        teacherField.setText("");
    }

    public static void main(String[] args) {

        new ManageCourses();
    }
}