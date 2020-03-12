package co3.greenfoodchallenge.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import co3.greenfoodchallenge.R;

public class SlideAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

//    public int[] list_image = {
//            R.drawable.spring,
//            R.drawable.summer,
//            R.drawable.fall,
//            R.drawable.winter
//    };

    public String[] list_description = {
            "Vancouver aims to become the greenest city in the world by 2020.",
            "However, this city consumes twice as much as a Earth can provided, and food are 20% of them.",
            "As residents of Vancouver, we can fulfill this goal by having a low carbon diet.",
            "Let's do the Green Food Challenge to track our low carbon diet and have a better Vancouver~"
    };



    public SlideAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 4;
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (LinearLayout) o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.slide,container,false);

        // ImageView imageSlide = (ImageView) view.findViewById(R.id.slideImage);
        TextView textDescription = (TextView) view.findViewById(R.id.txtDescription);


        // imageSlide.setImageResource(list_image[position]);
        textDescription.setText(list_description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

}
