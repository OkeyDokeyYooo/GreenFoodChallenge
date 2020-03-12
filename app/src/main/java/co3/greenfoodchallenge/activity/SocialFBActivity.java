package co3.greenfoodchallenge.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import co3.greenfoodchallenge.R;

public class SocialFBActivity extends AppCompatActivity implements View.OnClickListener {

    Button shareLink;
    Button sharePhoto;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    SharePhoto photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_fb);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_social_fb);

        //initialize button
        shareLink = (Button) findViewById(R.id.shareLink);
        sharePhoto = (Button)findViewById(R.id.sharePhoto);
//        Button shareButton = (Button) findViewById(R.id.share_button);


        //initialize FB
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);


        shareLink.setOnClickListener(this);
        sharePhoto.setOnClickListener(this);
//        shareButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shareLink:
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("Let's start Green Food Challenge!!")
                        .setContentUrl(Uri.parse("http://www.co3.greenfoodchallenge.com/greenfoodchallenge"))
                        //need change url address
                        .setShareHashtag(new ShareHashtag.Builder()
                                .setHashtag("#GreenFoodChallenge")
                                .build())
                        .build();
                if (shareDialog.canShow(ShareLinkContent.class)) {
                    shareDialog.show(linkContent);
                }
                break;

            case R.id.sharePhoto:
                Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.load_9);

                SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .build();

                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .setShareHashtag(new ShareHashtag.Builder()
                                .setHashtag("#GreenFoodChallenge")
                                .build())
                        .build();

                if (ShareDialog.canShow(SharePhotoContent.class)) {
                    shareDialog.show(content);
                }

                break;
//            case R.id.share_button:
//                Intent shareIntent = new PlusShare.Builder(this)
//                        .setType("text/plain")
//                        .setText("Welcome to the Google+ platform.")
//                        .setContentUrl(Uri.parse("https://developers.google.com/+/"))
//                        .getIntent();
//
//                startActivityForResult(shareIntent, 0);
//                finish();
//                break;
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
