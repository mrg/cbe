package cbe.inserting.model.auto;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _Person was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Person extends CayenneDataObject {

    public static final String FIRST_NAME_PROPERTY = "firstName";
    public static final String LAST_NAME_PROPERTY = "lastName";
    public static final String SOCIAL_SECURITY_NUMBER_PROPERTY = "socialSecurityNumber";

    public static final String SSN_PK_COLUMN = "SSN";

    public void setFirstName(String firstName) {
        writeProperty(FIRST_NAME_PROPERTY, firstName);
    }
    public String getFirstName() {
        return (String)readProperty(FIRST_NAME_PROPERTY);
    }

    public void setLastName(String lastName) {
        writeProperty(LAST_NAME_PROPERTY, lastName);
    }
    public String getLastName() {
        return (String)readProperty(LAST_NAME_PROPERTY);
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        writeProperty(SOCIAL_SECURITY_NUMBER_PROPERTY, socialSecurityNumber);
    }
    public String getSocialSecurityNumber() {
        return (String)readProperty(SOCIAL_SECURITY_NUMBER_PROPERTY);
    }

}
