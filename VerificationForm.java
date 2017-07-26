package max;

import com.digitalpersona.onetouch.*;
import java.awt.Frame;
import max.model.Biometrics;

public class VerificationForm extends CaptureForm {
    public interface Callback { void onVerify(Biometrics bio); }
    final Callback callback;
    final Biometrics biometrics;

    public VerificationForm(Frame owner,
            Biometrics biometrics,
            Callback callback) {
        super(owner);
        this.callback = callback;
        this.biometrics = biometrics;
    }
   
    @Override
    protected void init() {
        super.init();
        setTitle("Fingerprint Verification");
    }

    @Override
    protected void process(DPFPSample sample) {
        super.process(sample);
        DPFPFeatureSet features = extractFeatures(sample,
                DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        if (features != null) {
            makeReport("An error occured. Please try again");
            Util.error("An error occured. Please try again");
        } else if (biometrics.verify(sample).isVerified()) {
            makeReport("Fingerprint verified");
            Util.success("Fingerprint verified for " 
                    + biometrics.student.regNumber);
            if (callback != null) 
                callback.onVerify(biometrics);
            setVisible(false);
        } else {
            makeReport("Fingerprint does not match "
                    + biometrics.student.regNumber);
            Util.error("Fingerprint does not match "
                    + biometrics.student.regNumber);
        }   
    }
}
