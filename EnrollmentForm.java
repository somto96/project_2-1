package max;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class EnrollmentForm extends CaptureForm {
    private final DPFPEnrollment enroller =
            DPFPGlobal.getEnrollmentFactory().createEnrollment();
    public interface Callback { void onEnroll(DPFPTemplate template); }
    final Callback callback;

    EnrollmentForm(Frame owner, Callback callback) {
        super(owner);
        this.callback = callback;
    }

    @Override
    protected void init() {
        super.init();
        setTitle("FINGERPRINT ENROLLMENT");
        updateStatus();
    }

    @Override
    protected void process(DPFPSample sample) {
        super.process(sample);

        try {
            makeReport("The fingerprint feature set was created.");
            DPFPFeatureSet features = extractFeatures(sample, 
                    DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
            enroller.addFeatures(features);
        } catch (IllegalArgumentException|DPFPImageQualityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        updateStatus();

        // Check if template has been created.
        switch (enroller.getTemplateStatus()) {
            case TEMPLATE_STATUS_READY:
                stop();
                Util.success("Fingerprint capture completed");
                if (callback != null)
                    callback.onEnroll(enroller.getTemplate());
                setVisible(false);
                return;

            case TEMPLATE_STATUS_FAILED:
                enroller.clear();
                stop();
                updateStatus();
                Util.error("An error occured."
                        + " Please repeat fingerprint enrollment.");
                start();
                break;
        }
    }

    private void updateStatus() {
        // Show number of samples needed.
        setStatus(String.format("Fingerprint samples needed: %1$s",
                enroller.getFeaturesNeeded()));
    }

}
