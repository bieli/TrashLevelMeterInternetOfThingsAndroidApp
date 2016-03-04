package net.bieli.trashlevel.service.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class MqttActionListener implements IMqttActionListener {
    private static final String TAG = "MqttActionListener";
    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        // The message was published
//        String msgKey = asyncActionToken.getResponse().getKey();
//        Log.d(TAG, "SUBSCRIBE onSuccess - msgKey: '" + msgKey + "'");
        Log.d(TAG, "Client connected");
        Log.d(TAG, "Topics=" + asyncActionToken.getTopics());
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken,
                          Throwable exception) {
        // The subscription could not be performed, maybe the user was not
        // authorized to subscribe on the specified topic e.g. using wildcards

        Log.e(TAG, "SUBSCRIBE onFailure");
    }
}
