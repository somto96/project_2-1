package max;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.capture.*;
import com.digitalpersona.onetouch.capture.event.*;
import com.digitalpersona.onetouch.processing.*;

public class CaptureForm
        extends JDialog {

    private final DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
    private final JLabel picture = new JLabel();
    private final JTextField prompt = new JTextField();
    private final JTextArea log = new JTextArea();
    private final JTextField status = new JTextField("[status line]");

    public CaptureForm(Frame owner) {
        super(owner, true);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        rootPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        picture.setPreferredSize(new Dimension(240, 280));
        picture.setBorder(BorderFactory.createLoweredBevelBorder());
        prompt.setFont(UIManager.getFont("Panel.font"));
        prompt.setEditable(false);
        prompt.setColumns(40);
        prompt.setMaximumSize(prompt.getPreferredSize());
        prompt.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Prompt:"),
                        BorderFactory.createLoweredBevelBorder()
                ));
        log.setColumns(40);
        log.setEditable(false);
        log.setFont(UIManager.getFont("Panel.font"));
        JScrollPane logpane = new JScrollPane(log);
        logpane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Status:"),
                        BorderFactory.createLoweredBevelBorder()
                ));

        status.setEditable(false);
        status.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        status.setFont(UIManager.getFont("Panel.font"));

        JButton quit = new JButton("Close");
        quit.addActionListener((ActionEvent e) -> {
            setVisible(false);
        });

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.getColor("control"));
        right.add(prompt, BorderLayout.PAGE_START);
        right.add(logpane, BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.getColor("control"));
        center.add(right, BorderLayout.CENTER);
        center.add(picture, BorderLayout.LINE_START);
        center.add(status, BorderLayout.PAGE_END);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottom.setBackground(Color.getColor("control"));
        bottom.add(quit);

        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                init();
                start();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                stop();
            }

        });

        pack();
        setLocationRelativeTo(null);
    }

    private Runnable reporter(final String s) {
        return () -> {
            makeReport(s);
        };
    }

    protected void init() {
        capturer.addDataListener((final DPFPDataEvent e) -> {
            java.awt.EventQueue.invokeLater(() -> {
                makeReport("The fingerprint sample was captured.");
                setPrompt("Scan the same fingerprint again.");
                process(e.getSample());
            });
        });

        capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                java.awt.EventQueue.invokeLater(() -> {
                    makeReport("The fingerprint reader was connected.");
                    JOptionPane.showMessageDialog(null, "The fingerprint reader was connected.");
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(reporter("The fingerprint reader was disconnected."));
            }
        });
        capturer.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(reporter("The fingerprint reader was touched."));
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(reporter("The finger was removed from the fingerprint reader."));
            }
        });
        capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
            @Override
            public void onImageQuality(final DPFPImageQualityEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD)) {
                        makeReport("The quality of the fingerprint sample is good.");
                    } else {
                        makeReport("The quality of the fingerprint sample is poor.");
                    }
                });
            }
        });
    }

    protected void process(DPFPSample sample) {
		// Draw fingerprint sample image.

        drawPicture(convertSampleToBitmap(sample));
    }

    public DPFPSample process1(DPFPSample sample) {
        drawPicture(convertSampleToBitmap1(sample));
        return sample;
    }

    protected void start() {
        setPrompt("Scan your fingerprint using the fingerprint reader");
        try {
            capturer.startCapture();
        } catch(RuntimeException ex) {
            Util.error("Capture failed: " + ex.getMessage());
            ex.printStackTrace(System.err);
            setVisible(false);
        }
    }

    protected void stop() {
        capturer.stopCapture();
    }

    public void setStatus(String string) { status.setText(string); }
    public void setPrompt(String string) { prompt.setText(string); }
    public void makeReport(String string) { log.append(string + "\n"); }

    public void drawPicture(Image image) {
        picture.setIcon(new ImageIcon(
                image.getScaledInstance(
                        picture.getWidth(), 
                        picture.getHeight(), 
                        Image.SCALE_DEFAULT)));
    }

    protected Image convertSampleToBitmap(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    protected DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    public Image convertSampleToBitmap1(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

}
