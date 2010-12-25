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
public class LanguageStore {

    private final List<LanguageElement> elements = new ArrayList<LanguageElement>(20);

    public LanguageStore() {
        // new String[] {mime types}, "pastebin.com format", "GUI text")
        elements.add(new LanguageElement(new String[] {"text/plain"}, "text", "None"));
        elements.add(new LanguageElement(new String[] {"text/x-java"}, "java", "Java"));
        elements.add(new LanguageElement(new String[] {"text/x-c++", "text/x-c",
                    "text/x-h"}, "cpp", "C/C++"));
        elements.add(new LanguageElement(new String[] {"text/x-scala"}, "scala", "Scala"));
        elements.add(new LanguageElement(new String[] {"text/x-ruby"}, "ruby", "Ruby"));
        elements.add(new LanguageElement(new String[] {"text/x-python"}, "python", "Python"));
        elements.add(new LanguageElement(new String[] {"text/x-groovy"}, "groovy", "Groovy"));
        elements.add(new LanguageElement(new String[] {"text/x-php5",
                    "text/x-php"}, "php", "PHP"));
        elements.add(new LanguageElement(new String[] {"text/javascript"}, "javascript", "JavaScript"));
        elements.add(new LanguageElement(new String[] {"text/html", "text/xhtml"}, "html4strict", "HTML"));
        elements.add(new LanguageElement(new String[] {"text/x-css"}, "css", "CSS"));
        elements.add(new LanguageElement(new String[] {"text/x-diff"}, "diff", "Diff"));
        elements.add(new LanguageElement(new String[] {"text/x-ada"}, "ada", "Ada"));
        elements.add(new LanguageElement(new String[] {"text/x-perl"}, "perl", "Perl"));
        elements.add(new LanguageElement(new String[] {"text/x-properties"}, "properties", "Properties"));
        elements.add(new LanguageElement(new String[] {"text/tex"}, "latex", "LaTeX"));
        elements.add(new LanguageElement(new String[] {"text/x-sql"}, "sql", "SQL"));
        elements.add(new LanguageElement(new String[] {"text/xml",
                    "text/x-jsf+xml", "text/tomcat5+xml", "text/x-jnlp+xml",
                    "text/x-maven-pom+xml", "text/x-springconfig+xml",
                    "text/x-hibernate-reveng+xml",
                    "text/x-hibernate-mapping+xml", "text/x-hibernate-cfg+xml",
                    "text/x-dbschema+xml", "text/x-ant+xml"}, "xml", "XML"));
    }

    public LanguageElement getElementAt(int index) {
        return elements.get(index);
    }

    public LanguageElement getElementByContentType(String type) {
        for (LanguageElement element : elements) {
            for (String contentType : element.getContentTypes()) {
                if (contentType.equals(type)) {
                    return element;
                }
            }
        }
        return getElementByContentType("text/plain");
    }

    public int getSize() {
        return elements.size();
    }
}
