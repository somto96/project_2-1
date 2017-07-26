package max.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import max.db.Query;
import max.db.Row;

/**
 *
 * @author Leo Aso
 */
public final class State {
    private static final Query FETCH
            = new Query("SELECT id, name FROM state ORDER BY id ASC");
    private static final List<State> list = new ArrayList<>(37);
    public static final State NONE = new State(-1, " ");
    
    public final int id;
    public final String name;

    private State(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private State(Row row) {
        this.id = row.id();
        this.name = row.name();
    }
    
    public List<LGA> LGAs() {
        return LGA.list().stream()
                .filter(l -> l.state == this)
                .collect(Collectors.toList());
    }
    
    public static State get(int id) {
        return list().get(id);
    }
    
    public static State get(String name) {
        for (State s : list()) {
            if (s.name.equalsIgnoreCase(name)) {
                return s;
            }
        }
        throw new IllegalArgumentException(name);
    }
    
    public static List<State> list() {
        if (list.isEmpty()) {
            list.add(NONE);
            FETCH.execute().forEach((row) -> {
                list.add(new State(row));
            });
        }
        
        return Collections.unmodifiableList(list);
    }

    @Override
    public String toString() {
        return name;
    }
}
