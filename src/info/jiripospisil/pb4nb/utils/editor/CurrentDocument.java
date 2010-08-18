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
package info.jiripospisil.pb4nb.utils.editor;

import java.util.logging.Logger;

import org.openide.util.Utilities;
import org.openide.cookies.EditorCookie;

import javax.swing.JEditorPane;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class CurrentDocument {

    private static final Logger log = Logger.getLogger(
            CurrentDocument.class.getName());

    public static DocumentInfo getDocumentInfo() {
        EditorCookie editorCookie =
                Utilities.actionsGlobalContext().lookup(EditorCookie.class);
        try {
            for (JEditorPane currentPane : editorCookie.getOpenedPanes()) {
                String text = currentPane.getSelectedText();
                if (text.length() > 0) {
                    return new DocumentInfo(text, currentPane.getContentType());
                }
            }
        } catch (NullPointerException ex) {
            log.info("Could not detect any selected text.");
        }
        return new DocumentInfo("", "text/plain");
    }
}
