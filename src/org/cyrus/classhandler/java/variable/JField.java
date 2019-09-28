package org.cyrus.classhandler.java.variable;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.java.classtype.CommonJavaClass;

import java.lang.reflect.Modifier;
import java.util.Optional;

public class JField implements Field {

    protected CommonClass class1;
    protected java.lang.reflect.Field field;

    public JField(CommonClass class1, java.lang.reflect.Field field){
        this.class1 = class1;
        this.field = field;
    }

    @Override
    public Visibility getVisibility() {
        int modifier = this.field.getModifiers();
        if(Modifier.isPrivate(modifier)){
            return Visibility.PRIVATE;
        }else if(Modifier.isProtected(modifier)){
            return Visibility.PROTECTED;
        }else if(Modifier.isPublic(modifier)){
            return Visibility.PUBLIC;
        }
        return Visibility.DEFAULT;
    }

    @Override
    public boolean isStatic() {
        return Modifier.isStatic(this.field.getModifiers());
    }

    @Override
    public String getName() {
        return this.field.getName();
    }

    @Override
    public boolean isFinal() {
        return Modifier.isFinal(this.field.getModifiers());
    }

    @Override
    public CommonClass getAttachedClass() {
        return this.class1;
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(CommonJavaClass.of(this.field.getType()));
    }
}
