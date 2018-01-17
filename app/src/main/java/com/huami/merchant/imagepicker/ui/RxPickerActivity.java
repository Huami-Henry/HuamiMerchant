package com.huami.merchant.imagepicker.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.huami.merchant.R;
import com.huami.merchant.imagepicker.bean.ImageItem;
import com.huami.merchant.imagepicker.ui.fragment.PickerFragment;
import com.huami.merchant.imagepicker.utils.T;

import java.util.ArrayList;

/**
 * @author Smile
 * @time 2017/4/18  下午6:24
 * @desc ${TODD}
 */
public class RxPickerActivity extends AppCompatActivity {
    public static final String MEDIA_RESULT = "MEDIA_RESULT";
    private static final int READ_STORAGE_PERMISSION = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        requestPermission();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(MEDIA_RESULT, new ArrayList<ImageItem>());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @TargetApi(16)
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RxPickerActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE_PERMISSION);
        } else {
            setupFragment();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_STORAGE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupFragment();
            } else {
                T.show(RxPickerActivity.this, getString(R.string.permissions_error));
                finish();
            }
        }
    }

    private void setupFragment() {
        String tag = PickerFragment.class.getSimpleName();
        PickerFragment fragment = (PickerFragment) getFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            fragment = PickerFragment.newInstance();
        }
        getFragmentManager().beginTransaction().replace(R.id.fl_container, fragment, tag).commitAllowingStateLoss();
    }
}
