package co3.greenfoodchallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import co3.greenfoodchallenge.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ProfileActivity";
    ImageView profileUserPhoto;
    TextView profileUserName, profileUserRegion;
    Button logOutButton, editProfileButton;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    // Navi bar Initialize:
    //UI Object
    private TextView txt_home;
    private TextView txt_progress;
    private TextView txt_community;
    private TextView txt_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // find item in xml
        profileUserPhoto = findViewById(R.id.profile_page_user_photo_iv);
        profileUserName = findViewById(R.id.profile_file_user_name_tv);
        profileUserRegion = findViewById(R.id.profile_page_user_region_tv);
        logOutButton = findViewById(R.id.profile_user_log_out_btn);
        editProfileButton = findViewById(R.id.profile_user_edit_btn);

        mFirebaseAuth = FirebaseAuth.getInstance();
//        checkUserLoginMethod();
        updateUserProfile();


        // when user press the sign out button, call the auth signout() to log out and
        // intent to the log in page
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        // TODO: Display User data
        profileUserRegion.setText("UNKNOWN");


    }

    //    @Override
//    protected void onStart() {
//        super.onStart();
//        if (mFirebaseAuth.getCurrentUser() == null){
//            finish();
//            startActivity(new Intent(this, SelectFoodTypeActivity.class));
//        }
//    }

    // check user's login method
    private void checkUserLoginMethod(){
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user.getProviders().get(0).equals("google.com")){
            editProfileButton.setVisibility(View.INVISIBLE);
        }
        Log.i(TAG, "user sign in method = " + user.getProviders());
    }


    // user use.getDisplayName() and user.getPhotoUrl() to get name and photo from the sever
    private void updateUserProfile() {

        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        if (user != null) {
            Log.i(TAG,"get user's info");
            String username = user.getDisplayName();
            Uri photoUrl = user.getPhotoUrl();

            for(UserInfo userInfo: user.getProviderData()){
                if (username != null && userInfo.getDisplayName() != null){
                    username = userInfo.getDisplayName();
                }
                if(photoUrl != null && userInfo.getPhotoUrl() != null) {
                    photoUrl = userInfo.getPhotoUrl();
                }
            }

            profileUserName.setText(username);
            Glide.with(ProfileActivity.this)
                    .load(photoUrl)
                    .apply(new RequestOptions().error(R.drawable.male).dontAnimate())
                    .into(profileUserPhoto);
        }
//        profileUserName.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                Log.i(TAG, "name = "+ user.getDisplayName());
//                Log.i(TAG, "uri = " + user.getPhotoUrl());
//
//                profileUserName.setText(user.getDisplayName());
//
//                Glide.with(ProfileActivity.this)
//                        .load(user.getPhotoUrl().toString())
//                        .apply(new RequestOptions().error(R.drawable.male).dontAnimate())
//                        .into(profileUserPhoto);
//
//            }
//        },1000);
        bindViews();
    }


    // sign out the profile and toast a message
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(ProfileActivity.this, "Log out the account successful", Toast.LENGTH_LONG).show();
        final Intent i = LogInActivity.makeIntent(ProfileActivity.this);
        startActivity(i);
        finish();
    }


    public static Intent makeIntent(Context context){
        return new Intent(context, ProfileActivity.class);
    }

    // Navi bar:
    // Package the initial UI component and the event
    private void bindViews() {
        txt_home = (TextView) findViewById(R.id.txt_home);
        txt_progress = (TextView) findViewById(R.id.txt_progress);
        txt_community = (TextView) findViewById(R.id.txt_community);
        txt_profile = (TextView) findViewById(R.id.txt_profile);

        txt_home.setOnClickListener(this);
        txt_progress.setOnClickListener(this);
        txt_community.setOnClickListener(this);
        txt_profile.setOnClickListener(this);
    }
    // Reset all text
    private void setSelected(){
        txt_home.setSelected(false);
        txt_progress.setSelected(false);
        txt_community.setSelected(false);
        txt_profile.setSelected(false);
    }
//
//    // Hide all Fragment
//    private void hideAllFragment(FragmentTransaction fragmentTransaction){
//        if(fg1 != null)fragmentTransaction.hide(fg1);
//        if(fg2 != null)fragmentTransaction.hide(fg2);
//        if(fg3 != null)fragmentTransaction.hide(fg3);
//        if(fg4 != null)fragmentTransaction.hide(fg4);
//    }

    @Override
    public void onClick(View v)
    {
        // hideAllFragment(transaction);
        switch (v.getId()){
            // Follow the syntax to add the activities
            case R.id.txt_home:
                setSelected();
                txt_home.setSelected(true);
                Intent intent1 = new Intent(ProfileActivity.this, SelectFoodTypeActivity.class );
                startActivity(intent1);
                break;
            case R.id.txt_progress:
                setSelected();
                txt_progress.setSelected(true);
                Intent intent2 = new Intent(ProfileActivity.this, SummaryActivity.class);
                startActivity(intent2);
                break;
            case R.id.txt_community:
                setSelected();
                txt_community.setSelected(true);
                Intent intent3 = new Intent(ProfileActivity.this, Pledge.class);
                startActivity(intent3);
                break;

            // Link the profile "bottom" to ProcileActivity.class, but sth wrong with the class.
            //case R.id.txt_profile:
            // do nothing.
            //break;
        }
    }

}
