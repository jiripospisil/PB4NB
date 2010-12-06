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
package info.jiripospisil.pb4nb.ui.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.openide.util.NbPreferences;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class Preferences {

    private String nameTitle;
    private String email;
    private String subdomain;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void load() {
        setNameTitle(NbPreferences.forModule(Preferences.class).get("nameTitle", ""));
        setEmail(NbPreferences.forModule(Preferences.class).get("email", ""));
        setSubdomain(NbPreferences.forModule(Preferences.class).get("subdomain", ""));
    }

    public void save() {
        NbPreferences.forModule(Preferences.class).put("nameTitle", this.nameTitle);
        NbPreferences.forModule(Preferences.class).put("email", this.email);
        NbPreferences.forModule(Preferences.class).put("subdomain", this.subdomain);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        support.removePropertyChangeListener(propertyName, listener);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String old = this.email;
        this.email = email;

        support.firePropertyChange("email", old, this.email);
    }

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        String old = this.subdomain;
        this.subdomain = subdomain;

        support.firePropertyChange("subdomain", old, this.subdomain);
    }

    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        String old = this.nameTitle;
        this.nameTitle = nameTitle;

        support.firePropertyChange("nameTitle", old, this.nameTitle);
    }
}
