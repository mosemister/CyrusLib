package org.cyrus.classhandler.common.function;

import org.cyrus.classhandler.common.appliable.AnnotationAppliable;
import org.cyrus.classhandler.common.appliable.GenericAppliable;
import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Line;

import java.util.List;

public interface Function <C extends CommonClass> extends Callable.StandardCallable, GenericAppliable, AnnotationAppliable<C> {

    C getAttachedClass();

    interface Writable <T extends CommonClass> extends Function<T> {

        List<Line> getLines();

    }

    interface Namable <C extends CommonClass> extends Function<C> {

        Function.Namable<C> setName(String name);

    }

    interface Visable<C extends CommonClass> extends Function<C> {


        Function.Visable<C> setVisibility(Visibility vis);

    }

}
