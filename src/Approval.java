/**
 * Approval class which creates the approval screen and returns an email to the user once the validations pass or returns to the review screen if it doesn't pass.
 * @author Siddarth Mallemudi
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Approval extends JPanel{

    /**
     * Document Request form.
     */
    private DocumentRequest DRform;

    /**
     * Social Security Number.
     */
    private String SSN;

    /**
     * First name of user.
     */
    private String firstname;

    /**
     * Last name of user.
     */
    private String lastname;

    /**
     * Entry from the workflow table.
     */
    private Workflow entry;

    /**
     * User's email address.
     */
    private String email;

    /*
     * User's immigrant ID.
     */
    private int ID;

    /**
     * boolean for when the screen is no longer needed.
     */
    private boolean finished;

    /**
     * Approval constructor to instantiate the approval class and shows screen.
     * @param entry workflow table entry.
     * @param DRForm Document request form.
     */
    public Approval(Workflow entry, DocumentRequest DRForm){
        loadData(entry, DRForm);
        finished = false;
        showScreen();
    }

    /**
     * Shows the screen for approving the data.
     */
    public void showScreen(){
        //
        JFrame frame = new JFrame("Approval Screen");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);

        //Components of the frame.
        JLabel displayArea = new JLabel();
        JButton validateButton = new JButton("Validate Data");
        JButton returnButton = new JButton("Return to Previous Screen");
        JButton exitButton = new JButton("Exit Screen");

        String screenContent = "Name: " + firstname + " " + lastname + "\nSSN: " + SSN + "\nDocument Request Details:\n" +
                        "\tDocument : " + DRform.getField() + "\nID: " + ID + "\nEmail: " + email;

        displayArea.setText(screenContent);
        displayArea.setHorizontalAlignment(JLabel.CENTER);

        //return button actions.
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement logic for returning to the previous screen
                clearScreen(frame);
            }
        });

        //adds buttons to panel.
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(displayArea);
        panel.add(validateButton);
        panel.add(returnButton);
        frame.add(panel, BorderLayout.CENTER);

        //validate button to panel.
        validateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkData()) {
                    displayArea.setText("Validation Successful!\n Documents:" + DRform.getField() + " have been sent to your email.");
                    panel.remove(returnButton);
                    panel.remove(validateButton);
                    panel.add(exitButton);
            
                    panel.revalidate();
                    panel.repaint();

                    finished = true;

                } else {
                    displayArea.setText("Validation Failed.\n Return to previous screen to change information.");
                }
            }
        });

        //exit button action.
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearScreen(frame);
            }
        });

        frame.setVisible(true);
    }
    
    /**
     * Loads data to use for approval.
     * @param entry workflow table entry.
     * @param DRForm Documents Request form.
     */
    public void loadData(Workflow entry, DocumentRequest DRForm){
        this.DRform = DRForm;
        this.entry = entry;
        this.SSN = entry.getEntry().getSSN();
        this.firstname = entry.getEntry().getFirstName();
        this.lastname = entry.getEntry().getLastName(); 
        this.email = entry.getEntry().getEmail();
        this.ID = entry.getEntry().getID();
    }

    /**
     * Checks the data.
     * @return boolean of whether or not the data is valid.
     */
    public boolean checkData() {

        if (DRform.getField() == null) {
            return false;
        }

        if (entry == null) {
            return false;
        } else {
            if (firstname == null) {
                return false;
            }

            if (lastname == null) {
                return false;
            }

            if (email == null) {
                return false;
            }

            if (SSN == null) {
                return false;
            } else {
                try {
                    int ssnValue = Integer.parseInt(SSN);
                    if (ssnValue < 0 || ssnValue > 9999) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Generates email.
     * @return returns string of email.
     */
    public String generateEmail(){
        return "Documents: " + DRform.getField();
    }

    /**
     * Clears frame.
     * @param frame frame to be cleared.
     */
    public void clearScreen(JFrame frame){
        frame.dispose();
    }

    /**
     * Returns the status of the screen.
     * @return status of screen.
     */
    public boolean getFinished(){
        return finished;
    }
}
