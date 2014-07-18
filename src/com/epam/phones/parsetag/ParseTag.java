package com.epam.phones.parsetag;

import com.epam.phones.exception.ParseException;

/**
 * Created by Dmitry on 09.07.2014.
 */
public enum ParseTag {
    NAME("name"),

    SIZE("size"),

    PHONE("phone"),

    PHONES("phones"),

    MANUFACTURER("manuf"),

    MANUFACTURE_DATE("manufDate"),

    OS("os"),

    DISPLAY_TYPE("display"),

    SD_TYPE("SD");

    private String tagName;

    ParseTag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public static ParseTag toEnum(String text) {
        if (text != null) {
            for (ParseTag tag : ParseTag.values()) {
                if (text.equalsIgnoreCase(tag.tagName)) {
                    return tag;
                }
            }
        }
        return null;
    }
}
    /*public final static String NAME_STR = "name";
    public final static String SIZE_STR = "size";
    public final static String PHONE_STR = "phone";
    public final static String PHONES_STR = "phones";
    public final static String MANUF_STR = "manuf";
    public final static String OS_STR = "os";
    public final static String DISPLAY_STR = "display";
    public final static String SD_STR = "SD";
    public final static String MANUF_DATE_STR = "manufDate";*/

