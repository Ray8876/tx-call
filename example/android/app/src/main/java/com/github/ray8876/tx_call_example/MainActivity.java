package com.github.ray8876.tx_call_example;

import androidx.annotation.NonNull;

import com.github.ray8876.tx_call.TxCallPlugin;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);
    TxCallPlugin.context = this;
  }
}
