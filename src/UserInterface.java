import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

public class UserInterface implements ActionListener {
    WestminsterSkinConsultationManager skinConsultationManager = new WestminsterSkinConsultationManager();
    JFrame mainFrame;
    JButton viewDoctorsButton, addConsultationButton, viewConsultationsButton, exitButton;
    public UserInterface(){
        mainFrame = new JFrame("Westminster Skin Consultation Manager");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(1024, 720);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setResizable(false);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        mainFrame.add(panel1,BorderLayout.NORTH);
        mainFrame.add(panel2,BorderLayout.WEST);
        mainFrame.add(panel3,BorderLayout.EAST);
        mainFrame.add(panel4,BorderLayout.SOUTH);
        mainFrame.add(panel5,BorderLayout.CENTER);

        panel1.setPreferredSize(new Dimension(100,100));
        panel2.setPreferredSize(new Dimension(250,100));
        panel3.setPreferredSize(new Dimension(250,100));
        panel4.setPreferredSize(new Dimension(100,150));
        panel5.setPreferredSize(new Dimension(100,100));

        panel1.setBackground(new Color(200, 244, 244));
        panel2.setBackground(new Color(200, 244, 244));
        panel3.setBackground(new Color(200, 244, 244));
        panel4.setBackground(new Color(200, 244, 244));
        panel5.setBackground(new Color(200, 244, 244));

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,0,25));
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER,75,25));

        JLabel label1 = new JLabel("Westminster Skin Consultation Manager");
        label1.setForeground(new Color(43, 10, 110));
        label1.setFont(new Font("Comic Sans", Font.BOLD, 32));
        panel1.add(label1);

        JLabel imageLabel = new JLabel();
        ImageIcon image = new ImageIcon("medical.jpg");
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(132, 220, 219), 3));
        imageLabel.setIcon(image);
        panel5.add(imageLabel);

        viewDoctorsButton = new JButton("View Doctors");
        viewDoctorsButton.setBackground(new Color(43, 10, 110));
        viewDoctorsButton.setForeground(Color.WHITE);
        viewDoctorsButton.setFont(new Font("Comic Sans", Font.BOLD, 14));
        viewDoctorsButton.setFocusable(false);
        viewDoctorsButton.setPreferredSize(new Dimension(200, 40));
        viewDoctorsButton.addActionListener(this);
        panel4.add(viewDoctorsButton);

        addConsultationButton = new JButton("Add Consultation");
        addConsultationButton.setBackground(new Color(43, 10, 110));
        addConsultationButton.setForeground(Color.WHITE);
        addConsultationButton.setFont(new Font("Comic Sans", Font.BOLD, 14));
        addConsultationButton.setFocusable(false);
        addConsultationButton.setPreferredSize(new Dimension(200, 40));
        addConsultationButton.addActionListener(this);
        panel4.add(addConsultationButton);

        viewConsultationsButton = new JButton("View Consultations");
        viewConsultationsButton.setBackground(new Color(43, 10, 110));
        viewConsultationsButton.setForeground(Color.WHITE);
        viewConsultationsButton.setFont(new Font("Comic Sans", Font.BOLD, 14));
        viewConsultationsButton.setFocusable(false);
        viewConsultationsButton.setPreferredSize(new Dimension(200, 40));
        viewConsultationsButton.addActionListener(this);
        panel4.add(viewConsultationsButton);

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(43, 10, 110));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Comic Sans", Font.BOLD, 14));
        exitButton.setFocusable(false);
        exitButton.setPreferredSize(new Dimension(200, 40));
        exitButton.addActionListener(this);
        panel4.add(exitButton);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== viewDoctorsButton) {
            mainFrame.dispose();
            new ViewDoctorList();
        }
        else if (e.getSource()== addConsultationButton) {
            mainFrame.dispose();
            new AddConsultation();
        }
        else if (e.getSource() == viewConsultationsButton) {
            if (skinConsultationManager.getConsultationsList().isEmpty()){
                JOptionPane.showMessageDialog(mainFrame, "No Consultations To View!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                mainFrame.dispose();
                new ViewConsultationList();
            }
        }
        else if (e.getSource() == exitButton){
            if (JOptionPane.showConfirmDialog(mainFrame,"Confirm if you Want to Exit the Program?","Exit Program",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                mainFrame.dispose();

        }
    }
}

class AddConsultation implements ActionListener {
    WestminsterSkinConsultationManager skinConsultationManager = new WestminsterSkinConsultationManager();
    JFrame frame;
    JTextField uniqueIdTextField, consultationNoTextField, patientFirstNameTextField, patientSurnameTextField, patientMobileNumberTextField,
            consultationCostTextField;
    JTextArea consultationNotesTextArea;
    JButton enterUniqueIdButton, saveButton, submitButton, backButton;
    JComboBox<String> timeComboBox;
    JList<String> doctorList;
    JDatePickerImpl dobPicker;
    JDatePickerImpl datePicker;

    public AddConsultation() {

        frame = new JFrame("Westminster Skin Consultation Manager : Add Consultation");
        frame.setLayout(new BorderLayout());
        frame.setSize(1024, 720);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.add(panel5, BorderLayout.CENTER);

        panel1.setPreferredSize(new Dimension(100, 75));
        panel2.setPreferredSize(new Dimension(500, 100));
        panel3.setPreferredSize(new Dimension(500, 100));
        panel4.setPreferredSize(new Dimension(100, 125));
        panel5.setPreferredSize(new Dimension(50, 100));

        panel1.setBackground(new Color(200, 244, 244));
        panel2.setBackground(new Color(200, 244, 244));
        panel3.setBackground(new Color(200, 244, 244));
        panel4.setBackground(new Color(200, 244, 244));
        panel5.setBackground(new Color(200, 244, 244));

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        SpringLayout panel3Layout = new SpringLayout();
        panel3.setLayout(panel3Layout);
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 25));

        JLabel label = new JLabel("Add Consultation");
        label.setForeground(new Color(43, 10, 110));
        label.setFont(new Font("Comic Sans", Font.BOLD, 32));
        panel1.add(label);

        JLabel patientDetailsLabel = new JLabel("Enter Patient Details:");
        patientDetailsLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        patientDetailsLabel.setForeground(new Color(43, 10, 110));
        patientDetailsLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        patientDetailsLabel.setPreferredSize(new Dimension(250, 40));

        JLabel uniqueIdLabel = new JLabel("Unique ID:");
        uniqueIdLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        uniqueIdLabel.setForeground(new Color(40, 6, 99));
        uniqueIdLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        uniqueIdLabel.setPreferredSize(new Dimension(250, 20));

        uniqueIdTextField = new JTextField(15);
        uniqueIdTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        uniqueIdTextField.setPreferredSize(new Dimension(200, 40));

        JLabel consultationNoLabel = new JLabel("Consultation No:");
        consultationNoLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        consultationNoLabel.setForeground(new Color(40, 6, 99));
        consultationNoLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        consultationNoLabel.setPreferredSize(new Dimension(250, 20));

        consultationNoTextField = new JTextField();
        consultationNoTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        consultationNoTextField.setEditable(false);
        consultationNoTextField.setPreferredSize(new Dimension(150, 20));

        JLabel patientFirstNameLabel = new JLabel("First Name: ");
        patientFirstNameLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        patientFirstNameLabel.setForeground(new Color(40, 6, 99));
        patientFirstNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        patientFirstNameLabel.setPreferredSize(new Dimension(250, 20));

        patientFirstNameTextField = new JTextField();
        patientFirstNameTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        patientFirstNameTextField.setPreferredSize(new Dimension(150, 20));

        JLabel patientSurnameLabel = new JLabel("Surname: ");
        patientSurnameLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        patientSurnameLabel.setForeground(new Color(40, 6, 99));
        patientSurnameLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        patientSurnameLabel.setPreferredSize(new Dimension(250, 20));

        patientSurnameTextField = new JTextField();
        patientSurnameTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        patientSurnameTextField.setPreferredSize(new Dimension(150, 20));

        JLabel patientMobileNumberLabel = new JLabel("Mobile Number: ");
        patientMobileNumberLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        patientMobileNumberLabel.setForeground(new Color(40, 6, 99));
        patientMobileNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        patientMobileNumberLabel.setPreferredSize(new Dimension(250, 20));

        patientMobileNumberTextField = new JTextField();
        patientMobileNumberTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        patientMobileNumberTextField.setPreferredSize(new Dimension(150, 20));

        JLabel patientDateOfBirthLabel = new JLabel("Date Of Birth: ");
        patientDateOfBirthLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        patientDateOfBirthLabel.setForeground(new Color(40, 6, 99));
        patientDateOfBirthLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        patientDateOfBirthLabel.setPreferredSize(new Dimension(250, 20));

        UtilDateModel dobModel = new UtilDateModel();
        Properties dobProperties = new Properties();
        dobProperties.put("text.today", "Today");
        dobProperties.put("text.month", "Month");
        dobProperties.put("text.year", "Year");
        JDatePanelImpl dobPanel = new JDatePanelImpl(dobModel, dobProperties);
        dobPicker = new JDatePickerImpl(dobPanel, new DateFormatter());
        JFormattedTextField textField1 = dobPicker.getJFormattedTextField();
        textField1.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        textField1.setPreferredSize(new Dimension(150, 36));
        dobPicker.setTextEditable(true);

        JPanel topPane = new JPanel();
        topPane.setBackground(new Color(200, 244, 244));
        topPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        JPanel midPane = new JPanel();
        midPane.setBackground(new Color(200, 244, 244));
        GridLayout midLayout = new GridLayout(6, 2);
        midLayout.setVgap(20);
        midPane.setLayout(midLayout);

        topPane.add(patientDetailsLabel);
        topPane.add(uniqueIdLabel);
        topPane.add(uniqueIdTextField);

        enterUniqueIdButton = new JButton("Enter");
        enterUniqueIdButton.setBackground(new Color(43, 10, 110));
        enterUniqueIdButton.setForeground(Color.WHITE);
        enterUniqueIdButton.setPreferredSize(new Dimension(120, 30));
        enterUniqueIdButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        enterUniqueIdButton.setFocusable(false);
        enterUniqueIdButton.addActionListener(this);
        topPane.add(enterUniqueIdButton);

        midPane.add(consultationNoLabel);
        midPane.add(consultationNoTextField);
        midPane.add(patientFirstNameLabel);
        midPane.add(patientFirstNameTextField);
        midPane.add(patientSurnameLabel);
        midPane.add(patientSurnameTextField);
        midPane.add(patientMobileNumberLabel);
        midPane.add(patientMobileNumberTextField);
        midPane.add(patientDateOfBirthLabel);
        midPane.add(dobPicker);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(topPane);
        panel2.add(midPane);

        JLabel consultationDetailsLabel = new JLabel("Enter Consultation Details:");
        consultationDetailsLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        consultationDetailsLabel.setForeground(new Color(43, 10, 110));
        consultationDetailsLabel.setPreferredSize(new Dimension(350, 40));

        JLabel consultationDateLabel = new JLabel("Date:");
        consultationDateLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        consultationDateLabel.setForeground(new Color(40, 6, 99));
        consultationDateLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        consultationDateLabel.setPreferredSize(new Dimension(250, 20));

        JLabel consultationTimeLabel = new JLabel("Time:");
        consultationTimeLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        consultationTimeLabel.setForeground(new Color(40, 6, 99));
        consultationTimeLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        consultationTimeLabel.setPreferredSize(new Dimension(250, 20));

        JLabel consultationDoctorLabel = new JLabel("Doctor:");
        consultationDoctorLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        consultationDoctorLabel.setForeground(new Color(40, 6, 99));
        consultationDoctorLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        consultationDoctorLabel.setPreferredSize(new Dimension(250, 20));

        JLabel consultationCostLabel = new JLabel("Cost:");
        consultationCostLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        consultationCostLabel.setForeground(new Color(40, 6, 99));
        consultationCostLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        consultationCostLabel.setPreferredSize(new Dimension(250, 20));

        JLabel consultationNotesLabel = new JLabel("Notes:");
        consultationNotesLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        consultationNotesLabel.setForeground(new Color(40, 6, 99));
        consultationNotesLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        consultationNotesLabel.setPreferredSize(new Dimension(250, 20));

        UtilDateModel dateModel = new UtilDateModel();
        Properties dateProperties = new Properties();
        dateProperties.put("text.today", "Today");
        dateProperties.put("text.month", "Month");
        dateProperties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, dateProperties);
        datePicker = new JDatePickerImpl(datePanel, new DateFormatter());
        JFormattedTextField textField2 = datePicker.getJFormattedTextField();
        textField2.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        textField2.setPreferredSize(new Dimension(200, 35));
        datePicker.setTextEditable(true);

        timeComboBox = new JComboBox<>();
        timeComboBox.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        for (int i = 0; i < skinConsultationManager.getTimeSlots().size(); i++) {
            timeComboBox.addItem(String.valueOf(skinConsultationManager.getTimeSlots().get(i)));
        }
        timeComboBox.setPreferredSize(new Dimension(200, 30));

        final DefaultListModel<String> l1 = new DefaultListModel<>();
        for (int i = 0; i < skinConsultationManager.getDoctorsList().size(); i++) {
            l1.addElement(skinConsultationManager.getDoctorsList().get(i).getMedicalLicenseNumber() + " : " +
                    skinConsultationManager.getDoctorsList().get(i).getName() + " " + skinConsultationManager.getDoctorsList().get(i).getSurname() + " : " +
                    skinConsultationManager.getDoctorsList().get(i).getSpecialisation());
        }
        doctorList = new JList<>(l1);
        doctorList.setFont(new Font("Comic Sans", Font.BOLD, 13));
        doctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        doctorList.setSelectedIndex(0);
        doctorList.setVisibleRowCount(10);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(doctorList);
        scrollPane.setPreferredSize(new Dimension(325, 125));

        consultationCostTextField = new JTextField();
        consultationCostTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        consultationCostTextField.setPreferredSize(new Dimension(200, 35));
        consultationCostTextField.setEditable(false);

        consultationNotesTextArea = new JTextArea(5, 20);
        consultationNotesTextArea.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        JScrollPane scrollPane1 = new JScrollPane(consultationNotesTextArea);
        scrollPane1.setPreferredSize(new Dimension(300, 125));
        scrollPane1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        panel3.add(consultationDetailsLabel);
        panel3Layout.putConstraint(SpringLayout.WEST, consultationDetailsLabel, 125, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, consultationDetailsLabel, 5, SpringLayout.NORTH, panel3);

        panel3.add(consultationDateLabel);
        panel3.add(datePicker);
        panel3Layout.putConstraint(SpringLayout.WEST, consultationDateLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, consultationDateLabel, 50, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, datePicker, 50, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, datePicker, -125, SpringLayout.EAST, consultationDateLabel);

        panel3.add(consultationTimeLabel);
        panel3.add(timeComboBox);
        panel3Layout.putConstraint(SpringLayout.WEST, consultationTimeLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, consultationTimeLabel, 105, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, timeComboBox, 100, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, timeComboBox, -125, SpringLayout.EAST, consultationTimeLabel);

        panel3.add(consultationDoctorLabel);
        panel3.add(scrollPane);
        panel3Layout.putConstraint(SpringLayout.WEST, consultationDoctorLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, consultationDoctorLabel, 175, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, scrollPane, 175, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, scrollPane, -125, SpringLayout.EAST, consultationDoctorLabel);

        panel3.add(consultationCostLabel);
        panel3.add(consultationCostTextField);
        panel3Layout.putConstraint(SpringLayout.WEST, consultationCostLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, consultationCostLabel, 330, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, consultationCostTextField, 325, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, consultationCostTextField, -125, SpringLayout.EAST, consultationCostLabel);

        panel3.add(consultationNotesLabel);
        panel3.add(consultationNotesTextArea);
        panel3Layout.putConstraint(SpringLayout.WEST, consultationNotesLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, consultationNotesLabel, 380, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, consultationNotesTextArea, 375, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, consultationNotesTextArea, -125, SpringLayout.EAST, consultationNotesLabel);

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(43, 10, 110));
        saveButton.setForeground(Color.WHITE);
        saveButton.setPreferredSize(new Dimension(200, 40));
        saveButton.setFont(new Font("Comic Sans", Font.BOLD, 14));
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);
        panel4.add(saveButton);

        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(43, 10, 110));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(200, 40));
        submitButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);
        panel4.add(submitButton);

        backButton = new JButton("Back");
        backButton.setBackground(new Color(43, 10, 110));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(200, 40));
        backButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        panel4.add(backButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterUniqueIdButton) {
            ArrayList<JTextField> fields = new ArrayList<>();
            fields.add(patientFirstNameTextField);
            fields.add(patientSurnameTextField);
            fields.add(patientMobileNumberTextField);
            fields.add(dobPicker.getJFormattedTextField());
            try {
                int patientUniqueId = Integer.parseInt(uniqueIdTextField.getText());
                for (int i = 0; i < skinConsultationManager.getPatientsList().size(); i++) {
                    if (skinConsultationManager.getPatientsList().get(i).getUniqueId() == patientUniqueId) {
                        patientFirstNameTextField.setText(skinConsultationManager.getPatientsList().get(i).getName());
                        patientSurnameTextField.setText(skinConsultationManager.getPatientsList().get(i).getSurname());
                        patientMobileNumberTextField.setText(skinConsultationManager.getPatientsList().get(i).getMobileNumber());
                        dobPicker.getJFormattedTextField().setText(String.valueOf(skinConsultationManager.getPatientsList().get(i).getDateOfBirth()));
                        consultationCostTextField.setText("25");
                        fields.forEach((a) -> a.setEditable(false));
                    } else if (!skinConsultationManager.getPatientIdSet().contains(patientUniqueId)) {
                        fields.forEach((a) -> a.setEditable(true));
                        ArrayList<JTextField> textFields = new ArrayList<>();
                        textFields.add(patientFirstNameTextField);
                        textFields.add(patientSurnameTextField);
                        textFields.add(patientMobileNumberTextField);
                        textFields.add(dobPicker.getJFormattedTextField());
                        textFields.add(datePicker.getJFormattedTextField());
                        textFields.forEach((a) -> a.setText(""));
                        consultationNotesTextArea.setText("");
                        timeComboBox.setSelectedIndex(0);
                        consultationCostTextField.setText("15");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Unique ID!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            int consultationId;
            if (skinConsultationManager.getConsultationsList().isEmpty()) {
                consultationId = 1;
                consultationNoTextField.setText(consultationId + "");
                consultationCostTextField.setText("15");
            } else {
                int id = 0;
                for (Consultation consultation : skinConsultationManager.getConsultationsList()) {
                    id = consultation.getConsultationId();
                }
                consultationId = id + 1;
                consultationNoTextField.setText(consultationId + "");
            }
        }
        else if(e.getSource() == saveButton) {
            skinConsultationManager.saveToFile();
        }
        else if (e.getSource() == submitButton) {
            if (consultationNoTextField.getText().isEmpty() || patientFirstNameTextField.getText().isEmpty() || patientSurnameTextField.getText().isEmpty()
                    || patientMobileNumberTextField.getText().isEmpty() || uniqueIdTextField.getText().isEmpty() || consultationCostTextField.getText().isEmpty()
                    || consultationNotesTextArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please Fill Out All The Fields!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int consultationId = Integer.parseInt(consultationNoTextField.getText());
                String patientFirstName = patientFirstNameTextField.getText();
                if (patientFirstName.matches(".*\\d+.*")) {
                    JOptionPane.showMessageDialog(frame, "First Name Cannot Contain Numbers!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    patientFirstName = patientFirstName.substring(0, 1).toUpperCase() + patientFirstName.substring(1).toLowerCase();
                    String patientSurname = patientSurnameTextField.getText();
                    if (patientSurname.matches(".*\\d+.*")) {
                        JOptionPane.showMessageDialog(frame, "Surname Cannot Contain Numbers!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        patientSurname = patientSurname.substring(0, 1).toUpperCase() + patientSurname.substring(1).toLowerCase();
                        String mobileNumber = patientMobileNumberTextField.getText();
                        if (!mobileNumber.matches("^[+94]{3}-\\d{9}$")) {
                            JOptionPane.showMessageDialog(frame, "Invalid Mobile Number!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int patientUniqueID = Integer.parseInt(uniqueIdTextField.getText());
                            LocalDate dateOfBirth = LocalDate.parse(dobPicker.getJFormattedTextField().getText());
                            Patient newPatient = new Patient(patientFirstName, patientSurname, mobileNumber, dateOfBirth, patientUniqueID);
                            LocalDate consultationDate = LocalDate.parse(datePicker.getJFormattedTextField().getText());
                            String time = (String) timeComboBox.getSelectedItem();
                            assert time != null;
                            LocalTime selectedTime = LocalTime.parse(time);
                            Doctor doctor = null;
                            Doctor initialDoctor = null;
                            String data = "";
                            if (doctorList.getSelectedIndex() != -1) {
                                data = doctorList.getSelectedValue();
                            }
                            int doctorId = Integer.parseInt(data.replaceAll("[^0-9]", ""));
                            for (int i = 0; i < skinConsultationManager.getDoctorsList().size(); i++) {
                                if (skinConsultationManager.getDoctorsList().get(i).getMedicalLicenseNumber() == doctorId) {
                                    doctor = skinConsultationManager.getDoctorsList().get(i);
                                    initialDoctor = doctor;
                                }
                            }
                            assert doctor != null;
                            String specialisation = doctor.getSpecialisation();
                            ArrayList<Doctor> tempDoctorList = new ArrayList<>();
                            for (Doctor value : skinConsultationManager.getDoctorsList()) {
                                if (value.getSpecialisation().equals(specialisation)) {
                                    if (!value.equals(doctor)) {
                                        tempDoctorList.add(value);
                                    }
                                }
                            }
                            boolean validEntry = false;
                            do {
                                int count = 1;
                                if (!skinConsultationManager.getConsultationsList().isEmpty()) {
                                    for (Consultation consultation : skinConsultationManager.getConsultationsList()) {
                                        if (consultation.getDoctor().equals(doctor) && consultation.getDate().equals(consultationDate)
                                                && consultation.getTime().equals(selectedTime)) {
                                            if (tempDoctorList.isEmpty()) {
                                                JOptionPane.showMessageDialog(frame, "Doctor " + doctor.getName() + " " + doctor.getSurname() + " Is Not Available At " + selectedTime
                                                        + " On " + consultationDate + "!", "Error", JOptionPane.ERROR_MESSAGE);
                                                JOptionPane.showMessageDialog(frame, "No Other Doctors Available For " + specialisation + ", Unable To Book Consultation!",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                                //noinspection UnusedAssignment
                                                consultationId--;
                                                return;
                                            }
                                            if (count == 1) {
                                                JOptionPane.showMessageDialog(frame, "Doctor " + doctor.getName() + " " + doctor.getSurname() +
                                                        " Is Not Available At " + consultation.getTime() + " On " + consultation.getDate() +
                                                        "!", "Error", JOptionPane.ERROR_MESSAGE);
                                                count++;
                                            }
                                            int randomIndex = (int) (Math.random() * tempDoctorList.size());
                                            doctor = tempDoctorList.get(randomIndex);
                                        } else {
                                            validEntry = true;
                                        }
                                    }
                                } else {
                                    validEntry = true;
                                }
                            }
                            while (!validEntry);
                            if (!initialDoctor.equals(doctor)) {
                                JOptionPane.showMessageDialog(frame, "Doctor " + doctor.getName() + " " + doctor.getSurname() +
                                        " (ID :" + doctor.getMedicalLicenseNumber() + ") Selected For Consultation.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            int cost = Integer.parseInt(consultationCostTextField.getText());
                            String notes = (consultationNotesTextArea.getText());
                            notes = skinConsultationManager.encrypt(notes);
                            Consultation newConsultation = new Consultation(consultationId, doctor, newPatient, consultationDate, selectedTime, cost, notes);
                            ArrayList<JTextField> textFields = new ArrayList<>();
                            textFields.add(uniqueIdTextField);
                            textFields.add(patientFirstNameTextField);
                            textFields.add(patientSurnameTextField);
                            textFields.add(patientMobileNumberTextField);
                            textFields.add(dobPicker.getJFormattedTextField());
                            textFields.add(datePicker.getJFormattedTextField());
                            textFields.add(consultationCostTextField);
                            textFields.forEach((a) -> a.setText(""));
                            consultationNotesTextArea.setText("");
                            timeComboBox.setSelectedIndex(0);
                            JOptionPane.showMessageDialog(frame, "Consultation Saved Successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                            skinConsultationManager.getPatientsList().add(newPatient);
                            skinConsultationManager.getConsultationsList().add(newConsultation);
                        }
                    }
                }
            }
        } else if (e.getSource() == backButton) {
            frame.dispose();
            new UserInterface();
        }
    }
}

class ViewConsultationList implements ActionListener {
    WestminsterSkinConsultationManager skinConsultationManager = new WestminsterSkinConsultationManager();
    JFrame frame;
    JTextField consultationNoTextField, doctorNameIdTextField, doctorSpecialisationTextField, patientNameIdTextField,
            viewDateTextField, viewTimeTextField, viewCostTextField;
    JTextArea viewNotesTextArea;
    final String[] consultationColumnNames = {"Consultation No", "Patient ID", "Date"};
    JTable viewConsultationsTable;
    Object[][] consultationsObject;
    JButton viewDetailsButton, backButton, decryptButton;

    public ViewConsultationList() {

        frame = new JFrame("Westminster Skin Consultation Manager : View Consultations");
        frame.setLayout(new BorderLayout());
        frame.setSize(1024, 720);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.add(panel5, BorderLayout.CENTER);

        panel1.setPreferredSize(new Dimension(100, 75));
        panel2.setPreferredSize(new Dimension(500, 100));
        panel3.setPreferredSize(new Dimension(500, 100));
        panel4.setPreferredSize(new Dimension(100, 125));
        panel5.setPreferredSize(new Dimension(50, 100));

        panel1.setBackground(new Color(200, 244, 244));
        panel2.setBackground(new Color(200, 244, 244));
        panel3.setBackground(new Color(200, 244, 244));
        panel4.setBackground(new Color(200, 244, 244));
        panel5.setBackground(new Color(200, 244, 244));

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        SpringLayout panel3Layout = new SpringLayout();
        panel3.setLayout(panel3Layout);
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER,75,25));

        JLabel label = new JLabel("List Of Consultations");
        label.setForeground(new Color(43, 10, 110));
        label.setFont(new Font("Comic Sans", Font.BOLD, 32));
        panel1.add(label);

        consultationsObject = new Object[1000][1000];
        //noinspection rawtypes
        final Class[] columnClass = new Class[] {
                Integer.class, Integer.class, LocalDate.class
        };
        DefaultTableModel model = new DefaultTableModel(consultationsObject, consultationColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };
        viewConsultationsTable = new JTable(model);
        if (skinConsultationManager.getConsultationsList().size() != 0) {
            for (int i = 0; i < skinConsultationManager.getConsultationsList().size(); i++) {
                consultationsObject[i][0] = (i + 1);
                consultationsObject[i][1] = skinConsultationManager.getConsultationsList().get(i).getPatient().getUniqueId();
                consultationsObject[i][2] = skinConsultationManager.getConsultationsList().get(i).getDate();
                viewConsultationsTable = new JTable(consultationsObject, consultationColumnNames);
            }
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        viewConsultationsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        viewConsultationsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        viewConsultationsTable.setEnabled(false);
        viewConsultationsTable.getTableHeader().setReorderingAllowed(false);
        viewConsultationsTable.getTableHeader().setResizingAllowed(false);
        JScrollPane consultationsScrollPane = new JScrollPane(viewConsultationsTable);
        panel2.add(consultationsScrollPane);

        JLabel consultationNoLabel = new JLabel("Enter Consultation No: ");
        consultationNoLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        consultationNoLabel.setForeground(new Color(40, 6, 99));
        consultationNoLabel.setBounds(30, 500, 250, 20);
        panel2.add(consultationNoLabel);

        consultationNoTextField = new JTextField();
        consultationNoTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        consultationNoTextField.setPreferredSize(new Dimension(200,30));
        panel2.add(consultationNoTextField);

        viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setBackground(new Color(43, 10, 110));
        viewDetailsButton.setForeground(Color.WHITE);
        viewDetailsButton.setFocusable(false);
        viewDetailsButton.setPreferredSize(new Dimension(200, 40));
        viewDetailsButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        viewDetailsButton.addActionListener(this);
        panel4.add(viewDetailsButton);

        JLabel doctorNameIDLabel = new JLabel("Doctor Name & ID:");
        doctorNameIDLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        doctorNameIDLabel.setForeground(new Color(40, 6, 99));
        doctorNameIDLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        doctorNameIDLabel.setPreferredSize(new Dimension(250, 20));

        doctorNameIdTextField = new JTextField();
        doctorNameIdTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        doctorNameIdTextField.setPreferredSize(new Dimension(200, 25));

        JLabel doctorSpecialisationLabel = new JLabel("Doctor Specialisation:");
        doctorSpecialisationLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        doctorSpecialisationLabel.setForeground(new Color(40, 6, 99));
        doctorSpecialisationLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        doctorSpecialisationLabel.setPreferredSize(new Dimension(250, 20));

        doctorSpecialisationTextField = new JTextField();
        doctorSpecialisationTextField.setFont(new Font("Comic Sans", Font.BOLD, 12));
        doctorSpecialisationTextField.setPreferredSize(new Dimension(200, 25));

        JLabel patientNameIdLabel = new JLabel("Patient Name & ID:");
        patientNameIdLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        patientNameIdLabel.setForeground(new Color(40, 6, 99));
        patientNameIdLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        patientNameIdLabel.setPreferredSize(new Dimension(250, 20));

        patientNameIdTextField = new JTextField();
        patientNameIdTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        patientNameIdTextField.setPreferredSize(new Dimension(200, 25));

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        dateLabel.setForeground(new Color(40, 6, 99));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        dateLabel.setPreferredSize(new Dimension(250, 20));

        viewDateTextField = new JTextField();
        viewDateTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        viewDateTextField.setPreferredSize(new Dimension(200, 25));

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        timeLabel.setForeground(new Color(40, 6, 99));
        timeLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        timeLabel.setPreferredSize(new Dimension(250, 20));

        viewTimeTextField = new JTextField();
        viewTimeTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        viewTimeTextField.setPreferredSize(new Dimension(200, 25));

        JLabel costLabel = new JLabel("Cost:");
        costLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        costLabel.setForeground(new Color(40, 6, 99));
        costLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        costLabel.setPreferredSize(new Dimension(250, 20));

        viewCostTextField = new JTextField();
        viewCostTextField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        viewCostTextField.setPreferredSize(new Dimension(200, 25));

        JLabel notesLabel = new JLabel("Notes:");
        notesLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        notesLabel.setForeground(new Color(40, 6, 99));
        notesLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        notesLabel.setPreferredSize(new Dimension(250, 20));

        viewNotesTextArea = new JTextArea(5,20);
        viewNotesTextArea.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        JScrollPane scrollPane1 = new JScrollPane(viewNotesTextArea);
        scrollPane1.setPreferredSize(new Dimension(300, 90));

        panel3.add(doctorNameIDLabel);
        panel3.add(doctorNameIdTextField);
        panel3Layout.putConstraint(SpringLayout.WEST, doctorNameIDLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, doctorNameIDLabel, 30, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, doctorNameIdTextField, 30, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, doctorNameIdTextField, -50, SpringLayout.EAST, doctorNameIDLabel);

        panel3.add(doctorSpecialisationLabel);
        panel3.add(doctorSpecialisationTextField);
        panel3Layout.putConstraint(SpringLayout.WEST, doctorSpecialisationLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, doctorSpecialisationLabel, 80, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, doctorSpecialisationTextField, 80, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, doctorSpecialisationTextField, -50, SpringLayout.EAST, doctorSpecialisationLabel);

        panel3.add(patientNameIdLabel);
        panel3.add(patientNameIdTextField);
        panel3Layout.putConstraint(SpringLayout.WEST, patientNameIdLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, patientNameIdLabel, 130, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, patientNameIdTextField, 130, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, patientNameIdTextField, -50, SpringLayout.EAST, patientNameIdLabel);

        panel3.add(dateLabel);
        panel3.add(viewDateTextField);
        panel3Layout.putConstraint(SpringLayout.WEST, dateLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, dateLabel, 180, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, viewDateTextField, 180, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, viewDateTextField, -50, SpringLayout.EAST, dateLabel);

        panel3.add(timeLabel);
        panel3.add(viewTimeTextField);
        panel3Layout.putConstraint(SpringLayout.WEST, timeLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, timeLabel, 230, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, viewTimeTextField, 230, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, viewTimeTextField, -50, SpringLayout.EAST, timeLabel);

        panel3.add(costLabel);
        panel3.add(viewCostTextField);
        panel3Layout.putConstraint(SpringLayout.WEST, costLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, costLabel, 280, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, viewCostTextField, 280, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, viewCostTextField, -50, SpringLayout.EAST, costLabel);

        panel3.add(notesLabel);
        panel3.add(viewNotesTextArea);
        panel3Layout.putConstraint(SpringLayout.WEST, notesLabel, 25, SpringLayout.WEST, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, notesLabel, 330, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.NORTH, viewNotesTextArea, 330, SpringLayout.NORTH, panel3);
        panel3Layout.putConstraint(SpringLayout.WEST, viewNotesTextArea, -50, SpringLayout.EAST, notesLabel);

        backButton = new JButton("Back");
        backButton.setBackground(new Color(43, 10, 110));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusable(false);
        backButton.setPreferredSize(new Dimension(200, 40));
        backButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        backButton.addActionListener(this);
        panel4.add(backButton);

        decryptButton = new JButton("Decrypt");
        decryptButton.setBackground(new Color(43, 10, 110));
        decryptButton.setForeground(Color.WHITE);
        decryptButton.setFocusable(false);
        decryptButton.setPreferredSize(new Dimension(200, 40));
        decryptButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        decryptButton.addActionListener(this);
        panel4.add(decryptButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== viewDetailsButton){
            if (consultationNoTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "Please Enter Consultation Number!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int consultationNo = Integer.parseInt(consultationNoTextField.getText());
                if (consultationNo < 1 || consultationNo > skinConsultationManager.getConsultationsList().size()) {
                    ArrayList<JTextField> textFields = new ArrayList<>();
                    textFields.add(doctorNameIdTextField);
                    textFields.add(doctorSpecialisationTextField);
                    textFields.add(patientNameIdTextField);
                    textFields.add(viewDateTextField);
                    textFields.add(viewTimeTextField);
                    textFields.add(viewCostTextField);
                    textFields.forEach((a) -> a.setText(""));
                    viewNotesTextArea.setText("");
                    JOptionPane.showMessageDialog(frame, "Consultation Number Not Available!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    doctorNameIdTextField.setText(skinConsultationManager.getConsultationsList().get(consultationNo - 1).getDoctor().getName() + " "+
                            skinConsultationManager.getConsultationsList().get(consultationNo - 1).getDoctor().getSurname() +
                            " (ID-" + (skinConsultationManager.getConsultationsList().get(consultationNo - 1).getDoctor().getMedicalLicenseNumber()) + ")");
                    doctorSpecialisationTextField.setText(skinConsultationManager.getConsultationsList().get(consultationNo - 1).getDoctor().getSpecialisation());
                    patientNameIdTextField.setText(skinConsultationManager.getConsultationsList().get(consultationNo - 1).getPatient().getName() +
                            " "+skinConsultationManager.getConsultationsList().get(consultationNo - 1).getPatient().getSurname()+
                            " (ID-" + (skinConsultationManager.getConsultationsList().get(consultationNo - 1).getPatient().getUniqueId()) + ")");
                    viewDateTextField.setText(skinConsultationManager.getConsultationsList().get(consultationNo - 1).getDate() + "");
                    viewTimeTextField.setText(String.valueOf(skinConsultationManager.getConsultationsList().get(consultationNo - 1).getTime()));
                    viewCostTextField.setText("GBP " + skinConsultationManager.getConsultationsList().get(consultationNo - 1).getCost());
                    viewNotesTextArea.setText(skinConsultationManager.getConsultationsList().get(consultationNo - 1).getNotes());
                }
            }
        }
        else if (e.getSource() == decryptButton) {
            if (consultationNoTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Select Consultation First!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                int number;
                String key = JOptionPane.showInputDialog("Enter Key For Decryption:- ");
                if (key != null){
                    if (key.equals("1234")) {
                        try{
                            number = Integer.parseInt(consultationNoTextField.getText());
                            if (number < 1 || number > skinConsultationManager.getConsultationsList().size()) {
                                JOptionPane.showMessageDialog(frame, "Invalid Consultation Number!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                for (Consultation consultation : skinConsultationManager.getConsultationsList()) {
                                    if (consultation.getConsultationId() == (number)) {
                                        String notes = (skinConsultationManager.decrypt(skinConsultationManager.getConsultationsList().get(number-1).getNotes()));
                                        viewNotesTextArea.setText(notes);
                                        decryptButton.setEnabled(false);
                                    }
                                }
                            }
                        }
                        catch (Exception ex){
                            JOptionPane.showMessageDialog(frame, "Invalid Consultation Number!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
        else if (e.getSource() == backButton) {
            frame.dispose();
            new UserInterface();
        }
    }
}

class ViewDoctorList implements ActionListener {
    WestminsterSkinConsultationManager skinConsultationManager = new WestminsterSkinConsultationManager();
    JFrame frame;
    JButton sortButton, backButton;
    JTable viewDoctorsTable;

    public ViewDoctorList(){

        frame = new JFrame("Westminster Skin Consultation Manager : View Doctors");
        frame.setLayout(new BorderLayout());
        frame.setSize(1024, 720);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        frame.add(panel1,BorderLayout.NORTH);
        frame.add(panel2,BorderLayout.WEST);
        frame.add(panel3,BorderLayout.EAST);
        frame.add(panel4,BorderLayout.SOUTH);
        frame.add(panel5,BorderLayout.CENTER);

        panel1.setPreferredSize(new Dimension(100,100));
        panel2.setPreferredSize(new Dimension(25,100));
        panel3.setPreferredSize(new Dimension(25,100));
        panel4.setPreferredSize(new Dimension(100,100));
        panel5.setPreferredSize(new Dimension(100,100));

        panel1.setBackground(new Color(200, 244, 244));
        panel2.setBackground(new Color(200, 244, 244));
        panel3.setBackground(new Color(200, 244, 244));
        panel4.setBackground(new Color(200, 244, 244));
        panel5.setBackground(new Color(200, 244, 244));

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,0,25));
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER,75,25));

        JLabel label = new JLabel("List Of Doctors");
        label.setForeground(new Color(43, 10, 110));
        label.setFont(new Font("Comic Sans", Font.BOLD, 32));
        panel1.add(label);

        DoctorTableModel doctorTableModel = new DoctorTableModel(skinConsultationManager.getDoctorsList());
        viewDoctorsTable = new JTable(doctorTableModel);
        viewDoctorsTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        viewDoctorsTable.setDefaultRenderer(Integer.class, centerRenderer);
        viewDoctorsTable.setDefaultRenderer(LocalDate.class, centerRenderer);
        viewDoctorsTable.setEnabled(false);
        viewDoctorsTable.getTableHeader().setReorderingAllowed(false);
        viewDoctorsTable.getTableHeader().setResizingAllowed(false);
        viewDoctorsTable.setAutoCreateRowSorter(true);
        viewDoctorsTable.setRowHeight(viewDoctorsTable.getRowHeight() + 20);
        JScrollPane doctorScrollPane = new JScrollPane(viewDoctorsTable);
        doctorScrollPane.setPreferredSize(new Dimension(900,400));
        panel5.add(doctorScrollPane);

        sortButton = new JButton("Sort");
        sortButton.setBackground(new Color(43, 10, 110));
        sortButton.setForeground(Color.WHITE);
        sortButton.setFont(new Font("Comic Sans", Font.BOLD, 14));
        sortButton.setFocusable(false);
        sortButton.setPreferredSize(new Dimension(200, 40));
        sortButton.addActionListener(this);
        panel4.add(sortButton);

        backButton = new JButton("Back");
        backButton.setBackground(new Color(43, 10, 110));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Comic Sans", Font.BOLD, 14));
        backButton.setFocusable(false);
        backButton.setPreferredSize(new Dimension(200, 40));
        backButton.addActionListener(this);
        panel4.add(backButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sortButton){
            viewDoctorsTable.getRowSorter().toggleSortOrder(1);
        }
        else if(e.getSource() == backButton){
            frame.dispose();
            new UserInterface();
        }
    }
}


class DoctorTableModel extends AbstractTableModel {
    final String[] doctorColumnNames = {"Name", "Surname", "Mobile Number", "Date Of Birth",
            "Medical License Number", "Specialisation"};
    ArrayList<Doctor> doctorsList;

    public DoctorTableModel(ArrayList<Doctor> list) {
        this.doctorsList = list;
    }

    @Override
    public int getRowCount() {
        return doctorsList.size();
    }

    @Override
    public int getColumnCount() {
        return doctorColumnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        java.io.Serializable temp = null;
        if (col == 0) {
            temp = doctorsList.get(row).getName();
        } else if (col == 1) {
            temp = doctorsList.get(row).getSurname();
        } else if (col == 2) {
            temp = doctorsList.get(row).getMobileNumber();
        } else if (col == 3) {
            temp = doctorsList.get(row).getDateOfBirth();
        } else if (col == 4) {
            temp = doctorsList.get(row).getMedicalLicenseNumber();
        } else if (col == 5) {
            temp = doctorsList.get(row).getSpecialisation();
        }
        return temp;
    }

    @Override
    public String getColumnName(int col) {
        return doctorColumnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }
}

class DateFormatter extends JFormattedTextField.AbstractFormatter {

    private final String datePattern = "yyyy-MM-dd";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}
