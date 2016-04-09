package easyway2in.com.marathiukhanenew;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        headerSettings();


        AdView ad=(AdView)findViewById(R.id.adView);

        AdRequest adRequest=new AdRequest.Builder()
               // .addTestDevice("D5D741F489459C7E0CBA6E28CAA1D97F")
                .build();
        ad.loadAd(adRequest);
        //ad.loadAd(adRequest);

        gridView = (GridView) findViewById(R.id.gridView);

        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                if (item.getTitle().equals(getResources().getString(R.string.ABOUT_US_TITLE))) {
                    Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                    //Start details activity
                    startActivity(intent);

                } else {
                    //Create intent
                    Intent intent = new Intent(getApplicationContext(), DetailsUkhaneActivity.class);
                    intent.putExtra("CATEGORY_TITLE", item.getTitle());
//                intent.putExtra("image", item.getImage());

                    //Start details activity
                    startActivity(intent);


                }


            }
        });


    }


    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);

//        UkhaneParser uParser = new UkhaneParser();
//        String[] catNames = uParser.getUkhaneCategories();

        String[] catNames = getResources().getStringArray(R.array.UKHANE_CATEGORIES);

        for (int i = 0; i < imgs.length(); i++)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));

            imageItems.add(new ImageItem(bitmap, catNames[i]));
        }
        return imageItems;
    }

    private void headerSettings() {
        findViewById(R.id.btnBackHeader).setVisibility(View.GONE);
        findViewById(R.id.btnHomeHeader).setVisibility(View.VISIBLE);

        TextView headerTitle =(TextView)findViewById(R.id.txtHeading);
        headerTitle.setText(R.string.app_name);
    }


    public void gotoHome(View v){
        Intent i=new Intent(android.content.Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Marathi Ukhane");
        i.putExtra(android.content.Intent.EXTRA_TEXT, "Download application from " + "http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
        startActivity(Intent.createChooser(i, "Share via"));


    }

    public void addm(View adview )
    {
        AdView ad=(AdView)findViewById(R.id.adView);
        ad.loadAd(new AdRequest.Builder().build());
        InterstitialAd end_ad=new InterstitialAd(this);
        end_ad.setAdUnitId(getResources().getString(Integer.parseInt("ca-app-pub-7882386484143881/2202276450")));
        end_ad.loadAd(new AdRequest.Builder().build());
        if(end_ad.isLoaded())
        {
            end_ad.show();
        }

        ad=(AdView)findViewById(R.id.adView);
       // ad.setVisibility(View.GONE);
        ad.loadAd(new AdRequest.Builder().build());

        final AdView finalAd = ad;
        ad.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                finalAd.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
