package net.bieli.trashlevel.view;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import net.bieli.trashlevel.service.http.Client;

public class GetLevelFromHttpServiceOnClickListener implements View.OnClickListener {

    ImageView image;
    EditText editText;
    private Drawable drawable;
    private Drawable [] drawables = null;
    String host;
    String path;

    public GetLevelFromHttpServiceOnClickListener(ImageView image, EditText editText, Drawable[] drawables, String host, String path) {
        this.image = image;
        this.editText = editText;
        this.drawables = drawables;
        this.host = host;
        this.path = path;
    }

    @Override
    public void onClick(View arg0) {
            Client client = new Client(new Object());
            client.setLoggerTag("HttpClient");
            client.connect(host);
            client.setToken("TKN");
            client.disconnect();
            String result = client.subscribe(path);
            if (result != null && result.length() > 0) {
            int state = Integer.parseInt(result);
            editText.setText(result + "\n");
            if (state > 0 &&  state < 7) {
                drawable = drawables[state];
                image.setImageDrawable(drawable);
            }
        }
    }
}
