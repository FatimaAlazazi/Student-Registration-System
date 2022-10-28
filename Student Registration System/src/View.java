import java.io.FileNotFoundException;
import java.util.Scanner;

public class View {
    static Scanner in= new Scanner(System.in);
    static Controller cont;

    static {
        try {
            cont = new Controller();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public  void start() throws FileNotFoundException {
        System.out.println(" ____________________________________");
        System.out.println("|    Student's Registration System     |");
        System.out.println(" ____________________________________");

        while (true)
        {
            System.out.println(" ____________________________________");

            System.out.println("1: Add New Student\t\t2: View All Students\n"+
                            "3: Search For Student\t\t4: Edit Student\n" +
                            "5: Delete Student\t\t0: Exit" );

            System.out.print("your Choice:");

                int choice=in.nextInt();
                switch (choice)
                {
                    case 1:
                        add();
                        break;
                    case  2:
                        view();
                        break;
                    case 3:
                        find();
                        break;
                    case 4:
                        edit();
                        break;
                    case 5:
                        delete();
                        break;
                    case 0:
                        cont.writeStudentsFile();
                        System.out.println("GOOD Bye");
                        System.exit(0);
                }
            }

    }
    public  void add()
    {
      int id,year;
      String fName,lName;
        System.out.println( "____________________________");
        System.out.println("| New Student Form          |");
        System.out.println( "____________________________");
        System.out.println("ID  First Name      Last Name   Registration Year");
        id=in.nextInt();
        fName=in.next();
        lName=in.next();
        year=in.nextInt();
        boolean duplicated=cont.checkForDuplicate(new Student(id,fName,lName,year));
        if (duplicated)
            System.out.println(" there is already student with this data");
        else {
            boolean success = cont.addNewStudent(id, fName, lName, year);
            if (success)
                System.out.println("New Student Added Successfully");
            else
                System.out.println("incomplete addition process");

        }
    }
    public  void view()
    {
        System.out.println(" ________________________________________");
        System.out.println("|       ALL Registered Students Report    | ");
        System.out.println(" ________________________________________");
        System.out.println("ID  First Name      Last Name   Registration Year");
        System.out.println(" ________________________________________");

        Student[]all=cont.viewAllStudents();
        for (int i = 0; i <all.length ; i++) {
            if (all[i]!=null)
                System.out.println(all[i].print());
      }
        System.out.println(" ________________________________________");

    }
    public void find()
    {
        int searchedID;
        System.out.print("input a student ID to search:");
        searchedID=in.nextInt();
       Student s= cont.searchForStudent(searchedID);
       if (s!=null)
       {
           System.out.println(" ________________________________________");
           System.out.println("|        Student Search Form    | ");
           System.out.println(" ________________________________________");
           System.out.println("ID I First Name | Last Name | Registration Year");
           System.out.println(" ________________________________________");
           System.out.println(s.getId()+"\t"+s.getFirstName()+"\t" +
                   s.getLastName()+"\t"+s.getRegistrationYear());
           System.out.println(" ________________________________________");


       }
       else System.out.println("Sorry, There is no Student with "+searchedID+" ID");

    }
    public void edit() {
        int editedID;
        System.out.print("input a student ID to edit:");
        editedID = in.nextInt();
        System.out.println("new ID I new First Name | new Last Name | new Registration Year");
        int id=in.nextInt();
       String fName=in.next();
       String  lName=in.next();
       int year=in.nextInt();
        boolean edited = cont.editStudentData(editedID,id,fName,lName,year);
        if (edited) {
            System.out.println("Student " + editedID + " updated SuccessFully");
        } else System.out.println("Sorry, There is no Student with " + editedID + " ID");

    }
        public void delete()
    {
        int deletedID;
        System.out.print("input a student ID to Delete:");
        deletedID=in.nextInt();
        boolean deleted= cont.DeleteStudent(deletedID);
        if (deleted)
        {
            System.out.println("Student "+deletedID+" Deleted SuccessFully");
        }
        else System.out.println("Sorry, There is no Student with "+deletedID+" ID");


    }
}
