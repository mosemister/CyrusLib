package org.cyrus.classhandler.common;

public enum Visibility {

    PUBLIC,
    PROTECTED,
    PRIVATE,
    DEFAULT {

        @Override
        public String getJavaLine(){
            return "";
        }
    };

    public String getJavaLine(){
        return name().toLowerCase() + " ";
    }


}
