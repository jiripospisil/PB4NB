/*
 * PB4NB
 *
 * Copyright (c) 2010 Jiri Pospisil (jiripospisil.info)
 *
 * This software is licensed under the New BSD License. See
 * license.txt in the root directory of this software package
 * for more details.
 *
 */
package info.jiripospisil.pb4nb.ui;

import info.jiripospisil.pb4nb.ui.models.ExpirationComboBoxModel;
import info.jiripospisil.pb4nb.ui.models.LanguageComboBoxModel;
import info.jiripospisil.pb4nb.ui.models.Preferences;
import info.jiripospisil.pb4nb.utils.editor.CurrentDocument;
import info.jiripospisil.pb4nb.utils.editor.DocumentInfo;
import info.jiripospisil.pb4nb.utils.request.PastebinRequest;
import info.jiripospisil.pb4nb.utils.request.Post;
import info.jiripospisil.pb4nb.utils.stores.ExpirationElement;
import info.jiripospisil.pb4nb.utils.stores.LanguageElement;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.text.EditorKit;
import org.javabuilders.BuildResult;
import org.javabuilders.annotations.DoInBackground;
import org.javabuilders.event.BackgroundEvent;
import org.javabuilders.swing.SwingJavaBuilder;
import org.netbeans.api.editor.mimelookup.MimeLookup;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class PostDialog extends JFrame {

    private static final Logger log = Logger.getLogger(PastebinRequest.class.
            getName());
    private final BuildResult result;
    private final PropertyChangeSupport support;
    private JComboBox languages, expiration;
    private JEditorPane editor;
    private Preferences preferences;

    @SuppressWarnings("LeakingThisInConstructor")
    public PostDialog() {
        setupComponents();

        support = new PropertyChangeSupport(this);
        result = SwingJavaBuilder.build(this);
    }

    private void setupComponents() {
        SwingJavaBuilder.getConfig().addResourceBundle(PostDialog.class.getName());

        DocumentInfo docInfo = CurrentDocument.getDocumentInfo();

        LanguageComboBoxModel languageComboBoxModel = new LanguageComboBoxModel();
        languageComboBoxModel.setSelectedItem(docInfo.getContentType());
        languages = new JComboBox(languageComboBoxModel);

        ExpirationComboBoxModel expirationComboBoxModel = new ExpirationComboBoxModel();
        expirationComboBoxModel.setSelectedItem("1M");
        expiration = new JComboBox(expirationComboBoxModel);

        editor = new JEditorPane();
        editor.setEditorKit(
                MimeLookup.getLookup(docInfo.getContentType()).lookup(EditorKit.class));
        editor.setText(docInfo.getText());

        preferences = new Preferences();
        preferences.load();
    }

    @DoInBackground(progressMessage = "label.sending")
    private void post(BackgroundEvent evt) {
        preferences.save();

        try {
            Post post = getPostFromForm();
            String url = new PastebinRequest().execute(post);
            new CopyDialog(url).setVisible(true);
        } catch (Exception ex) {
            showAndLogErrorMessage(ex);
        }
    }

    private void showAndLogErrorMessage(Exception ex) {
        JOptionPane.showMessageDialog(this, "Unable to send the post. See log for details.");
        log.log(Level.SEVERE, ex.toString());
    }

    private Post getPostFromForm() {
        Post post = new Post();

        post.setEmail(preferences.getEmail());
        post.setName(preferences.getNameTitle());
        post.setSubdomain(preferences.getSubdomain());
        post.setText(editor.getText());

        boolean exposure = ((JRadioButton) result.get("exposure_private")).
                isSelected();
        post.setPrivacy(exposure);

        LanguageElement languageElement = (LanguageElement) languages.getModel().
                getSelectedItem();
        post.setLanguage(languageElement.getCode());

        ExpirationElement expirationElement = (ExpirationElement) expiration.
                getModel().
                getSelectedItem();
        post.setExpiration(expirationElement.getCode());

        return post;
    }

    private void close() {
        setVisible(false);
        dispose();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        support.removePropertyChangeListener(propertyName, listener);
    }
}
