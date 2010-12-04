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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.text.EditorKit;
import org.javabuilders.BuildResult;
import org.javabuilders.swing.SwingJavaBuilder;
import org.netbeans.api.editor.mimelookup.MimeLookup;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class PostDialog extends JFrame {

    private final BuildResult result;
    private final PropertyChangeSupport support;
    private final JComboBox languages;
    private final JComboBox expiration;
    private final JEditorPane editor;

    @SuppressWarnings("LeakingThisInConstructor")
    public PostDialog() {
        SwingJavaBuilder.getConfig().addResourceBundle(PostDialog.class.getName());

        this.languages = new JComboBox(new LanguageComboBoxModel());
        this.expiration = new JComboBox(new ExpirationComboBoxModel());

        this.editor = new JEditorPane();
        this.editor.setEditorKit(
                MimeLookup.getLookup("text/x-java").lookup(EditorKit.class));

        this.support = new PropertyChangeSupport(this);
        this.result = SwingJavaBuilder.build(this);
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
