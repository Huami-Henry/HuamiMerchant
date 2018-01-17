package com.huami.merchant.activity.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huami.merchant.R;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;

public class AuditLastStepActivity extends AppCompatActivity implements TaskViewInter{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_last_step);
    }

    @Override
    public void doSuccess(Object tag, String json) {

    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {

    }
}
