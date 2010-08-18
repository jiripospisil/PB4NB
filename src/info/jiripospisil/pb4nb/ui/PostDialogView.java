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
import info.jiripospisil.pb4nb.utils.editor.CurrentDocument;
import info.jiripospisil.pb4nb.utils.editor.DocumentInfo;
import info.jiripospisil.pb4nb.utils.stores.ExpirationElement;
import info.jiripospisil.pb4nb.utils.stores.LanguageElement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.EditorKit;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class PostDialogView extends JDialog {

    public PostDialogView() {
        initComponents();
        initWindow();
        setupComponents();
    }

    public void addSubmitButtonListener(ActionListener al) {
        submit.addActionListener(al);
    }

    private void initWindow() {
        setLocationRelativeTo(null);
        setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
    }

    public void closeDialog() {
        saveUserPreferences();
        setVisible(false);
        dispose();
    }

    private void setupComponents() {
        DocumentInfo docInfo = CurrentDocument.getDocumentInfo();

        setEditorKit(docInfo);
        setComponentFocusAndText(docInfo);
        setLanguageComboBoxModel(docInfo);
        setExpirationComboBoxModel();
        registerEscapeKeyShortcut();
        loadUserPreferences();
    }

    private void setComponentFocusAndText(DocumentInfo docInfo) {
        if (docInfo.getText().length() > 0) {
            editorPane.setText(docInfo.getText());
            submit.requestFocusInWindow();
        } else {
            editorPane.requestFocusInWindow();
        }
    }

    private void setEditorKit(DocumentInfo docInfo) {
        editorPane.setEditorKit(
                MimeLookup.getLookup(
                docInfo.getContentType()).lookup(EditorKit.class));
    }

    private void setLanguageComboBoxModel(DocumentInfo docInfo) {
        LanguageComboBoxModel languageModel = new LanguageComboBoxModel();
        languageModel.setSelectedItem(docInfo.getContentType());
        syntaxHighlighting.setModel(languageModel);
    }

    private void setExpirationComboBoxModel() {
        ExpirationComboBoxModel expirationModel = new ExpirationComboBoxModel();
        expirationModel.setSelectedItem("1M");
        postExpiration.setModel(expirationModel);
    }

    private void registerEscapeKeyShortcut() {
        rootPane.registerKeyboardAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                closeDialog();
            }
        }, KeyStroke.getKeyStroke(
                KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void loadUserPreferences() {
        nameTitle.setText(NbPreferences.forModule(PostDialogView.class).get("nameTitle", ""));
        email.setText(NbPreferences.forModule(PostDialogView.class).get("email", ""));
        subdomain.setText(NbPreferences.forModule(PostDialogView.class).get("subdomain", ""));

        ((ExpirationComboBoxModel) postExpiration.getModel()).setSelectedItem(
                NbPreferences.forModule(
                PostDialogView.class).get("postExpiration", "1M"));
    }

    private void saveUserPreferences() {
        NbPreferences.forModule(PostDialogView.class).put("nameTitle", nameTitle.getText());
        NbPreferences.forModule(PostDialogView.class).put("email", email.getText());
        NbPreferences.forModule(PostDialogView.class).put("subdomain", subdomain.getText());
        NbPreferences.forModule(PostDialogView.class).put("postExpiration", getExpire());
    }

    public boolean isTextAreaEmpty() {
        String text = editorPane.getText();
        return text.trim().length() == 0;
    }

    public void showErrorOccuredAndLogIt(Exception ex) {
        Logger.getLogger(PostDialogView.class.getName()).severe(ex.getMessage());
        JOptionPane.showMessageDialog(null,
                NbBundle.getMessage(PostDialogView.class, "PostDialogView.anError"));
    }

    public void showEmptyTextAreaMessage() {
        JOptionPane.showMessageDialog(null,
                NbBundle.getMessage(PostDialogView.class, "PostDialogView.emptyEditorPane"));
    }

    public String getText() {
        String text = editorPane.getText();

        return text.length() > 0 ? text : "";
    }

    public String getAuthor() {
        return nameTitle.getText().trim();
    }

    public String getSubmain() {
        return subdomain.getText().trim();
    }

    public String getEmail() {
        return email.getText().trim();
    }

    public boolean isPrivate() {
        return privateButton.isSelected();
    }

    public String getFormat() {
        LanguageElement element = (LanguageElement) syntaxHighlighting.getModel().
                getSelectedItem();
        return element.getCode();
    }

    public String getExpire() {
        ExpirationElement element = (ExpirationElement) postExpiration.getModel().
                getSelectedItem();
        return element.getCode();
    }

    @SuppressWarnings("unchecked")
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        privacyButtonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nameTitle = new javax.swing.JTextField();
        syntaxHighlighting = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        postExpiration = new javax.swing.JComboBox();
        submit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        editorPane = new javax.swing.JEditorPane();
        publicButton = new javax.swing.JRadioButton();
        privateButton = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        subdomain = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Post a new Pastebin");
        setMinimumSize(new java.awt.Dimension(759, 444));
        setResizable(false);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(PostDialogView.class, "PostDialogView.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11));
        jLabel2.setText(org.openide.util.NbBundle.getMessage(PostDialogView.class, "PostDialogView.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(PostDialogView.class, "PostDialogView.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(PostDialogView.class, "PostDialogView.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(PostDialogView.class, "PostDialogView.jLabel5.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(PostDialogView.class, "PostDialogView.jLabel6.text")); // NOI18N

        submit.setText(org.openide.util.NbBundle.getMessage(PostDialogView.class, "PostDialogView.submit.text")); // NOI18N

        jScrollPane1.setViewportView(editorPane);

        privacyButtonGroup.add(publicButton);
        publicButton.setSelected(true);
        publicButton.setText("Public");

        privacyButtonGroup.add(privateButton);
        privateButton.setText("Private");

        jLabel7.setText(org.openide.util.NbBundle.getMessage(PostDialogView.class, "PostDialogView.jLabel7.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(PostDialogView.class, "PostDialogView.jLabel8.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(submit, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(postExpiration, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(syntaxHighlighting, 0, 160, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(publicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(privateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(subdomain)
                            .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(publicButton)
                    .addComponent(syntaxHighlighting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(privateButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(subdomain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postExpiration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(nameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(submit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane editorPane;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameTitle;
    private javax.swing.JComboBox postExpiration;
    private javax.swing.ButtonGroup privacyButtonGroup;
    private javax.swing.JRadioButton privateButton;
    private javax.swing.JRadioButton publicButton;
    private javax.swing.JTextField subdomain;
    private javax.swing.JButton submit;
    private javax.swing.JComboBox syntaxHighlighting;
    // End of variables declaration//GEN-END:variables
}
