package easyway2in.com.marathiukhanenew;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 10/17/2015.
 */
public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;

    ArrayList<String>titleData;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context,ArrayList<String>titlesData)
    {
        this.context = context;
        this.titleData = titlesData;
    }

    @Override
    public int getCount() {
        return titleData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txTitle;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        txTitle = (TextView) itemView.findViewById(R.id.txtHeading);


        String itemTitle =titleData.get(position);

        // Locate the TextViews in viewpager_item.xmls
        txTitle.setText(itemTitle);

        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

}
