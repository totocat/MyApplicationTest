package com.example.pubu.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FlashlightActivity extends AppCompatActivity {

    private Camera camera;
    private ImageButton flashlightSwitchImg;
    private boolean isFlashlightOn;
    Camera.Parameters params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);

        flashlightSwitchImg = (ImageButton) findViewById(R.id.flashlightSwitch);

        // check if the camera supports the flash feature
        boolean isCameraFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!isCameraFlash) {
            showNoCamerAlert();
        } else {
            isFlashlightOn = false;
            camera = Camera.open();
            params = camera.getParameters();
        }

        flashlightSwitchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlashlightOn) {
                    setFlashlightOff();
                } else {
                    setFlashlightOn();
                }
            }
        });
    }

    private void showNoCamerAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Error: No Camera Flash!")
                .setMessage("Camera Flash is not avaiable in this device!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); //close this activity
                    }
                });
    }

    private void setFlashlightOff() {
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(params);
        isFlashlightOn = false;
        flashlightSwitchImg.setImageResource(R.mipmap.light_off);
    }

    private void setFlashlightOn() {
        params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        camera.setParameters(params);
        isFlashlightOn = true;
        flashlightSwitchImg.setImageResource(R.mipmap.light_on);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
