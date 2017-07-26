package max.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import max.db.Query;
import max.db.Row;

public final class Faculty {
    private static final Query FETCH
            = new Query("SELECT id, name FROM faculty ORDER BY id ASC");
    private static final List<Faculty> list = new ArrayList<>(15);
    public static final Faculty NONE = new Faculty(-1, " ");
    
    public final int id;
    public final String name;

    private Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private Faculty(Row row) {
        this.id = row.id();
        this.name = row.name();
    }
    
    public List<Department> departments() {
        return Department.list().stream()
                .filter(d -> d.faculty == this)
                .collect(Collectors.toList());
    }
    
    public static Faculty get(int id) {
        return list().get(id);
    }
    
    public static Faculty get(String name) {
        for (Faculty n : list()) {
            if (n.name.equalsIgnoreCase(name)) {
                return n;
            }
        }
        throw new IllegalArgumentException(name);
    }
    
    public static List<Faculty> list() {
        if (list.isEmpty()) {
            list.add(NONE);
            FETCH.execute().forEach((row) -> {
                list.add(new Faculty(row));
            });
        }
        
        return Collections.unmodifiableList(list);
    }

    @Override
    public String toString() {
        return name;
    }
}
