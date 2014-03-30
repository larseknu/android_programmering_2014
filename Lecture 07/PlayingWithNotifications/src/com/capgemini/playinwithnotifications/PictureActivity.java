package com.capgemini.playinwithnotifications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureActivity extends Activity {
  public static final String TITLE_EXTRA = "title extra";
  public static final String IMAGE_TEXT_EXTRA = "image text extra";
  public static final String IMAGE_RESOURCE_ID_EXTRA = "image resource id extra";

  public static final int IMAGE_RESOURCE_ID_NOT_SET = -1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple_picture);

    Intent createIntent = getIntent();
    String title = createIntent.getStringExtra(TITLE_EXTRA);
    String imageText = createIntent.getStringExtra(IMAGE_TEXT_EXTRA);
    int imageResourceId =
        createIntent.getIntExtra(IMAGE_RESOURCE_ID_EXTRA, IMAGE_RESOURCE_ID_NOT_SET);

    ImageView imageView = (ImageView)findViewById(R.id.imageView);
    
    if(title != null)
      setTitle(title);
    if(imageText != null)
    {
    	((TextView)findViewById(R.id.imageTextView)).setText(imageText);
        imageView.setContentDescription(imageText);
    }
    if(imageResourceId != IMAGE_RESOURCE_ID_NOT_SET)
      imageView.setImageResource(imageResourceId);
  }
}
