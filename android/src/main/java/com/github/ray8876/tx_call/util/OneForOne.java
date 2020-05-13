package com.github.ray8876.tx_call.util;


import android.content.Context;

import com.github.ray8876.tx_call.model.ITRTCAudioCall;
import com.github.ray8876.tx_call.model.TRTCAudioCallImpl;
import com.github.ray8876.tx_call.model.TRTCAudioCallListener;

import java.util.List;
import java.util.Map;

public class OneForOne {
    private static ITRTCAudioCall sCall;
    private static String userId;
    private static int SDKAppID;
    private static String userSign;

    public static void Init(String _userId, int _SDKAppID, String _userSign, Context context) {
        userId = _userId;
        SDKAppID = _SDKAppID;
        userSign = _userSign;

        sCall = TRTCAudioCallImpl.sharedInstance(context);
        sCall.init();
        addListener();
        sCall.login(SDKAppID, userId, userSign, new ITRTCAudioCall.ActionCallBack() {
            @Override
            public void onError(int code, String msg) {
                MessageToFlutter.events.success("进房间失败error code: " + code + "msg: " + msg);
            }

            @Override
            public void onSuccess() {
                MessageToFlutter.events.success("用户" + userId + "进房间成功！");
            }
        });
    }

    public static void sendCall(final String _userId) {
        sCall.call(_userId);
    }

    public static void accept(){
        sCall.accept();
    }

    public static void reject(){
        sCall.reject();
    }

    public static void hangup(){
        sCall.hangup();
    }

    public static void setHandsFree(boolean isHandsFree){
        sCall.setHandsFree(isHandsFree);
    }

    public static void setMicMute(boolean isMute){
        sCall.setMicMute(isMute);
    }

    private static void addListener(){
        sCall.addListener(new TRTCAudioCallListener() {
            @Override
            public void onError(int code, String msg) {
                MessageToFlutter.events.success("语音监听器error code: " + code + "msg: " + msg);
                addListener();
            }

            @Override
            public void onInvited(String sponsor, List<String> userIdList, boolean isFromGroup, int callType) {
                MessageToFlutter.events.success(
                        "接到电话" + "sponsor：" + sponsor + "；userList：" + userIdList.toString() + "；isFromGroup：" + isFromGroup +"；callType：" + callType);
            }

            @Override
            public void onGroupCallInviteeListUpdate(List<String> userIdList) {
                MessageToFlutter.events.success("通话用户列表更新啦啦" + userIdList.toString());
            }

            @Override
            public void onUserEnter(String userId) {
                MessageToFlutter.events.success("通话用户进来啦" + userId);
            }

            @Override
            public void onUserLeave(String userId) {
                MessageToFlutter.events.success("通话用户离开啦" + userId);
            }

            @Override
            public void onReject(String userId) {
                MessageToFlutter.events.success("通话被拒绝啦" + userId);
            }

            @Override
            public void onNoResp(String userId) {
                MessageToFlutter.events.success("通话无应答啦" + userId);
            }

            @Override
            public void onLineBusy(String userId) {
                MessageToFlutter.events.success("通话忙线啦" + userId);
            }

            @Override
            public void onCallingCancel() {
                MessageToFlutter.events.success("通话取消啦");
            }

            @Override
            public void onCallingTimeout() {
                MessageToFlutter.events.success("通话超时啦");
            }

            @Override
            public void onCallEnd() {
                MessageToFlutter.events.success("通话结束啦");
            }

            @Override
            public void onUserAudioAvailable(String userId, boolean isVideoAvailable) {
                MessageToFlutter.events.success("通话语音可用啦" + userId  + "isVideoAvailable： " + isVideoAvailable );
            }

            @Override
            public void onUserVoiceVolume(Map<String, Integer> volumeMap) {

            }
        });
    }
}
