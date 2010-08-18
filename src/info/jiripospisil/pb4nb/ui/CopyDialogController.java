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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class CopyDialogController {

    private final CopyDialogModel model;
    private final CopyDialogView view;

    CopyDialogController(CopyDialogModel copyModel, CopyDialogView copyView) {
        model = copyModel;
        view = copyView;

        view.addCopyCloseButtonListener(new CopyCloseButtonListener());
        view.addCloseButtonListener(new CloseButtonListener());
    }

    class CopyCloseButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            model.copyToClipboard(view.getUrlText());
            view.closeDialog();
        }
    }

    class CloseButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.closeDialog();
        }
    }
}
