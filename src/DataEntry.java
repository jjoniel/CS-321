import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DataEntry extends JPanel
{
    private JTextField firstname, lastname, ssn;
    public DataEntry() 
    {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Enter Your Details");
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel questions = new JPanel();
        questions.setLayout(new GridLayout(3,2));
        JLabel first = new JLabel("First Name: ");
        firstname = new JTextField(30);
        JLabel last = new JLabel("Last Name: ");
        lastname = new JTextField(30);
        JLabel last4 = new JLabel("SSN Last 4 Digits: ");
        ssn = new JTextField(30);

        questions.add(first);
        questions.add(firstname);
        questions.add(last);
        questions.add(lastname);
        questions.add(last4);
        questions.add(ssn);

        add(questions, BorderLayout.CENTER);

        JButton submit = new JButton("SUBMIT");
        submit.addActionListener(new SubmitListener(this));
        add(submit, BorderLayout.SOUTH);
    }

    public static boolean isAlphabetic(String s)
    {
        s = s.trim();
        for(int x = 0; x < s.length(); x ++)
        {
            if(!Character.isAlphabetic(s.charAt(x)))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(String s)
    {
        s = s.trim();
        for(int x = 0; x < s.length(); x ++)
        {
            if(!Character.isDigit(s.charAt(x)))
            {
                return false;
            }
        }
        return true;
    }

    public void validateData()
    {
        if(!isAlphabetic(firstname.getText()) || firstname.getText().length() == 0) 
        {
            JOptionPane.showMessageDialog(null, "Please Enter A Valid First Name!");
        }
        else if(!isAlphabetic(lastname.getText()) || lastname.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please Enter A Valid Last Name!");
        }
        else if(!isNumeric(ssn.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please Enter A Valid SSN!");
        }
        else if(ssn.getText().trim().length() < 4)
        {
            JOptionPane.showMessageDialog(null, "Please Enter Last 4 Digits Of SSN!");
        }
        else if(ssn.getText().trim().length() > 4)
        {
            JOptionPane.showMessageDialog(null, "Please Only Enter Last 4 Digits Of SSN!");
        }
    }

    private class SubmitListener implements ActionListener 
    {
        private DataEntry form;
        public SubmitListener(DataEntry d)
        {
            this.form = d;
        }
        public void actionPerformed(ActionEvent e)
        {
            form.validateData();
        }
    }
}

class FormFrame extends JFrame 
{
    public FormFrame() 
    {
        setTitle("Form");

        DataEntry form = new DataEntry();
        add(form);

        setSize(new Dimension(400, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) 
    {
        FormFrame frame = new FormFrame();
    }
}
