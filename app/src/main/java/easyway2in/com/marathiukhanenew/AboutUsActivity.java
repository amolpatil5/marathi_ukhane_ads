package easyway2in.com.marathiukhanenew;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AboutUsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        headerSettings();
    }

    private void headerSettings() {
        findViewById(R.id.btnBackHeader).setVisibility(View.GONE);
        findViewById(R.id.btnHomeHeader).setVisibility(View.VISIBLE);

        TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
        headerTitle.setText(getResources().getString(R.string.title_activity_about_us));

    }


    public void gotoHome(View v){
        Intent i=new Intent(android.content.Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Marathi Ukhane");
        i.putExtra(android.content.Intent.EXTRA_TEXT, "Download application from " + "http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
        startActivity(Intent.createChooser(i, "Share via"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
