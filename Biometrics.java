package max.model;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import max.db.Query;
import max.db.Row;
import max.db.Rows;

public class Biometrics {
    public final int id;
    public final Student student;
    public final BufferedImage picture;
    public final DPFPTemplate template;

    private Biometrics(int id, 
            Student student, 
            BufferedImage picture, 
            DPFPTemplate template) {
        this.id = id;
        this.student = student;
        this.picture = picture;
        this.template = template;
    }
    
    private Biometrics(Row row) {
        this.id = row.id();
        this.student = Student.get(row.num("student"));
        this.picture = row.image("picture");
        this.template = row.template("fingerprint");
    }
    
    public static Biometrics of(Student s) {
        Query query = new Query("SELECT * FROM biometrics WHERE student=?");
        Rows result = query.execute(s.id);
        if (result.isEmpty()) return null;
        return new Biometrics(result.first());
    }
    
    public static Biometrics upload(Student student,
                                    BufferedImage picture, 
                                    DPFPTemplate template) {
        Query query = new Query("INSERT INTO biometrics "
                + "(student, picture, fingerprint) VALUES (?,?,?)");
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try { ImageIO.write(picture, "jpg", baos); }
        catch (IOException ex) { throw new RuntimeException(ex); }

        byte[] bImage = baos.toByteArray();
        byte[] bPrint = template.serialize();
        int id = query.insert(student, bImage, bPrint);
        return new Biometrics(id, student, picture, template);
    }
    
    private static final DPFPVerification verificator
            = DPFPGlobal.getVerificationFactory().createVerification();
    
    private static DPFPFeatureSet extractFeatures
            (DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal
                .getFeatureExtractionFactory()
                .createFeatureExtraction();
        
        try { 
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            throw new RuntimeException(e); 
        }
    }
            
    public DPFPVerificationResult verify(DPFPSample sample) {
        DPFPFeatureSet features = extractFeatures(sample,
                DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        return verificator.verify(features, template);
    }
    
    public DPFPVerificationResult verify(DPFPFeatureSet features) {
        return verificator.verify(features, template);
    }
}
