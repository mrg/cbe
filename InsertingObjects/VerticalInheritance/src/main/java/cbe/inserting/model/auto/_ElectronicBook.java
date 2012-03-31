package cbe.inserting.model.auto;

import cbe.inserting.constants.ElectronicBookFormat;
import cbe.inserting.model.Book;

/**
 * Class _ElectronicBook was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _ElectronicBook extends Book {

    public static final String FORMAT_PROPERTY = "format";
    public static final String SIZE_PROPERTY = "size";

    public static final String ID_PK_COLUMN = "id";

    public void setFormat(ElectronicBookFormat format) {
        writeProperty("format", format);
    }
    public ElectronicBookFormat getFormat() {
        return (ElectronicBookFormat)readProperty("format");
    }

    public void setSize(Integer size) {
        writeProperty("size", size);
    }
    public Integer getSize() {
        return (Integer)readProperty("size");
    }

}