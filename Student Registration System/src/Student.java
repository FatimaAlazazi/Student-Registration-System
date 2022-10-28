public class Student {
    private int id;
    static int counter=0;
    private String firstName;
    private String lastName;
    private int registrationYear;

    public Student(int id, String firstName, String lastName, int registrationYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationYear = registrationYear;

    }

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }
//

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationYear=" + registrationYear +
                "counter="+Student.counter+"}";
    }
    public String print() {
        return  id +
                "\t" + firstName +
                "\t" + lastName +
                "\t" + registrationYear +
                "\n";
    }

    public boolean equals(Object o)
    {
        if (o==null)return false;
        if (this.getClass()!=o.getClass())return false;
        Student s=(Student) o;
        if (s.getId()==getId()
                &&s.getFirstName().equals(getFirstName())
                &&s.getLastName().equals(getLastName())
                &&s.getRegistrationYear()==getRegistrationYear())
            return true;
        else return false;
    }


}
