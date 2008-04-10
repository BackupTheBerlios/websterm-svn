/*
 * ChangeLogTag.java
 * Created: Thu 10 Apr 2008 17:54:29 BST
 */
package org.jaden.changelog;
import com.thoughtworks.qdox.model.DocletTag;

/**
 * TODO: Class description
 * 
 * @author: Rongzhou Shen
 * @version 1.0
 *
 * @qtags.location class
 */
public interface ChangeLogTag extends DocletTag {
    /**
     *
     * @qtags.required
     */
    String getDate();

    /**
     *
     * @qtags.required
     */
    String getMessage();
}
