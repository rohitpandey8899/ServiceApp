package com.manish.obaa.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.manish.obaa.R;

import java.util.List;


public class SliderAdapter extends PagerAdapter {

    private Activity activity;
    private List<String> li;
    //private String[] namesArray;

    public SliderAdapter(Activity activity, List<String> li) {
        this.activity = activity;
        this.li = li;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = activity.getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.slide, container, false);
        ImageView imageView = viewItem.findViewById(R.id.imageView);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(activity)
                .load(li.get(position))
                .into(imageView);

//        imageView.setImageResource(imagesArray[position]);
//        TextView textView1 = (TextView) viewItem.findViewById(R.id.textview);
//        textView1.setText(namesArray[position]);
        container.addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return li.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // TODO Auto-generated method stub
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }
}