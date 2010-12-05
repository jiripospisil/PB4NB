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

import info.jiripospisil.pb4nb.ui.PostDialog;
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
                new PostDialog().setVisible(true);
            }
        });
    }
}
