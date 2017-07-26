package max.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Leo Aso
 */
public final class Rows implements Iterable<Row> {
    public static final Rows EMPTY
            = new Rows(Collections.emptyList());
    
    private final List<Row> list;
    Rows() { this.list = new ArrayList<>(); }
    Rows(int size) { this.list = new ArrayList<>(size); }
    private Rows(List<Row> emptyList) { this.list = emptyList; }
    
    public boolean isEmpty() { return list.isEmpty(); }
    public Row row(int i) { return list.get(i); }
    public Row first() { return list.get(0); }
    
    public int rows() { return list.size(); }
    public int columns() { return isEmpty() ? 0 : first().columns(); }
    
    Row addRow() { 
        Row row;
        if (isEmpty()) row = new Row();
        else row = new Row(first().columns());
        list.add(row);
        return row;
    }

    @Override
    public String toString() {
        if (list.isEmpty()) return "{NO RESULTS}";
        StringBuilder sb = new StringBuilder();
        sb.append(first().keys()).append('\n');
        for (Row row : list)
            sb.append(row.values()).append('\n');
        return sb.toString();
    }

    @Override
    public Iterator<Row> iterator() {
        return list.iterator();
    }
}
