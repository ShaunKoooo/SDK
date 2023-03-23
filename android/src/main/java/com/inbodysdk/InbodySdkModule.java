package com.inbodysdk;


import android.Manifest;
import android.app.AlertDialog;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.inbody.inbodysdk.IB_BleManager;

import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = InbodySdkModule.NAME)
public class InbodySdkModule extends ReactContextBaseJavaModule {
  public static final String NAME = "InBodySdk";
  private IB_BleManager _InBodyBLEManager = null;
  private Promise initSDKPromise = null;
  private double height = 130.0;
  private double weight = 33.0;
  private int age = 11;
  private String gender = "M";
  private String DEVICE_NAME = "InBodyH20New";

  public InbodySdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  IB_BleManager.BLECallback bleCallback = new IB_BleManager.BLECallback() {
    @Override
    public void CallbackInitSDK(JSONObject jsonObject) {
      Log.d("jsonObject",jsonObject.toString());
//      initSDKPromise.resolve(JSONObject);
    }

    @Override
    public void CallbackSelectDevice(JSONObject jsonObject) {
      Log.d("bbbbbbb-Select", jsonObject.toString());
    }

    @Override
    public void CallbackRemoveDevice(JSONObject jsonObject) {

    }

    @Override
    public void CallbackConnectDisconnect(JSONObject jsonObject) {
      try {
//        Log.d("aaaa123",jsonObject.getString("IsSuccess").toString() == "1" );
        if (jsonObject.getString("IsSuccess").equals("1")) {
           Log.d("c",jsonObject.toString());
          _InBodyBLEManager.SelectDeviceWithCallback(DEVICE_NAME, true);
        } else {
          Log.d("aaa", "test");
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void CallbackSetSync(JSONObject jsonObject) {

    }

    @Override
    public void CallbackSetMobileNumber(JSONObject jsonObject) {

    }

    @Override
    public void CallbackGetBcaData(JSONObject jsonObject) {

    }

    @Override
    public void CallbackGetHRData(JSONObject jsonObject) {

    }

    @Override
    public void CallbackGetActivityData(JSONObject jsonObject) {

    }

    @Override
    public void CallbackGetSleepData(JSONObject jsonObject) {

    }

    @Override
    public void CallbackStartBandInBodyTest(JSONObject jsonObject) {

    }

    @Override
    public void CallbackStartBandHRTest(JSONObject jsonObject) {

    }

    @Override
    public void CallbackSetProfileSync(JSONObject jsonObject) {

    }

    @Override
    public void CallbackSetWait(JSONObject jsonObject) {

    }

    @Override
    public void CallbackSetBand1Setting(JSONObject jsonObject) {

    }

    @Override
    public void CallbackSetBand2Setting(JSONObject jsonObject) {

    }

    @Override
    public void CallbackSetBandTimeAlarm(JSONObject jsonObject) {

    }

    @Override
    public void CallbackSetEZtraining(JSONObject jsonObject) {

    }

    @Override
    public void CallbackSetInBodyON(JSONObject jsonObject) {
      Log.d("bbbbbb-999", jsonObject.toString());
    }

    @Override
    public void CallbackSetInBodyH20(JSONObject jsonObject) {
      Log.d("bbbbbb-888", jsonObject.toString());
    }

    @Override
    public void CallbackGetPPGHR(JSONObject jsonObject) {

    }

    @Override
    public void CallbackGetEcgRawData(JSONObject jsonObject) {

    }

    @Override
    public void CallbackGetEcgRawDataCnt(JSONObject jsonObject) {

    }

    @Override
    public void CallbackStartEcgPpg(JSONObject jsonObject) {

    }
  };
  private static String[] PERMISSIONS = {
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_CONTACTS,
    Manifest.permission.READ_PHONE_STATE,
    Manifest.permission.RECEIVE_SMS,
    Manifest.permission.BLUETOOTH,
    Manifest.permission.BLUETOOTH_ADMIN,
    Manifest.permission.BLUETOOTH_CONNECT,
    Manifest.permission.BLUETOOTH_SCAN
  };

  @ReactMethod
  public void initSDK(ReadableMap data, Promise promise) {
    ActivityCompat.requestPermissions(getCurrentActivity(), PERMISSIONS, 0x11);
    _InBodyBLEManager = IB_BleManager.getInstance();
    _InBodyBLEManager.SetCallback(bleCallback);
    _InBodyBLEManager.InitSDKWithCallback(
      data.getDouble("height"),
      data.getDouble("weight"),
      data.getInt("age"),
      data.getString("gender"),
      data.getString("deviceName"),
      data.getBoolean("useHealthKit"),
      getReactApplicationContext());
  }

  @ReactMethod
  public void connectDisconnect(String isConnect) {
    try {
      boolean test = Boolean.parseBoolean(isConnect);
      _InBodyBLEManager.ConnectDisconnectWithCallback(test);
      Log.d("bbbb1", String.valueOf(test));
    } catch(Exception e) {
      Log.d("bbbb", e.toString());
    }
  }

  @ReactMethod
  public void getInBodyConnect(
  int language,
  int unit,
  int sound) {
    try {
      Log.d("bbbb-88989", "aaaa");
      _InBodyBLEManager.SetInBodyONWithCallback(language, unit, sound);
    } catch(Exception e) {
      Log.d("bbbb", e.toString());
    }
  }
}

