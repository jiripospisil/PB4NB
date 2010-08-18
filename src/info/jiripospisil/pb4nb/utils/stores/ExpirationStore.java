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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class ExpirationStore {

    private final List<ExpirationElement> elements = new ArrayList<ExpirationElement>(5);

    public ExpirationStore() {
        elements.add(new ExpirationElement("N", "Never"));
        elements.add(new ExpirationElement("10M", "10 Minutes"));
        elements.add(new ExpirationElement("1H", "1 Hour"));
        elements.add(new ExpirationElement("1D", "1 Day"));
        elements.add(new ExpirationElement("1M", "1 Month"));
    }

    public ExpirationElement getElementAt(int index) {
        return elements.get(index);
    }

    public ExpirationElement getElementByCode(String code) {
        for (ExpirationElement element : elements) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return getElementByCode("1M");
    }

    public int getSize() {
        return elements.size();
    }
}
