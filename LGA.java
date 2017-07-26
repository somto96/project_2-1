package max.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import max.db.Query;
import max.db.Row;

public class LGA {
    private static final Query FETCH
            = new Query("SELECT id, name, state FROM lga ORDER BY id ASC");
    private static final List<LGA> list = new ArrayList<>(774);
    public static final LGA NONE = new LGA(-1, State.NONE, " ");
    
    public final int id;
    public final State state;
    public final String name;

    private LGA(int id, State state, String name) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    private LGA(Row row) {
        this.id = row.id();
        this.name = row.name();
        this.state = State.get(row.num("state"));
    }
    
    public static LGA get(int id) {
        return list().get(id);
    }
    
    public static LGA get(String name) {
        for (LGA n : list()) {
            if (n.name.equalsIgnoreCase(name)) {
                return n;
            }
        }
        throw new IllegalArgumentException(name);
    }
    
    public static List<LGA> list() {
        if (list.isEmpty()) {
            list.add(NONE);
            FETCH.execute().forEach((row) -> {
                list.add(new LGA(row));
            });
        }
        
        return Collections.unmodifiableList(list);
    }

    @Override
    public String toString() {
        return name;
    }
}
