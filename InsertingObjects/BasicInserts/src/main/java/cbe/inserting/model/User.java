package cbe.inserting.model;

import org.apache.commons.codec.digest.DigestUtils;

import cbe.inserting.model.auto._User;

public class User extends _User
{
    public String getFullName()
    {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public void setPassword(String unencryptedPassword)
    {
        // A real password handler would do more than this.  Read:
        // http://www.owasp.org/index.php/Hashing_Java
        super.setPassword(DigestUtils.shaHex(unencryptedPassword));
    }
}
