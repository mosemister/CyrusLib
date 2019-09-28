package org.cyrus.classhandler.common.appliable;

import org.cyrus.classhandler.common.classtype.AnnotationClass;

import java.util.List;

public interface AnnotationAppliable<A extends AnnotationAppliable> {

    List<AnnotationClass<? extends AnnotationClass>> getAnnotations();
}
