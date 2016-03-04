package net.bieli.trashlevel;

import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

//import net.bieli.trashlevel.service.mqtt.Client;
import net.bieli.trashlevel.view.GetLevelFromHttpServiceOnClickListener;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView image;
    EditText editText;
    private Drawable drawable;
    private Drawable [] drawables = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        drawables = new Drawable[] {
                ResourcesCompat.getDrawable(getResources(), R.drawable.t0, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t1, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t2, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t3, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t4, null),
                ResourcesCompat.getDrawable(getResources(), R.drawable.t5, null)
        };

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnChangeImage);
        button.requestFocus();
        button.requestFocusFromTouch();
        image = (ImageView) findViewById(R.id.imageView1);
        editText = (EditText) findViewById(R.id.editText);
        View.OnClickListener listener =
                new GetLevelFromHttpServiceOnClickListener(
                        image, editText, drawables, "http://bieli.net", "net.bieli.trashlevel/trash/home/");
        button.setOnClickListener(listener);
        image.setOnClickListener(listener);
//        addListenerOnButton();
    }

//    public void addListenerOnButton() {
//        View.OnClickListener listener = new ChooseNextLevelOnClickListener(getResources(), image, drawables);
//        button.setOnClickListener(listener);
//    }
}
