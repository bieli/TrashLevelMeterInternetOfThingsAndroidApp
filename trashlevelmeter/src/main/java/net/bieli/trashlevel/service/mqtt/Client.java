package net.bieli.trashlevel.service.mqtt;

import android.content.Context;
import android.util.Log;

import net.bieli.trashlevel.service.IClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Client implements IClient {
    private static final String TAG = "TrashLevelMeterActivity";
    Context context;
    MqttAndroidClient client;
    MqttActionListener mqttActionListener;

    public Client(Context context) {
        this.context = context;
    }

    @Override
    public void connect(String brokerHost) {
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(context, brokerHost, clientId);

        try {
            MqttConnectOptions options = new MqttConnectOptions();

            IMqttToken token = client.connect(options, context, mqttActionListener);
            Log.d(TAG, "client.getResultCode():" + client.getResultCode());

//            token.setActionCallback(mqttActionListener);

//            Log.d(TAG, "token.getResponse():" + token.getResponse());
//            Log.d(TAG, "token.getTopics():" + token.getTopics());
//            Log.d(TAG, "token.getMessageId():" + token.getMessageId());
//            Log.d(TAG, "token.getGrantedQos():" + token.getGrantedQos());

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean publish(String topicEndpoint, String messageBody) {
        return true;
    }

    @Override
    public String subscribe(String topicEndpoint) {
        return null;
    }

    @Override
    public void disconnect() {
    }
/*
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

    public void disconnect() {
        try {
            IMqttToken disconToken = client.disconnect();
            disconToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // we are now successfully disconnected
                    Log.d(TAG, "DISCONNECT onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // something went wrong, but probably we are disconnected anyway
                    Log.e(TAG, "DISCONNECT onFailure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }*/
}
