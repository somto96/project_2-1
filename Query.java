package max.db;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Query {
    private static final class Failure extends RuntimeException {
        Failure(Throwable cause) { super(cause); }
        Failure() {}
    }
    
    private static final String url = "jdbc:mysql://localhost/studentportal";
    private static final String username = "root";
    private static final String password = "somto000";
    private final String command;

    public Query(String command) {
        this.command = command;
    }
    
    public int insert(Object... args) {
        try (PreparedStatement ps = connect(true, args)) {
            System.out.print("Executing " + command);
            if (args.length == 0) System.out.println();
            else System.out.println(" with " + Arrays.asList(args));
            
            ps.executeUpdate();
            ResultSet rset = ps.getGeneratedKeys();
            rset.next(); 
            return rset.getInt(1);
        } catch (SQLException ex) {
            throw new Failure(ex);
        }
    }
    
    public Rows execute(Object... args) {
        try (PreparedStatement ps = connect(false, args)) {
            System.out.print("Executing " + command);
            if (args.length == 0) System.out.println();
            else System.out.println(" with " + Arrays.asList(args));
            
            if (!ps.execute()) return Rows.EMPTY;
            Rows rows = new Rows();
            ResultSet rset = ps.getResultSet();
            ResultSetMetaData meta = rset.getMetaData();
            
            while (rset.next()) {
                Row row = rows.addRow();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String col = meta.getColumnLabel(i);
                    row.put(col, rset.getObject(i));
                }
            }
            
            return rows;
        } catch (SQLException ex) {
            throw new Failure(ex);
        }
    }
    
    private PreparedStatement 
        connect(boolean getKeys, Object... args) throws SQLException {
        PreparedStatement ps = getKeys 
                ? connect().prepareStatement(command, 
                     Statement.RETURN_GENERATED_KEYS)
                : connect().prepareStatement(command);
        for (int i = 1; i <= args.length; i++) {
            Object o = args[i - 1];
            if (o instanceof byte[]) {
                ps.setBlob(i, new ByteArrayInputStream((byte[]) o));
            } else if (o instanceof char[]) {
                ps.setString(i, new String((char[]) o));
            } else {
                ps.setObject(i, o);
            }
        }
        
        return ps;
    }

    private static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection(url, username, password);
            System.out.println("Connected to DB!");
            return connection;
        } catch (ClassNotFoundException|SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
