package org.cyrus.classhandler.common.appliable;

import org.cyrus.classhandler.common.classtype.AnnotationClass;

import java.util.List;

/**
 * An interface that is designed to extend onto anything that can have
 * an annotation (example - @Override)
 * @param <A>
 */
public interface AnnotationApplicable<A extends AnnotationApplicable> {

    /**
     * Gets all annotations applied directly
     * @return All annotations applied directly
     */
    List<AnnotationClass<? extends AnnotationClass>> getAnnotations();
}
