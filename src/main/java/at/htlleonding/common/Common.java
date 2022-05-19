package at.htlleonding.common;

public abstract class Common {
    public static void checkArgument(Object obj, String objName) {
        if(obj == null)
            throw new IllegalArgumentException(objName);
    }
}
