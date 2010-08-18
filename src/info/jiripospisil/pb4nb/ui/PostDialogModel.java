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
import info.jiripospisil.pb4nb.utils.request.PastebinRequest;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class PostDialogModel {

    public void executeAsyncRequest(final Post post, final PostDialogController callback) {
        RequestProcessor.getDefault().post(new Runnable() {

            @Override
            public void run() {
                try {
                    String url = new PastebinRequest().execute(post);
                    callback.showCopyDialog(url);
                } catch (Exception ex) {
                    callback.showErrorOccuredAndLogIt(ex);
                }
            }
        });
    }
}
