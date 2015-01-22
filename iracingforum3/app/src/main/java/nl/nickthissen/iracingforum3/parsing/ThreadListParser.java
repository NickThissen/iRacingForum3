package nl.nickthissen.iracingforum3.parsing;

import nl.nickthissen.iracingforum3.models.forum.*;
import nl.nickthissen.iracingforum3.models.forum.Thread;

/**
 * Created by Nick on 1/16/2015.
 */
public class ThreadListParser extends Parser<ThreadList>
{
    @Override
    protected ParseResult<ThreadList> doParse(String html)
    {
        ThreadList list = new ThreadList();

        list.threads.add(new nl.nickthissen.iracingforum3.models.forum.Thread(0, "Thread 0"));
        list.threads.add(new Thread(1, "Thread 1"));
        list.threads.add(new Thread(2, "Thread 2"));
        list.threads.add(new Thread(3, "Thread 3"));

        return ParseResult.ok(list);
    }
}
