package cbe.inserting;


public class Listeners
{
    public void onPrePersist(Object object)
    {
        System.out.println("About to save: " + object);
    }

    public void onPostPersist(Object object)
    {
        System.out.println("Just saved: " + object);
    }
}
