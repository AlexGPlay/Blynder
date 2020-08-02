package org.blynder.data.http;

/**
 * 
 * HttpRepository interface does nothing on its own. This interface is just
 * extended from a repository annotated class in order to inject the correct
 * dependency into it. If you have the annotation but not the extension of this
 * class the autowiring won't work, the same occurs if the class is present but
 * not the annotation.
 *
 */
public interface HttpRepository {

}
