package controllers;

/**
 * Created by Work on 18.04.2017.
 */
public class CFactory {
    private static CFactory instance = new CFactory();

    public CFactory() {
    }

    public static CFactory getInstance() {
        return CFactory.instance;
    }
}
