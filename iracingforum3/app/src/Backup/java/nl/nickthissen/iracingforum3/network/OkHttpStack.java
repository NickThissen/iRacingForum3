package nl.nickthissen.iracingforum3.network;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

/**
 * An {@link com.android.volley.toolbox.HttpStack HttpStack} implementation
 * which uses OkHttp as its transport.
 */
public class OkHttpStack extends HurlStack
{
    private final OkUrlFactory _factory;

    public OkHttpStack() {
        this(new OkHttpClient());
    }

    public OkHttpStack(OkHttpClient client) {
        if (client == null) {
            throw new NullPointerException("Client must not be null.");
        }
        _factory = new OkUrlFactory(client);
    }
}
