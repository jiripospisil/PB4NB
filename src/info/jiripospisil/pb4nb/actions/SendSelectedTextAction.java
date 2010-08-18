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
package info.jiripospisil.pb4nb.actions;

import info.jiripospisil.pb4nb.ui.PostDialogController;
import info.jiripospisil.pb4nb.ui.PostDialogModel;
import info.jiripospisil.pb4nb.ui.PostDialogView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public final class SendSelectedTextAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                PostDialogModel model = new PostDialogModel();
                PostDialogView view = new PostDialogView();
                PostDialogController controller = new PostDialogController(model, view);

                view.setVisible(true);
            }
        });
    }
}
