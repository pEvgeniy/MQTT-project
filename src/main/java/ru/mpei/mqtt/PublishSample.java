package ru.mpei.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PublishSample {

    public void Publish(String data/*, String topicPref*/) {

        String broker = "tcp://srv1.clusterfly.ru:9124";
        String topic = "user_401d05a5"/* + topicPref*/;
        String username = "user_401d05a5";
        String password = "pass_60f8100d";
        String clientid = "iii";
        int qos = 0;

        try {
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setServerURIs(new String[]{broker});
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // connect
            client.connect(options);

            client.subscribe(new String[] {"user_401d05a5/1", "user_401d05a5/2", "user_401d05a5/3"});

            // create message and setup QoS
            MqttMessage message = new MqttMessage(data.getBytes());
            message.setQos(qos);
            // publish message
            client.publish(topic, message);
//            System.out.println("Message published");
//            System.out.println("topic: " + topic);
//            System.out.println("message content: " + data);
            // disconnect
            client.disconnect();
            // close client
            client.close();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
