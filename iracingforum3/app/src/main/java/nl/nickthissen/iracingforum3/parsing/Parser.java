package nl.nickthissen.iracingforum3.parsing;

/**
 * Created by Nick on 1/16/2015.
 */
public abstract class Parser<T>
{
    public Parser()
    {

    }

    public ParseResult<T> parse(String html)
    {
        ParseResult<T> result;
        this.log("Starting to parse: " + html);
        try
        {
            result = this.doParse(html);
        }
        catch (Exception ex)
        {
            this.log("Failed to parse.");
            result = ParseResult.error(ex.getMessage());
        }

        return result;
    }

    protected abstract ParseResult<T> doParse(String html);

    private void log(String message)
    {

    }
}
