package org.cyrus.classhandler.custom.variable.field;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;

import java.util.Optional;

public class AttachableCustomField<C extends CommonCustomClass> extends CustomField<C> implements Field.Attachable<C> {

    protected Line<C> line;

    public AttachableCustomField(C attached, String name, CommonClass<? extends Object> clazz){
        this(attached, name, clazz, null);
    }

    public AttachableCustomField(C attached, String name, CommonClass<? extends Object> clazz, Line<C> line) {
        super(attached, name, clazz);
        this.line = line;
    }

    @Override
    public Optional<Line<C>> getAttachedLine() {
        return Optional.ofNullable(this.line);
    }
}
