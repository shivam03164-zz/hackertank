package com.hackertank.poolme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mohammad.salim on 06/06/15.
 */
public class UserInfoActivity extends Activity {

    private EditText homeAddressText;
    private EditText officeAddressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.user_info_view);


    }
}
