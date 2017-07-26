package max.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import max.db.Query;
import max.db.Row;

public class Department {
    private static final Query FETCH
            = new Query("SELECT id, name, faculty FROM department ORDER BY id ASC");
    private static final List<Department> list = new ArrayList<>(101);
    public static final Department NONE = new Department(-1, Faculty.NONE, " ");
    
    public final int id;
    public final String name;
    public final Faculty faculty;

    private Department(int id, Faculty faculty, String name) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
    }

    private Department(Row row) {
        this.id = row.id();
        this.name = row.name();
        this.faculty = Faculty.get(row.num("faculty"));
    }
    
    public static Department get(int id) {
        return list().get(id);
    }
    
    public static Department get(String name) {
        for (Department n : list()) {
            if (n.name.equalsIgnoreCase(name)) {
                return n;
            }
        }
        throw new IllegalArgumentException(name);
    }
    
    public static List<Department> list() {
        if (list.isEmpty()) {
            list.add(NONE);
            FETCH.execute().forEach((row) -> {
                list.add(new Department(row));
            });
        }
        
        return Collections.unmodifiableList(list);
    }

    @Override
    public String toString() {
        return name;
    }
}
