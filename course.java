
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class course extends JFrame {
    public course() {
        setTitle("Teacher Course Material");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Main heading
        JLabel heading = new JLabel("Uploading Material", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        add(heading, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(); //this is the main panel to hold all the components
        mainPanel.setLayout(new GridBagLayout()); //making main panel as grid(rows and column)

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); //inset gives spacing to elements
        gbc.fill = GridBagConstraints.HORIZONTAL; //this is to specify that element will get horizental direction

        JLabel courseLabel = new JLabel("Select Course:");

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(courseLabel, gbc);

        // List of courses
        String courses[] = {
            "Artificial Intelligence",
            "Database Systems",
            "Web Application Development",
            "Analysis and Design of Software Systems"
        };

        JComboBox<String> courseBox = new JComboBox<>(courses);

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(courseBox, gbc);

        JLabel categoryLabel = new JLabel("Material Category:");

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(categoryLabel, gbc);

        // List of material categories
        String categories[] = {
            "Lectures",
            "Assignments",
            "Reference Material"
        };

        JComboBox<String> categoryBox = new JComboBox<>(categories);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(categoryBox, gbc);

        JLabel uploadedLabel = new JLabel("Previously Uploaded Material:");
        uploadedLabel.setFont(new Font("Arial", Font.BOLD, 15));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(uploadedLabel, gbc);

        // List of previously uploaded files
        String uploadedFiles[] = {
            "Lecture_01.pdf",
            "Lecture_02.pdf",
            "Assignment_01.pdf",
            
        };

        JList<String> materialList = new JList<>(uploadedFiles);
        materialList.setVisibleRowCount(4);

        // Adds scrollbars to the material list
        JScrollPane materialScroll = new JScrollPane(materialList);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.ipady = 60;
        mainPanel.add(materialScroll, gbc);

        gbc.ipady = 0;

        // Upload button
        JButton uploadButton = new JButton("Upload Material");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(uploadButton, gbc);

        // Selected files preview label
        JLabel previewLabel = new JLabel("Selected Files Preview:");
        previewLabel.setFont(new Font("Arial", Font.BOLD, 15));

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(previewLabel, gbc);

        JTextArea previewArea = new JTextArea(5, 30);
        previewArea.setEditable(false);
        previewArea.setText(
            "No files selected.\n\n" +
            "Selected files will appear here for preview."
        );

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(previewArea, gbc);

        // Panel to hold Confirm and Cancel buttons
        JPanel buttonPanel = new JPanel();

        JButton confirmButton = new JButton("Confirm Upload");
        confirmButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        if (materialList.getSelectedValue() == null){
        JOptionPane.showMessageDialog(
        null,
        "Please select the file first",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
    );}
 }
});
        JButton cancelButton = new JButton("Cancel");
cancelButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

        int index = materialList.getSelectedIndex();

        if (index != -1) {
            materialList.remove(index);
        }
        else {
            JOptionPane.showMessageDialog(
                null,
                "Please select a file first"
            );
        }
    }
});
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(buttonPanel, gbc);

        // Adds the main panel to the center of the window
        add(mainPanel, BorderLayout.CENTER);

        // Displays the window
        setVisible(true);
    }

    // Main method
    public static void main(String[] args) {
        new course();
    }
}
