package com.example.playinwithinteractivenotifications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ServiceControlActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_service_control);
  }

  public void btnStopServiceOnClick (View view) {
    Intent intent = SimpleAvengerService.getStopIntent(this);
    startService(intent);
  }

}
