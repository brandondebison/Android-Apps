package kamranshahid.singletonimplementation;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView txtWelcome  = (TextView) findViewById(R.id.txtWelcome);

        //Displaying our username using singleton class.
        txtWelcome.setText("Welcome\n" + SingletonSession.Instance().getUsername());
    }
}
