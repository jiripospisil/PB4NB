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
package info.jiripospisil.pb4nb.utils.stores;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class LanguageElement {

    private String[] contentTypes;
    private String code;
    private String name;

    public LanguageElement(String[] contentTypes, String code, String name) {
        this.contentTypes = contentTypes;
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String[] getContentTypes() {
        return contentTypes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
