package net.bieli.trashlevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.bieli.trashlevel.view.ChooseNextLevelOnClickListener;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        button = (Button) findViewById(R.id.btnChangeImage);
        image = (ImageView) findViewById(R.id.imageView1);

        View.OnClickListener listener = new ChooseNextLevelOnClickListener(getResources(), image);
        button.setOnClickListener(listener);
    }
}
