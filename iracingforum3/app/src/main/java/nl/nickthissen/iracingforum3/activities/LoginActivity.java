package nl.nickthissen.iracingforum3.activities;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import nl.nickthissen.iracingforum3.R;

/**
 * Created by Nick on 1/21/2015.
 */
@EActivity(R.layout.login_activity)
public class LoginActivity extends Activity
{
    @ViewById
    EditText txtUsername;

    @ViewById
    EditText txtPassword;

    @ViewById
    CheckBox chkRememberLogin;

    @AfterViews
    void afterViews()
    {

    }

    @Click(R.id.btnLogin)
    void loginClicked()
    {

    }
}
