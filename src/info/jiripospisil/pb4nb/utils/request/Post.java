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
package info.jiripospisil.pb4nb.utils.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class Post {

    private static final String QUERY_STRING = "paste_code=%s&paste_name=%s&paste_email=%s&"
            + "paste_subdomain=%s&paste_private=%s&"
            + "paste_expire_date=%s&paste_format=%s";
    private static final String CHARSET = "UTF-8";
    private String text;
    private String name;
    private String email;
    private String subdomain;
    private int privacy;
    private String expiration;
    private String language;

    @Override
    public String toString() {
        return String.format(QUERY_STRING, encode(text),
                encode(emptyStringIfNull(name)),
                encode(emptyStringIfNull(email)),
                encode(emptyStringIfNull(subdomain)),
                encode(privacy),
                encode(emptyStringIfNull(expiration)),
                encode(emptyStringIfNull(language)));
    }

    private String emptyStringIfNull(String str) {
        return str == null ? "" : str;
    }

    private String encode(String str) {
        try {
            return URLEncoder.encode(str, CHARSET);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    private String encode(int num) {
        return encode(num + "");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpiration(String expire) {
        this.expiration = expire;
    }

    public void setLanguage(String format) {
        this.language = format;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy ? 1 : 0;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    public void setText(String text) {
        this.text = text;
    }
}
