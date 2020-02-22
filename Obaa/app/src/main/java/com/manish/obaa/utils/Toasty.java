package com.manish.obaa.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.manish.obaa.R;


public class Toasty {

    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;
    public static final int GRAVITY_CENTER_VERTICAL = Gravity.CENTER_VERTICAL;
    public static final int GRAVITY_CENTER_HORIZONTAL = Gravity.CENTER_HORIZONTAL;
    public static final int GRAVITY_CENTER = Gravity.CENTER;
    public static final int GRAVITY_TOP = Gravity.TOP;
    public static final int GRAVITY_BOTTOM = Gravity.BOTTOM;
    private static final Typeface typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL);

    Toasty() { }

    public static void success(Context context, String message, int duration) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
            RelativeLayout toastRootLayout = view.findViewById(R.id.toastRootLayout);
            ImageView iconTV = view.findViewById(R.id.toastIconIV);
            TextView messageTV = view.findViewById(R.id.toastMessaheTV);
            GradientDrawable layout = (GradientDrawable) toastRootLayout.getBackground();

            layout.setTint(Color.parseColor("#388E3C"));
            iconTV.setImageResource(R.drawable.ic_check_white_48dp);
            messageTV.setText(message);
            messageTV.setTypeface(typeface);
            toast.setDuration(duration);
            toast.setView(view);
            toast.show();
        } else {
            Toast.makeText(context, message, duration).show();
        }


    }

    public static void success(Context context, String message, int duration, int gravity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
            RelativeLayout toastRootLayout = view.findViewById(R.id.toastRootLayout);
            ImageView iconTV = view.findViewById(R.id.toastIconIV);
            TextView messageTV = view.findViewById(R.id.toastMessaheTV);
            GradientDrawable layout = (GradientDrawable) toastRootLayout.getBackground();

            layout.setTint(Color.parseColor("#388E3C"));
            iconTV.setImageResource(R.drawable.ic_check_white_48dp);
            messageTV.setText(message);
            messageTV.setTypeface(typeface);
            toast.setDuration(duration);
            toast.setGravity(gravity, 0, 0);
            toast.setView(view);
            toast.show();
        } else {
            Toast.makeText(context, message, duration).show();
        }


    }

    public static void error(Context context, String message, int duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
            RelativeLayout toastRootLayout = view.findViewById(R.id.toastRootLayout);
            ImageView iconTV = view.findViewById(R.id.toastIconIV);
            TextView messageTV = view.findViewById(R.id.toastMessaheTV);
            GradientDrawable layout = (GradientDrawable) toastRootLayout.getBackground();

            layout.setTint(Color.parseColor("#D50000"));
            iconTV.setImageResource(R.drawable.ic_error_outline_white_48dp);
            messageTV.setText(message);
            messageTV.setTypeface(typeface);
            toast.setDuration(duration);
            toast.setView(view);
            toast.show();
        } else {
            Toast.makeText(context, message, duration).show();
        }

    }

    public static void error(Context context, String message, int duration, int gravity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
            RelativeLayout toastRootLayout = view.findViewById(R.id.toastRootLayout);
            ImageView iconTV = view.findViewById(R.id.toastIconIV);
            TextView messageTV = view.findViewById(R.id.toastMessaheTV);
            GradientDrawable layout = (GradientDrawable) toastRootLayout.getBackground();

            layout.setTint(Color.parseColor("#D50000"));
            iconTV.setImageResource(R.drawable.ic_error_outline_white_48dp);
            messageTV.setText(message);
            messageTV.setTypeface(typeface);
            toast.setDuration(duration);
            toast.setGravity(gravity, 0, 0);
            toast.setView(view);
            toast.show();
        } else {
            Toast.makeText(context, message, duration).show();
        }


    }

    public static void warning(Context context, String message, int duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
            RelativeLayout toastRootLayout = view.findViewById(R.id.toastRootLayout);
            ImageView iconTV = view.findViewById(R.id.toastIconIV);
            TextView messageTV = view.findViewById(R.id.toastMessaheTV);
            GradientDrawable layout = (GradientDrawable) toastRootLayout.getBackground();

            layout.setTint(Color.parseColor("#FFA900"));
            iconTV.setImageResource(R.drawable.ic_error_outline_white_48dp);
            messageTV.setText(message);
            messageTV.setTypeface(typeface);
            toast.setDuration(duration);
            toast.setView(view);
            toast.show();
        } else {
            Toast.makeText(context, message, duration).show();
        }

    }

    public static void warning(Context context, String message, int duration, int gravity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
            RelativeLayout toastRootLayout = view.findViewById(R.id.toastRootLayout);
            ImageView iconTV = view.findViewById(R.id.toastIconIV);
            TextView messageTV = view.findViewById(R.id.toastMessaheTV);
            GradientDrawable layout = (GradientDrawable) toastRootLayout.getBackground();

            layout.setTint(Color.parseColor("#FFA900"));
            iconTV.setImageResource(R.drawable.ic_error_outline_white_48dp);
            messageTV.setText(message);
            messageTV.setTypeface(typeface);
            toast.setDuration(duration);
            toast.setGravity(gravity, 0, 0);
            toast.setView(view);
            toast.show();
        } else {
            Toast.makeText(context, message, duration).show();
        }


    }

    public static void info(Context context, String message, int duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
            RelativeLayout toastRootLayout = view.findViewById(R.id.toastRootLayout);
            ImageView iconTV = view.findViewById(R.id.toastIconIV);
            TextView messageTV = view.findViewById(R.id.toastMessaheTV);
            GradientDrawable layout = (GradientDrawable) toastRootLayout.getBackground();

            layout.setTint(Color.parseColor("#3F51B5"));
            iconTV.setImageResource(R.drawable.ic_info_outline_white_48dp);
            messageTV.setText(message);
            messageTV.setTypeface(typeface);
            toast.setDuration(duration);
            toast.setView(view);
            toast.show();
        } else {
            Toast.makeText(context, message, duration).show();
        }

    }

    public static void info(Context context, String message, int duration, int gravity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
            RelativeLayout toastRootLayout = view.findViewById(R.id.toastRootLayout);
            ImageView iconTV = view.findViewById(R.id.toastIconIV);
            TextView messageTV = view.findViewById(R.id.toastMessaheTV);
            GradientDrawable layout = (GradientDrawable) toastRootLayout.getBackground();

            layout.setTint(Color.parseColor("#3F51B5"));
            iconTV.setImageResource(R.drawable.ic_info_outline_white_48dp);
            messageTV.setText(message);
            messageTV.setTypeface(typeface);
            toast.setDuration(duration);
            toast.setGravity(gravity, 0, 0);
            toast.setView(view);
            toast.show();
        } else {
            Toast.makeText(context, message, duration).show();
        }

    }
}