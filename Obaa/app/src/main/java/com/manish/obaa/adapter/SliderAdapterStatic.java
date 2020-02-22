package com.manish.obaa.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.manish.obaa.R;

public class SliderAdapterStatic extends PagerAdapter {

    private Activity activity;
    private Integer[] imagesArray;
    //private String[] namesArray;

    public SliderAdapterStatic(Activity activity, Integer[] imagesArray/*, String[] namesArray*/){

        this.activity = activity;
        this.imagesArray = imagesArray;
        //this.namesArray = namesArray;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = ((Activity)activity).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.slide, container, false);
        ImageView imageView = viewItem.findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(imagesArray[position]);
//        TextView textView1 = (TextView) viewItem.findViewById(R.id.textview);
//        textView1.setText(namesArray[position]);
        container.addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imagesArray.length;
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