import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Controller {
    public int numOfStudents;
    Student[] allStudents;

    public Controller() throws FileNotFoundException {
        numOfStudents=0;
        allStudents= new Student[10];
        readStudentsFile();
    }
    public boolean addNewStudent(int id, String firstName, String lastName, int registrationYear) {
        if (numOfStudents < allStudents.length) {
            Student.counter++;
            Student s = new Student(id, firstName, lastName, registrationYear);
            allStudents[numOfStudents] = s;
            numOfStudents++;
            return true;
        } else
            return false;
    }
    public Student[] viewAllStudents()
    {
      return allStudents;
    }
    public Student searchForStudent(int id)
    {
        int i=0;
        boolean found=false;
        while (!found&&i<numOfStudents)
        {
            if (allStudents[i].getId()==id)
                found=true;
            else
                i++;

        }
        if (i<numOfStudents)
            return allStudents[i];
        else
            return null;
    }

    public int searchForStudentIndex(int id)
    {
        int i=0;
        boolean found=false;
        while (!found&&i<numOfStudents)
        {
            if (allStudents[i].getId()==id)
                found=true;
            else
                i++;

        }
        if (i<numOfStudents)
            return i;
        else
            return -1;
    }

    public boolean DeleteStudent(int id)
    {
        int index=searchForStudentIndex(id);
        if (index!=-1)
        {int i=index;
            while (i<numOfStudents)
            {allStudents[i]=allStudents[i+1];
                i++;}
            allStudents[i]=null;
            numOfStudents--;
            return true;}
        else return false;
    }
    public boolean checkForDuplicate(Student s)
    {
        for (int i = 0; i <numOfStudents ; i++) {
            if (allStudents[i].equals(s))
                return true;
        }
        return false;
    }
    public boolean editStudentData(int id,int newID,String newFirstName,String newLastName,int newYear)
    {
        int index=searchForStudentIndex(id);
        if (index!=-1)
        {
            allStudents[index].setId(newID);
            allStudents[index].setFirstName(newFirstName);
            allStudents[index].setLastName(newLastName);
            allStudents[index].setRegistrationYear(newYear);
            return true;}
        else return false;
    }
    public void readStudentsFile() throws FileNotFoundException {
        Scanner in= new Scanner(new File("D:\\StudentFile.txt"));
    while (in.hasNext())
    {
        int i=in.nextInt();
        String f=in.next();
        String l=in.next();
        int y=in.nextInt();
        addNewStudent(i,f,l,y);
    }
in.close();
    }
    public void writeStudentsFile() throws FileNotFoundException {
        PrintWriter writer=new PrintWriter(new File("D:\\StudentFile.txt"));
        int i=0;
        while (i<numOfStudents) {
            writer.write(allStudents[i].getId() + " " + allStudents[i].getFirstName()
                    + " " + allStudents[i].getLastName() + " " + allStudents[i].getRegistrationYear() + "\n");

            i++;
        }
        writer.close();
        }
}
