package net.bieli.trashlevel.mqtt;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Client {
    private static final String TAG = "TrashLevelMeterActivity";
    Context context;
    MqttAndroidClient client;

    public Client(Context context) {
        this.context = context;
    }

    public void connect(String brokerHost) {
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(context, brokerHost, clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "CONNECT onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.e(TAG, "CONNECT onFailure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic) {
        int qos = 1;
        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // The message was published
                    String msgKey = asyncActionToken.getResponse().getKey();
                    Log.d(TAG, "SUBSCRIBE onSuccess - msgKey: '" + msgKey + "'");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // The subscription could not be performed, maybe the user was not
                    // authorized to subscribe on the specified topic e.g. using wildcards

                    Log.e(TAG, "SUBSCRIBE onFailure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
