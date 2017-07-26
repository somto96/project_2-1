package max;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Util {
    public static void setTextLength(JTextField text, final int limit) {
        text.setDocument(new PlainDocument() {
            @Override
            public void insertString(
                    int at, String s, AttributeSet a)
                    throws BadLocationException {
                if (s == null) return;
                int len = (getLength() + s.length());
                if (len <= limit) super.insertString(at, s, a);
            }
        });
    }
    
    public static void setImageRes(JLabel label, String name) {
        try {
            InputStream input = Util.class.getResourceAsStream(name);
            BufferedImage img = ImageIO.read(input);
            img = Util.scaleWidth(img, label.getWidth());
            label.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static BufferedImage scaleWidth(BufferedImage image, int maxWidth) {
        if (image.getWidth() <= maxWidth) return image;
        double scale = maxWidth / (double) image.getWidth();
        int w = (int) (scale * image.getWidth());
        int h = (int) (scale * image.getHeight());
        BufferedImage scaled 
                = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = scaled.createGraphics();
        g.drawImage(image, 0, 0, w, h, null);
        g.dispose();
        
        return scaled;
    }
    
    public static void setLookAndFeel() {
        try {
            javax.swing.UIManager.setLookAndFeel
            (UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
    }
    
    public static <T> ComboBoxModel<T> model(List<T> items) {
        return model(items, null);
    }
    
    public static <T> ComboBoxModel<T> model(List<T> items, T empty) {
        @SuppressWarnings("UseOfObsoleteCollectionType")
        java.util.Vector<T> vector = new java.util.Vector<>();
        if (empty != null && items.isEmpty()) {
            vector.add(empty);
        }
        
        vector.addAll(items);
        return new DefaultComboBoxModel<>(vector);
    }
    
    public static <T> T get(JComboBox<T> combo) {
        return (T) combo.getSelectedItem();
    }
    
    public static void redirect(JFrame from, JFrame to) {
        from.setVisible(false);
        to.setVisible(true);
        from.dispose();
    }
    
    public static void error(String why) {
        JOptionPane.showMessageDialog
        (null, why, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void success(String why) {
        JOptionPane.showMessageDialog
        (null, why, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static String lpad(Object o, int len) {
        StringBuilder sb = new StringBuilder(len);
        String s = Objects.toString(o, "NULL");
        
        for (int i = s.length(); i < len; i++) {
            sb.append(' ');
        }
        
        return sb.append(s).toString();
    }
    
    public static String rpad(Object o, int len) {
        StringBuilder sb = new StringBuilder(len);
        String s = Objects.toString(o, "NULL");
        sb.append(s);
        
        for (int i = s.length(); i < len; i++) {
            sb.append(' ');
        }
        
        return sb.toString();
    }
    
    public static String string(Object o) {
        if (o == null) return null;
        return o.toString();
    }
    
    public static int number(Object o) {
        if (o instanceof Number) {
            Number num = (Number) o;
            return num.intValue();
        } else if (o instanceof String) {
            return Integer.parseInt(o.toString());
        } else if (o == null) {
            throw new NullPointerException();
        } else {
            throw new IllegalArgumentException
            (o + " (" + o.getClass() + ") is not a number");
        }
    }
}
