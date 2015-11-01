package easyway2in.com.marathiukhanenew;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsUkhaneActivity extends Activity {

    ViewPager viewPager;
    ViewPagerAdapter PagerAdapter;
    ArrayList<String> ukhaneData;
    TextView counter;

    String currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_ukhane);

        currentCategory = getIntent().getStringExtra("CATEGORY_TITLE");

        headerSettings();

        System.out.println("AMOL PATIL");

JSONArray objJson=DummyResponses.getInstance(this.getApplicationContext()).getResponseFor(currentCategory);
        System.out.println(objJson+"AMOL PATIL");
        ukhaneData = new ArrayList<String>();
        for(int i=0;i<objJson.length();i++)
        {
            String formula  = null;
            try {
                formula = (String) objJson.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            String formula = jb.getString("formule");
//            String url = jb.getString("url");
            ukhaneData.add(formula);
        }

        counter = (TextView)findViewById(R.id.txtCounter);
        viewPager = (ViewPager)findViewById(R.id.myviewpager);
        PagerAdapter = new ViewPagerAdapter(getApplicationContext(),ukhaneData);
        viewPager.setAdapter(PagerAdapter);
        counter.setText(viewPager.getCurrentItem() + 1 + "/" + ukhaneData.size());


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int arg0)
            {
                // TODO Auto-generated method stub

                counter.setText(viewPager.getCurrentItem() + 1 + "/" + ukhaneData.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }
    private void headerSettings() {
        findViewById(R.id.btnBackHeader).setVisibility(View.GONE);
        findViewById(R.id.btnHomeHeader).setVisibility(View.VISIBLE);

        TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
        headerTitle.setText(currentCategory);
    }

    public void gotoNext(View v)
    {
        int currenSlectedtIndex = viewPager.getCurrentItem();
        if(currenSlectedtIndex+1 < ukhaneData.size())
        viewPager.setCurrentItem(currenSlectedtIndex+1);
        counter.setText(viewPager.getCurrentItem()+1+"/"+ukhaneData.size());
    }
    public void gotoPrev(View v)
    {
        int currenSlectedtIndex = viewPager.getCurrentItem();
        if(currenSlectedtIndex-1 >= 0)
            viewPager.setCurrentItem(currenSlectedtIndex-1);
        counter.setText(viewPager.getCurrentItem()+1+"/"+ukhaneData.size());
    }


    public void gotoHome(View v){
        Intent i=new Intent(android.content.Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Marathi Ukhane");
//        i.putExtra(android.content.Intent.EXTRA_TEXT, "Download application from " + "http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());

        String ukhaneStr = ukhaneData.get(viewPager.getCurrentItem());
        i.putExtra(android.content.Intent.EXTRA_TEXT, "\n"+ukhaneStr+ "Download application from " + "http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());

        startActivity(Intent.createChooser(i, "Share via"));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details_ukhane, menu);
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
