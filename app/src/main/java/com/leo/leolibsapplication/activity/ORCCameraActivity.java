package com.leo.leolibsapplication.activity;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wildma.idcardcamera.camera.IDCardCamera;

public class ORCCameraActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent intent = new Intent(this, CameraActivity.class);
//        intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, Environment.getDataDirectory()+"/111.jpg");
//        intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
//        startActivityForResult(intent, 1001);

        IDCardCamera idCardCamera = IDCardCamera.create(this);
        idCardCamera.openCamera(IDCardCamera.TYPE_IDCARD_FRONT);
    }
}
