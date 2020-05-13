package com.github.ray8876.tx_call;

import android.content.Context;

import androidx.annotation.NonNull;

import com.github.ray8876.tx_call.util.GenerateTestUserSig;
import com.github.ray8876.tx_call.util.MessageToFlutter;
import com.github.ray8876.tx_call.util.OneForOne;

import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** TxCallPlugin */
public class TxCallPlugin implements FlutterPlugin, MethodCallHandler {

  public static Context context;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "tx_call");
    channel.setMethodCallHandler(new TxCallPlugin());

    MessageToFlutter.initEventChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor());
  }

//  public static void registerWith(Registrar registrar) {
//    final MethodChannel channel = new MethodChannel(registrar.messenger(), "tx_call");
//    channel.setMethodCallHandler(new TxCallPlugin());
//  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {

    switch (call.method){
      case "getPlatformVersion":
        result.success("Android " + android.os.Build.VERSION.RELEASE);
        break;
      case "enterRoom":
        try{
          Map mp = (Map) call.arguments;
          int _userId = (int) mp.get("userId");
          int appID = (int) mp.get("appID");
          String userId = String.valueOf(_userId);
          String userSign = GenerateTestUserSig.genTestUserSig(userId);
          OneForOne.Init(userId,appID,userSign,context);

//          MessageToFlutter.events.success("that amazing!!");
          result.success("enterRoom Done");
        }catch (Exception e){
          result.success(e.toString());
        }
        break;
      case "sendCall":
        try{
          Map mp = (Map) call.arguments;
          int _userId = (int) mp.get("userId");
          String userId = String.valueOf(_userId);
          OneForOne.sendCall(userId);
          result.success("sendCall Done");
        }catch (Exception e){
          result.success(e.toString());
        }
        break;
      case "getCall":
        try{
          Map mp = (Map) call.arguments;
          int data = (int) mp.get("data");
          if (data == 2){
            OneForOne.hangup();
            result.success("挂了");
          }
          else if (data == 1) {
            OneForOne.accept();
            result.success("接了");
          }
          else {
            OneForOne.reject();
            result.success("没接");
          }
        }catch (Exception e){
          result.success(e.toString());
        }
      case "setHandsFree":
        try{
          Map mp = (Map) call.arguments;
          int data = (int) mp.get("data");
          if (data == 1) {
            OneForOne.setHandsFree(true);
            result.success("免提");
          }
          else {
            OneForOne.setHandsFree(false);
            result.success("听筒");
          }
        }catch (Exception e){
          result.success(e.toString());
        }
      case "setMicMute":
        try{
          Map mp = (Map) call.arguments;
          int data = (int) mp.get("data");
          if (data == 1) {
            OneForOne.setMicMute(true);
            result.success("开启静音");
          }
          else {
            OneForOne.setMicMute(false);
            result.success("关闭静音");
          }
        }catch (Exception e){
          result.success(e.toString());
        }

        result.success("不要点了，方法还没写呢");
        break;


      default:
        result.success("方法名写错了？");
        break;
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
  }
}
