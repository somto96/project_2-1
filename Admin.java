package max.model;

import max.db.Query;
import max.db.Row;
import max.db.Rows;

public final class Admin {
    public final int id;
    public final String firstName;
    
    private Admin(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    private Admin(Row row) {
        this.id = row.id();
        this.firstName = row.text("firstname");
    }
    
    private static final Query LOGIN
            = new Query("SELECT id, firstname FROM admin "
                    + "WHERE username=? AND password=?");
    
    public static Admin login(String u, String p) {
        if (u.isEmpty() || p.isEmpty()) 
            throw new IllegalArgumentException();
        
        Rows result = LOGIN.execute(u, p);
        if (result.isEmpty()) return null;
        return new Admin(result.first());
    }
    
    public static Admin DEBUG = new Admin(1, "Leo");
}
