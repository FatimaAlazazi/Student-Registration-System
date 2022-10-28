import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GUI {

    private JButton addNewStudentButton;
    private JButton viewAllStudentButton;
    private JButton searchForStudentButton;
    private JPanel allCards;
    private JPanel c1;
    private JPanel c2;
    private JPanel c3;
    private JTextField sID;
    private JTextField fName;
    private JTextField lName;
    private JTextField year;
    private JPanel mainPannel;
    private JTextArea allStudentData;
    private JTextField id;
    private JTextArea searchedData;
    private JButton searchButton;
    private JButton saveToFileButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel c4;
    private JTextField idToEdit;
    private JTextField newYear;
    private JTextField newLName;
    private JTextField newFname;
    private JTextField newID;
    private JButton editButton1;
    private JPanel c5;
    private JTextField idToDelete;
    private JButton deleteStudentButton;
    private JLabel studentsNumber;
    Controller c;
    public GUI() throws FileNotFoundException {
      c= new Controller();
      studentsNumber.setText("NUMBER OF OUR STUDENTS "+Student.counter);
        addNewStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allCards.removeAll();
                allCards.add(c1);
                allCards.repaint();
                allCards.revalidate();
                if (!sID.getText().equals("")&&!fName.getText().equals("")&&!lName.getText().equals("")&&!year.getText().equals("")) {
                    int i = Integer.parseInt(sID.getText());
                    String f = fName.getText();
                    String l = lName.getText();
                    int y = Integer.parseInt(year.getText());
                    boolean added = c.addNewStudent(i, f, l, y);
                    if (added) {
                        JOptionPane.showMessageDialog(null, "Added Successfully");
                        sID.setText("");fName.setText("");lName.setText("");year.setText("");
                        studentsNumber.setText("NUMBER OF OUR STUDENTS "+Student.counter);


                    }
                    else JOptionPane.showMessageDialog(null, "some thing went wrong");

                }
            }
        });
        viewAllStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Student[]all= c.viewAllStudents();
               allStudentData.setText("ID\tFirst Name\t Last Name\t Registration Year");
                for (int i = 0; i <c.numOfStudents ; i++) {
                    allStudentData.setText(allStudentData.getText()+"\n"+all[i].getId()+"\t"+all[i].getFirstName()+"\t"+all[i].getLastName()+"\t"+all[i].getRegistrationYear());

                }
                allCards.removeAll();
                allCards.add(c2);
                allCards.repaint();
                allCards.revalidate();
            }
        });
        searchForStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allCards.removeAll();
                allCards.add(c3);
                allCards.repaint();
                allCards.revalidate();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!id.getText().equals(""))
                {
                   int i= Integer.parseInt(id.getText());
                  Student s= c.searchForStudent(i);
                  if (s!=null)
                      searchedData.setText(s.getId()+" "+s.getFirstName()+" "
                      +s.getLastName()+" "+s.getRegistrationYear());
                  else searchedData.setText("not found");
                }
            }
        });
        saveToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c.writeStudentsFile();
                    JOptionPane.showMessageDialog(null,"Data was Saved Successfully");
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"some thing went wrong");
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allCards.removeAll();
                allCards.add(c4);
                allCards.repaint();
                allCards.revalidate();

            }
        });
        editButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idToEdit.getText().equals("")) {
                    int i = Integer.parseInt(idToEdit.getText());
                    int nID=Integer.parseInt(newID.getText());
                    String nFName=newFname.getText();
                    String nLName=newLName.getText();
                    int nYear=Integer.parseInt(newYear.getText());
                    boolean edited=c.editStudentData(i,nID,nFName,nLName,nYear);
                if (edited) {
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    idToEdit.setText("");
                    newID.setText("");
                    newFname.setText("");
                    newLName.setText("");
                    newYear.setText("");
                }else JOptionPane.showMessageDialog(null,"No Student with This ID '"+idToEdit+"'");
                }
            }
        });
        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=Integer.parseInt(idToDelete.getText());
                Student deletedStudent=c.searchForStudent(i);
                if (deletedStudent!=null) {
               int confirm = JOptionPane.showConfirmDialog(null, "Do you want to delete the following Student \n" + deletedStudent.print() + "?");
if (confirm==0) {
 boolean delete=   c.DeleteStudent(i);
    if (delete)JOptionPane.showMessageDialog(null, "Deleted Successfully");
    else     JOptionPane.showMessageDialog(null, "could not delete");

}
}else JOptionPane.showMessageDialog(null,"No Student with this ID "+idToDelete.getText());
            }

        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allCards.removeAll();
                allCards.add(c5);
                allCards.repaint();
                allCards.revalidate();
            }
        });
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Student Registration System");
        frame.setLocation(500,300);
        frame.setSize(1000,600);
        frame.setContentPane(new GUI().mainPannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);
    }
}
