package net.bieli.trashlevel;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView image;
    private Random random;
    private Drawable drawable;
    // create a Drawables array that stores location of different images
    private Boolean img_random = false;
    private Drawable [] drawables = null;
    private int img_counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

        // Store the location of images inside the array
        drawables = new Drawable[] {
            ResourcesCompat.getDrawable(getResources(), R.drawable.t0, null),
            ResourcesCompat.getDrawable(getResources(), R.drawable.t1, null),
            ResourcesCompat.getDrawable(getResources(), R.drawable.t2, null),
            ResourcesCompat.getDrawable(getResources(), R.drawable.t3, null),
            ResourcesCompat.getDrawable(getResources(), R.drawable.t4, null),
            ResourcesCompat.getDrawable(getResources(), R.drawable.t5, null)
        };

    }

    public void addListenerOnButton() {
        image = (ImageView) findViewById(R.id.imageView1);

        button = (Button) findViewById(R.id.btnChangeImage);
        button.setOnClickListener(new View.OnClickListener() {

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

        });
    }
}
