package info.jiripospisil.pb4nb.ui;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;
import org.javabuilders.BuildResult;
import org.javabuilders.swing.SwingJavaBuilder;

public class CopyDialog extends JDialog {

    private final BuildResult result;
    private final String url;
    private JTextField urlField;

    @SuppressWarnings("LeakingThisInConstructor")
    public CopyDialog(String url) {
        this.url = url;

        setupComponents();

        this.result = SwingJavaBuilder.build(this);

        requestCopyCloseButtonFocus();
    }

    private void setupComponents() {
        SwingJavaBuilder.getConfig().addResourceBundle(CopyDialog.class.getName());

        urlField = new JTextField(url);
    }

    private void requestCopyCloseButtonFocus() {
        ((JButton) result.get("copyClose")).requestFocusInWindow();
    }

    private void copy() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                new StringSelection(url), null);
    }

    private void close() {
        setVisible(false);
        dispose();
    }
}
