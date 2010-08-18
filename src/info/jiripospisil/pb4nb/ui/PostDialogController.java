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

import info.jiripospisil.pb4nb.utils.request.Post;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class PostDialogController {

    private final PostDialogModel model;
    private final PostDialogView view;

    public PostDialogController(PostDialogModel model, PostDialogView view) {
        this.model = model;
        this.view = view;

        view.addSubmitButtonListener(new SubmitButtonListener());
    }

    class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!view.isTextAreaEmpty()) {
                view.closeDialog();
                Post post = getFormValues();
                executeRequest(post);
            } else {
                view.showEmptyTextAreaMessage();
            }
        }
    }

    private Post getFormValues() {
        Post post = new Post();
        post.setText(view.getText());
        post.setName(view.getAuthor());
        post.setEmail(view.getEmail());
        post.setSubdomain(view.getSubmain());
        post.setExpire(view.getExpire());
        post.setPrivacy(view.isPrivate());
        post.setFormat(view.getFormat());

        return post;
    }

    private void executeRequest(Post post) {
        model.executeAsyncRequest(post, this);
    }

    void showCopyDialog(final String url) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                CopyDialogView copyView = new CopyDialogView(url);
                CopyDialogModel copyModel = new CopyDialogModel();
                CopyDialogController copyController = new CopyDialogController(copyModel, copyView);

                copyView.setVisible(true);
            }
        });
    }

    void showErrorOccuredAndLogIt(Exception ex) {
        view.showErrorOccuredAndLogIt(ex);
    }
}
