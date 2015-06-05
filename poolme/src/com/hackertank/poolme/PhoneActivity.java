package com.hackertank.poolme;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

/**
 * Created by mohammad.salim on 06/06/15.
 */

public class PhoneActivity extends Activity{

    private Button nextButton;
    private EditText phoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.phone_view);

        nextButton = (Button) findViewById(R.id.submit_phone);
        phoneText = (EditText) findViewById(R.id.phone_text);

        nextButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long userId = null;
                String name = null;
                //TODO: call api to get username  /user?phone_number=758857

                if(userId == null) {
                    Intent userInfoActivity = new Intent(PhoneActivity.this, UserInfoActivity.class);
                    userInfoActivity.putExtra("Name", name);
                    startActivity(userInfoActivity);
                }
                else {
                    Intent selectionActivity = new Intent(PhoneActivity.this, SelectionActivity.class);
                    selectionActivity.putExtra("userId", userId);
                    selectionActivity.putExtra("phoneNumber", phoneText.getText().toString());
                    startActivity(selectionActivity);
                }
            }
        }));
    }
}
