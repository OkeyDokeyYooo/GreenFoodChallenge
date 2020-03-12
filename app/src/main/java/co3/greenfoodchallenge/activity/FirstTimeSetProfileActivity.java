package co3.greenfoodchallenge.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import co3.greenfoodchallenge.R;

public class FirstTimeSetProfileActivity extends AppCompatActivity {

    EditText userName;
    Button startBTN;
    private static final String TAG = "SetProfileActivity";
    FirebaseStorage storage;
    StorageReference storageReference;
    Spinner mSpinner;
    String userCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_set_profile);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        userName = findViewById(R.id.first_time_user_name);
        startBTN = findViewById(R.id.first_time_start_btn);
        mSpinner = findViewById(R.id.first_time_user_city);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userCity = parent.getItemAtPosition(position).toString();
                Toast.makeText(FirstTimeSetProfileActivity.this, userCity,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(FirstTimeSetProfileActivity.this,"Please choose your city", Toast.LENGTH_LONG).show();
                startBTN.setVisibility(View.INVISIBLE);
            }
        });


        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });

    }

    private void updateUserProfile(){
        String name = userName.getText().toString();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(FirstTimeSetProfileActivity.this, "Can not leave the area empty!", Toast.LENGTH_LONG).show();
        } else {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (name != null && userCity != null){
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(FirstTimeSetProfileActivity.this, "update scusseful", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                String[] userInfo = new String[5];
                String userPhotoUrl = user.getPhotoUrl().toString();
                userInfo[0] = userName.getText().toString();
                userInfo[1] = user.getDisplayName();
                userInfo[2] = user.getEmail();
                userInfo[3] = userPhotoUrl;
                userInfo[4] = userCity;

                startActivity(new Intent(FirstTimeSetProfileActivity.this,SelectFoodTypeActivity.class));
            }
        }
    }



    public static Intent makeIntent(Context context){
        return new Intent(context, FirstTimeSetProfileActivity.class);
    }
}
