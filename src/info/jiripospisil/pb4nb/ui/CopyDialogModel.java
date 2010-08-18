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

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
class CopyDialogModel {

    public void copyToClipboard(String urlText) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                new StringSelection(urlText), null);
    }
}
