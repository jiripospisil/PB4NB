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

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class DocumentInfo {

    private String text;
    private String contentType;

    public DocumentInfo(String text, String contentType) {
        this.text = text;
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public String getText() {
        return text;
    }
}
