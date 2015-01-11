package nl.nickthissen.iracingforum3.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import org.androidannotations.annotations.AfterViews;

import nl.nickthissen.iracingforum3.MainActivity;
import nl.nickthissen.iracingforum3.adapters.ListAdapter;
import nl.nickthissen.iracingforum3.adapters.PostListAdapter;
import nl.nickthissen.iracingforum3.models.forum.Forum;
import nl.nickthissen.iracingforum3.models.forum.Post;
import nl.nickthissen.iracingforum3.models.forum.PostList;
import nl.nickthissen.iracingforum3.models.forum.Thread;

/**
 * Created by Nick on 1/11/2015.
 */
public class PostListFragment extends DrawerListFragment implements PostListAdapter.ListItemClickListener<Post>
{
    private static final String KEY_THREAD = "KEY_THREAD";

    private MainActivity _activity;
    private PostList _postList;
    private PostListAdapter _adapter;
    private Thread _thread;

    public PostListFragment() {}

    public static PostListFragment create(Thread thread)
    {
        // Create new fragment for thread
        PostListFragment fragment = new PostListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_THREAD, thread);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle bundle)
    {
        _activity = (MainActivity) this.getActivity();
        _thread = (Thread) bundle.getSerializable(KEY_THREAD);

        _postList = new PostList();
    }

    @AfterViews
    void afterViews()
    {
        this.loadPosts();
    }

    private void loadPosts()
    {
        _postList.posts.add(new Post());
        _postList.posts.add(new Post());
        _postList.posts.add(new Post());

        this.loadPostsFinished();
    }

    private void loadPostsFinished()
    {
        this.setItems();
    }

    private void setItems()
    {
        _adapter = new PostListAdapter(_activity, _postList.posts, this);
        this.setListAdapter(_adapter);
    }

    @Override
    public void onItemClicked(Post item, int position)
    {

    }

    @Override
    public void onItemLongClicked(Post item, int position)
    {

    }
}