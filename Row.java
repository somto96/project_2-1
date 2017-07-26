package max.db;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import max.Util;

/**
 * Contains a single row from the result of a successful query.
 * Methods exist to return values as dates, bytes, text, integers,
 * and special types decoded from bytes, such as images, or fingerprints.
 */
public final class Row {
    private final Map<String, Object> map;
    Row() { this.map = new LinkedHashMap<>(); }
    Row(int size) { this.map = new LinkedHashMap<>(size); }
    
    public int id() { return num("id"); }
    public String name() { return text("name"); }
    
    public Date date(String key) { return (Date) map.get(key); }
    public byte[] blob(String key) { return (byte[]) map.get(key); }
    public String text(String key) { return Util.string(map.get(key)); }
    public int num(String key) { return Util.number(map.get(key)); }
    public int columns() { return map.size(); }
        
    public BufferedImage image(String key)  {
        byte[] b = this.blob(key);
        try { return ImageIO.read(new ByteArrayInputStream(b)); }
        catch (IOException ex) { throw new RuntimeException(ex); }
    }
    
    public DPFPTemplate template(String key) {
        return DPFPGlobal.getTemplateFactory()
                .createTemplate(this.blob(key));
    }
    
    public List<String> keys() {
        List<String> list = new ArrayList<>(map.keySet());
        return Collections.unmodifiableList(list);
    }
    
    public List<Object> values() {
        List<Object> list = new ArrayList<>(map.values());
        return Collections.unmodifiableList(list);
    }
    
    public Map<String, Object> map() {
        return Collections.unmodifiableMap(map);
    }
    
    void put(String key, Object o) {
        if (o instanceof Blob) {
            Blob b = (Blob) o;
            try { o = b.getBytes(1, (int) b.length()); }
            catch (SQLException ex) { throw new RuntimeException(ex); }
        }
        map.put(key, o);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
