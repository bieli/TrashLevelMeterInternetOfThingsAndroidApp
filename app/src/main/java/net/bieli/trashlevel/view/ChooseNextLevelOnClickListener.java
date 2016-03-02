package net.bieli.trashlevel.view;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ImageView;

import net.bieli.trashlevel.R;

import java.util.Random;

public class ChooseNextLevelOnClickListener implements View.OnClickListener {
    ImageView image;
    private Random random;
    private Drawable drawable;
    // create a Drawables array that stores location of different images
    private Boolean img_random = false;
    private int img_counter = 0;
    private Resources resources;
    // Store the location of images inside the array
    private Drawable [] drawables = null;

    public ChooseNextLevelOnClickListener(Resources resources, ImageView image) {
        this.resources = resources;
        this.image = image;
        drawables = new Drawable[] {
                ResourcesCompat.getDrawable(getResources(), R.drawable.t0, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t1, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t2, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t3, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t4, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t5, null)
        };
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void onClick(View arg0) {
        image.setImageResource(R.mipmap.ic_launcher);

        int img_number = 0;
        if (img_random) {
            random = new Random();
            img_number = random.nextInt(drawables.length);
        } else {
            img_number = img_counter;
            if (img_counter >= 5) {
                img_counter = 0;
            } else {
                img_counter++;
            }
        }
        drawable = drawables[img_number];
        image.setImageDrawable(drawable); // set the image to the ImageView
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
