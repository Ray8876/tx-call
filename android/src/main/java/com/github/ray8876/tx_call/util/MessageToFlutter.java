package com.github.ray8876.tx_call.util;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;


/**
 *  此模块用于 原生主动向Flutter发送消息
 *  例如接收到语音电话 或者异步消息时 可通过此处返回处理结果
 */
public class MessageToFlutter {
    public static EventChannel.EventSink events;

    public static void initEventChannel(BinaryMessenger messenger) {
        new EventChannel(messenger, "tx_call_message").setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, EventChannel.EventSink _events) {
                events = _events;
            }

            @Override
            public void onCancel(Object o) {

            }
        });
    }
}
