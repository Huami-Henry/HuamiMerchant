package com.huami.merchant.imagepicker.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huami.merchant.R;
import com.huami.merchant.imagepicker.bean.ImageItem;
import com.huami.merchant.imagepicker.ui.adapter.PreviewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Smile
 * @time 2017/4/20  上午11:37
 * @desc ${TODD}
 */
public class PreviewActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private ViewPager vpPreview;
  private PreviewAdapter vpAdapter;

  private List<ImageItem> data;

  public static void start(Context context, ArrayList<ImageItem> data) {
    Intent intent = new Intent(context, PreviewActivity.class);
    intent.putExtra("preview_list", data);
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preview);
    handleData();
    setupToolbar();
    vpPreview = (ViewPager) findViewById(R.id.vp_preview);
    vpAdapter = new PreviewAdapter(data);
    vpPreview.setAdapter(vpAdapter);
    vpPreview.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override public void onPageSelected(int position) {
        toolbar.setTitle(position + 1 + "/" + data.size());
      }
    });
  }

  private void handleData() {
    data = (List<ImageItem>) getIntent().getSerializableExtra("preview_list");
  }

  private void setupToolbar() {
    toolbar = (Toolbar) findViewById(R.id.nav_top_bar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
//        onBackPressed();
        finish();
      }
    });
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("1/" + data.size());
  }
}