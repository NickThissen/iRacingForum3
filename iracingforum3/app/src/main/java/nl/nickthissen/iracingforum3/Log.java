package nl.nickthissen.iracingforum3;

/**
 * Created by Nick on 1/19/2015.
 */
public class Log
{
    private static final String TAG = "iRF3";

    public static void info(String msg)
    {
        android.util.Log.i(TAG, msg);
    }

    public static void error(String error)
    {
        android.util.Log.e(TAG, error);
    }
}
