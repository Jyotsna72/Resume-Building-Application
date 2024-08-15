package com.resumebuilder.gui;

import com.resumebuilder.model.Template;
import com.resumebuilder.util.ExportUtil;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private List<Template> templates;

    public MainFrame() {
        setTitle("Resume Builder");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        templates = new ArrayList<>();
        loadTemplates();

        initUI();
    }

    private void loadTemplates() {
        templates.add(new Template("Modern", "A modern resume template"));
        templates.add(new Template("Classic", "A classic resume template"));
        // Add more templates as needed
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Resume Builder", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        // Main Panel for form fields
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Personal Information
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel personalInfoLabel = new JLabel("Personal Information", JLabel.CENTER);
        personalInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(personalInfoLabel, gbc);

        addLabeledTextField(mainPanel, "First name:", 0, 1, gbc);
        addLabeledTextField(mainPanel, "Surname:", 1, 1, gbc);
        addLabeledTextField(mainPanel, "Address Line 1:", 0, 2, gbc);
        addLabeledTextField(mainPanel, "Address Line 2:", 1, 2, gbc);
        addLabeledTextField(mainPanel, "Post Code:", 0, 3, gbc);
        addLabeledTextField(mainPanel, "Nationality:", 1, 3, gbc);
        addLabeledTextField(mainPanel, "Date of Birth:", 0, 4, gbc);
        addLabeledTextField(mainPanel, "Contact:", 1, 4, gbc);
        addLabeledTextField(mainPanel, "Email:", 0, 5, gbc);

        // Skills
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel skillsLabel = new JLabel("Skills", JLabel.CENTER);
        skillsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(skillsLabel, gbc);

        JTextArea skillsArea = new JTextArea(10, 20);
        gbc.gridy = 1;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        JScrollPane skillsScrollPane = new JScrollPane(skillsArea);
        mainPanel.add(skillsScrollPane, gbc);
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Work Experience
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JLabel workExperienceLabel = new JLabel("Work Experience", JLabel.CENTER);
        workExperienceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(workExperienceLabel, gbc);

        addLabeledTextField(mainPanel, "Company Name:", 0, 7, gbc);
        addLabeledTextField(mainPanel, "Work Done:", 1, 7, gbc);
        addLabeledTextField(mainPanel, "Company Name:", 0, 8, gbc);
        addLabeledTextField(mainPanel, "Work Done:", 1, 8, gbc);
        addLabeledTextField(mainPanel, "Company Name:", 0, 9, gbc);
        addLabeledTextField(mainPanel, "Work Done:", 1, 9, gbc);

        // Qualifications
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        JLabel qualificationsLabel = new JLabel("Qualifications", JLabel.CENTER);
        qualificationsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(qualificationsLabel, gbc);

        addLabeledTextField(mainPanel, "College/University:", 0, 11, gbc);
        addLabeledTextField(mainPanel, "Title of Qualification A:", 1, 11, gbc);
        addLabeledTextField(mainPanel, "Title of Qualification B:", 0, 12, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save CV");
        saveButton.addActionListener(e -> saveCV(mainPanel));
        JButton generateButton = new JButton("Generate CV");
        generateButton.addActionListener(e -> generateCV(mainPanel));
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields(mainPanel));

        buttonPanel.add(saveButton);
        buttonPanel.add(generateButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addLabeledTextField(JPanel panel, String labelText, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        panel.add(label, gbc);
        gbc.gridx = x + 1;
        JTextField textField = new JTextField(20);
        panel.add(textField, gbc);
    }

    private void saveCV(JPanel customizationPanel) {
        // Implement save functionality here
        JOptionPane.showMessageDialog(this, "Save CV functionality to be implemented");
    }

    private void generateCV(JPanel customizationPanel) {
        StringBuilder resumeContent = new StringBuilder();

        Component[] components = customizationPanel.getComponents();
        for (int i = 0; i < components.length; i += 2) {
            if (components[i] instanceof JLabel && components[i + 1] instanceof JTextComponent) {
                JLabel label = (JLabel) components[i];
                JTextComponent textComponent = (JTextComponent) components[i + 1];
                resumeContent.append(label.getText()).append(" ").append(textComponent.getText()).append("\n\n");
            }
        }

        ExportUtil.exportToFile(resumeContent.toString(), "resume.txt");
        JOptionPane.showMessageDialog(this, "Resume generated successfully!");
    }

    private void clearFields(JPanel customizationPanel) {
        for (Component component : customizationPanel.getComponents()) {
            if (component instanceof JTextComponent) {
                ((JTextComponent) component).setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}







