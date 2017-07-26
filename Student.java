package max.model;

import max.db.Query;
import max.db.Row;
import max.db.Rows;

public class Student {
    public final int id;
    public final String regNumber;
    public final Department department;
    public final int level;
    public final String surname;
    public final String firstName;
    public final String otherNames;
    public final LGA lga;
    public final String phone;
    public final String email;
    public final String address;
    
    public String fullName() {
        return surname + ", " + firstName + ' ' + otherNames;
    }

    private Student(int id, String regNumber, Department department, int level, 
            String surname, String firstName, String otherNames, 
            LGA lga, String phone, String email, String address) {
        this.id = id;
        this.regNumber = regNumber;
        this.department = department;
        this.level = level;
        this.surname = surname;
        this.firstName = firstName;
        this.otherNames = otherNames;
        this.lga = lga;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    
    private Student(Row row) {
        this.id = row.id();
        this.regNumber = row.text("regnumber");
        this.department = Department.get(row.num("department"));
        this.level = row.num("level");
        this.surname = row.text("surname");
        this.firstName = row.text("firstname");
        this.otherNames = row.text("othernames");
        this.lga = LGA.get(row.num("lga"));
        this.phone = row.text("phone");
        this.email = row.text("email");
        this.address = row.text("address");
    }
    
    public static Student get(int id) {
        Query query = new Query
        ("SELECT * FROM student WHERE id=?");
        
        Rows result = query.execute(id);
        if (result.isEmpty()) return null;
        return new Student(result.first());
    }
    
    public static Student get(String regnum) {
        Query query = new Query
        ("SELECT * FROM student WHERE regnumber=?");
        
        Rows result = query.execute(regnum);
        if (result.isEmpty()) return null;
        return new Student(result.first());
    }
    
    public static Student enroll(
            String regNumber, Department department, int level, 
            String surname, String firstName, String otherNames, 
            LGA lga, String phone, String email, String address) {
        Query query = new Query("INSERT INTO student "
                + "(regnumber, department, level,"
                + " surname, firstname, othernames," 
                + " lga, phone, email, address) "
                + " VALUES (?,?,?,?,?,?,?,?,?,?)");
        int id = query.insert(regNumber, department.id, level,
                surname, firstName, otherNames, lga.id,
                phone, email, address);
        return new Student(id, regNumber, department, level,
                surname, firstName, otherNames, lga, phone, email, address);
    }

    @Override
    public String toString() {
        return regNumber + " - " + surname 
                + ", " + firstName + ' ' + otherNames;
    }
}
