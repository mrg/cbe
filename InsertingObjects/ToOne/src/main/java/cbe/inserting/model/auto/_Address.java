package cbe.inserting.model.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;

import cbe.inserting.model.User;

/**
 * Class _Address was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Address extends CayenneDataObject {

    public static final String CITY_PROPERTY = "city";
    public static final String STATE_PROPERTY = "state";
    public static final String STREET_PROPERTY = "street";
    public static final String ZIP_PROPERTY = "zip";
    public static final String USER_PROPERTY = "user";

    public static final String ID_PK_COLUMN = "id";

    public void setCity(String city) {
        writeProperty("city", city);
    }
    public String getCity() {
        return (String)readProperty("city");
    }

    public void setState(String state) {
        writeProperty("state", state);
    }
    public String getState() {
        return (String)readProperty("state");
    }

    public void setStreet(String street) {
        writeProperty("street", street);
    }
    public String getStreet() {
        return (String)readProperty("street");
    }

    public void setZip(String zip) {
        writeProperty("zip", zip);
    }
    public String getZip() {
        return (String)readProperty("zip");
    }

    public void addToUser(User obj) {
        addToManyTarget("user", obj, true);
    }
    public void removeFromUser(User obj) {
        removeToManyTarget("user", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<User> getUser() {
        return (List<User>)readProperty("user");
    }


}
