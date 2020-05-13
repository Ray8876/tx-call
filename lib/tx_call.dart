import 'dart:async';

import 'package:flutter/services.dart';

class TxCall {
  static const MethodChannel _channel = const MethodChannel('tx_call');


  /// 监听原声的主动消息 请在需要的地方调用以下方法
  //  @override
  //  void initState() {
  //    super.initState();
  //    eventChannel.receiveBroadcastStream().listen(_onEvent, onError: _onError);
  //  }
  //
  //  void _onEvent(Object event) {}
  //  void _onError(Object error) {}
  static const eventChannel = const EventChannel('tx_call_message');


  static Future<String> enterRoom({id, appID, appKey}) async {
    final String result = await _channel.invokeMethod('enterRoom', {
      'userId': id,
      'appID': appID,
      'appKey': appKey,
    });
    return result;
  }

  static Future<String> sendCall({id}) async {
    final String result = await _channel.invokeMethod('sendCall', {
      'userId': id,
    });
    return result;
  }

  /// 接到电话之后 选择
  /// int data : 0 拒绝 1 接听
  static Future<String> getCall({data}) async {
    final String result = await _channel.invokeMethod('getCall', {
      'data': data,
    });
    return result;
  }

  /// 接到电话设置是否免提
  /// int data : 0 否 1 是
  static Future<String> setHandsFree({data}) async {
    final String result = await _channel.invokeMethod('setHandsFree', {
      'data': data,
    });
    return result;
  }

  /// 把对方静音
  /// int data : 0 否 1 是
  static Future<String> setMicMute({data}) async {
    final String result = await _channel.invokeMethod('setMicMute', {
      'data': data,
    });
    return result;
  }

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }


}
