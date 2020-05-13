import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:tx_call/tx_call.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String info = 'Unknown';

  @override
  void initState() {
    super.initState();
    TxCall.eventChannel.receiveBroadcastStream().listen(_onEvent, onError: _onError);
  }

  void _onEvent(Object event) {
    setState(() {
      info = event.toString() + '\n' + info;
    });
    TxCall.eventChannel.receiveBroadcastStream().listen(_onEvent, onError: _onError);
  }

  void _onError(Object error) {
    setState(() {
      info = error.toString() + '\n' +info;
    });
  }

  Future<void> entryRoom(int id) async {
    info = await TxCall.enterRoom(
      id: id,
      appID: 1400368795,
      appKey: '31d73ed054d7078dafc479d5c371973f13a7ae51402f2d87b0fd955e5a2e1074',
    ) + '\n' + info;
    setState(() {});
  }

  Future<void> sendCall(int id) async {
    info = await TxCall.sendCall(
      id: id
    ) + '\n' + info;
    setState(() {});
  }

  Future<void> getCall(int data) async {
    info = await TxCall.getCall(
      data: data,
    ) + '\n' + info;
    setState(() {});
  }

  Future<void> setHandsFree(int data) async {
    info = await TxCall.setHandsFree(
      data: data,
    ) + '\n' + info;
    setState(() {});
  }

  Future<void> setMicMute(int data) async {
    info = await TxCall.setMicMute(
      data: data,
    ) + '\n' + info;
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: myListView(),
      ),
    );
  }

  Widget myListView() {
    return ListView(
      children: <Widget>[
        RaisedButton(
          onPressed: () {
            entryRoom(30);
          },
          child: Text('进房间ID：30'),
        ),
        RaisedButton(
          onPressed: () {
            entryRoom(60);
          },
          child: Text('进房间ID：60'),
        ),

        RaisedButton(
          onPressed: () {
            sendCall(60);
          },
          child: Text('打给：60'),
        ),

        RaisedButton(
          onPressed: () {
            sendCall(30);
          },
          child: Text('打给：30'),
        ),
        RaisedButton(
          onPressed: () {
            getCall(1);
          },
          child: Text('接听'),
        ),
        RaisedButton(
          onPressed: () {
            getCall(0);
          },
          child: Text('拒绝'),
        ),
        RaisedButton(
          onPressed: () {
            getCall(2);
          },
          child: Text('挂断'),
        ),
        RaisedButton(
          onPressed: () {
            setHandsFree(0);
          },
          child: Text('听筒'),
        ),
        RaisedButton(
          onPressed: () {
            setHandsFree(1);
          },
          child: Text('免提'),
        ),
        RaisedButton(
          onPressed: () {
            setMicMute(0);
          },
          child: Text('不静音'),
        ),
        RaisedButton(
          onPressed: () {
            setMicMute(1);
          },
          child: Text('静音'),
        ),

        Center(
          child: Text('$info'),
        )
      ],
    );
  }
}
