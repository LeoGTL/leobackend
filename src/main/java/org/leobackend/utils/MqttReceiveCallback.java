package org.leobackend.utils;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created on 2021-01-03.
 * 接收消息回调
 *
 * @author Leo
 */
public class MqttReceiveCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("断开连接...");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("接收成功topic:" + topic + " | message:" + JSONObject.toJSONString(mqttMessage));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
