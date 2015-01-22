package nl.nickthissen.iracingforum3.parsing;

/**
 * Created by Nick on 1/16/2015.
 */
public class ParseResult<T>
{
    private ParseResult(T result)
    {
        _result = result;
        _success = true;
    }

    private T _result;
    public T result()
    {
        return _result;
    }

    private boolean _success;
    public boolean success()
    {
        return _success;
    }

    private String _error;
    private void setError(String error)
    {
        _error = error;
        _success = false;
    }

    public String getError()
    {
        return _error;
    }

    public static <T> ParseResult<T> ok(T object)
    {
        return new ParseResult<T>(object);
    }

    public static <T> ParseResult<T> error(String error)
    {
        ParseResult<T> result = new ParseResult<>(null);
        result.setError(error);
        return result;
    }
}
