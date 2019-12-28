package com.example.myprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView tv;
    int x;
    EditText name;

    /**
     * @author Itay Elbaz
     * @since 6.7.2003
     * tv is the Text View object
     * x is the count value
     * name is the Edit Text object
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.num);
        name = (EditText) findViewById(R.id.name);

        SharedPreferences settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        name.setText(settings.getString("name", "hello"));
        x = settings.getInt("count", -1);
        if (x == -1) {
             x=0;
             tv.setText("there was a problem...   "+x);
        }
        else{
            tv.setText(""+x);

        }


    }

    /**
     *This method adds 1 to the counting
     */
    public void count(View view) {
        x++;
        tv.setText(""+x);
    }

    /**
     *This method resets the counting and the edit text
     */
    public void reset(View view) {
        x=0;
        tv.setText("0");
        name.setText("");
    }

    /**
     *This method saves the settings and exits the app
     */
    public void exit(View view) {
        SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("name",name.getText().toString());
        editor.putInt("count",x);
        editor.commit();
        finish();

    }

    /**
     * creates the menu.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * when the button "credits" is clicked we move to the credits activity
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if (st.equals("credits")) {
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }

}