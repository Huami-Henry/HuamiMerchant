package com.huami.merchant.imagepicker.ui.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.imagepicker.base.AbstractFragment;
import com.huami.merchant.imagepicker.bean.FolderClickEvent;
import com.huami.merchant.imagepicker.bean.ImageFolder;
import com.huami.merchant.imagepicker.bean.ImageItem;
import com.huami.merchant.imagepicker.ui.PreviewActivity;
import com.huami.merchant.imagepicker.ui.adapter.PickerFragmentAdapter;
import com.huami.merchant.imagepicker.ui.fragment.mvp.PickerFragmentContract;
import com.huami.merchant.imagepicker.ui.fragment.mvp.PickerFragmentPresenter;
import com.huami.merchant.imagepicker.utils.CameraHelper;
import com.huami.merchant.imagepicker.utils.DensityUtil;
import com.huami.merchant.imagepicker.utils.PickerConfig;
import com.huami.merchant.imagepicker.utils.RxBus;
import com.huami.merchant.imagepicker.utils.RxPickerManager;
import com.huami.merchant.imagepicker.utils.T;
import com.huami.merchant.imagepicker.widget.DividerGridItemDecoration;
import com.huami.merchant.imagepicker.widget.PopWindowManager;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseToast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pub.devrel.easypermissions.AppSettingsDialog;

import static android.app.Activity.RESULT_OK;

/**
 * @author Smile
 * @time 2017/4/19  上午9:30
 * @desc ${TODD}
 */
public class PickerFragment extends AbstractFragment<PickerFragmentPresenter> implements PickerFragmentContract.View, View.OnClickListener {

    public static final int DEFAULT_SPAN_COUNT = 3;
    public static final int CAMERA_REQUEST = 0x001;
    private static final int CAMERA_PERMISSION = 0x002;

    public static final String MEDIA_RESULT = "MEDIA_RESULT";

    private TextView title;
    private RecyclerView recyclerView;
    private LinearLayout ivSelectPreview;
    private TextView tvSelectOk;
    private RelativeLayout rlBottom;

    private PickerFragmentAdapter adapter;
    private List<ImageFolder> allFolder;

    private PickerConfig config;
    private Disposable folderClicksubscribe;
    private Disposable imageItemsubscribe;

    public static PickerFragment newInstance() {
        return new PickerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_picker;
    }

    @Override
    protected void initView(View view) {
        config = RxPickerManager.getInstance().getConfig();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        title = (TextView) view.findViewById(R.id.title);
        ivSelectPreview = (LinearLayout) view.findViewById(R.id.iv_select_preview);
        ivSelectPreview.setOnClickListener(this);
        tvSelectOk = (TextView) view.findViewById(R.id.iv_select_ok);
        tvSelectOk.setOnClickListener(this);
        rlBottom = (RelativeLayout) view.findViewById(R.id.rl_bottom);
        rlBottom.setVisibility(config.isSingle() ? View.GONE : View.VISIBLE);
        initToolbar(view);
        initRecycler();
        initObservable();
        loadData();
    }

    private void initToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.nav_top_bar);
        final AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.onBackPressed();
            }
        });
    }

    private void initObservable() {
        folderClicksubscribe = RxBus.singleton().toObservable(FolderClickEvent.class).subscribe(new Consumer<FolderClickEvent>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull FolderClickEvent folderClickEvent) throws Exception {
                String folderName = folderClickEvent.getFolder().getName();
                title.setText(folderName);
                refreshData(allFolder.get(folderClickEvent.getPosition()));
            }
        });

        imageItemsubscribe = RxBus.singleton().toObservable(ImageItem.class).subscribe(new Consumer<ImageItem>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull ImageItem imageItem) throws Exception {
                ArrayList<ImageItem> data = new ArrayList<>();
                data.add(imageItem);
                handleResult(data);
            }
        });
    }

    private void loadData() {
        presenter.loadAllImage(BaseApplication.getContext());
    }

    private void refreshData(ImageFolder folder) {
        adapter.setData(folder.getImages());
        adapter.notifyDataSetChanged();
    }

    private void initPopWindow(List<ImageFolder> data) {
        try {
            new PopWindowManager().init(title, data);
        } catch (Exception e) {

        }
    }

    private void initRecycler() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), DEFAULT_SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);

        final DividerGridItemDecoration decoration = new DividerGridItemDecoration(getActivity());
        Drawable divider = decoration.getDivider();
        int imageWidth = DensityUtil.getDeviceWidth(getActivity()) / DEFAULT_SPAN_COUNT + divider.getIntrinsicWidth() * DEFAULT_SPAN_COUNT - 1;

        adapter = new PickerFragmentAdapter(imageWidth);
        adapter.setCameraClickListener(new CameraClickListener());
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                tvSelectOk.setText(getString(R.string.select_confim, adapter.getCheckImage().size(), config.getMaxValue()));
            }
        });

        tvSelectOk.setText(getString(R.string.select_confim, adapter.getCheckImage().size(), config.getMaxValue()));
    }

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String file_name;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            handleCameraResult();
        }
    }
    public File createFile(File folder, String prefix, String suffix) {
        if (!folder.exists() || !folder.isDirectory()) folder.mkdirs();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        String filename = prefix + dateFormat.format(new Date(System.currentTimeMillis())) + suffix;
        return new File(folder, filename);
    }
    private void handleCameraResult(File file) {
        CameraHelper.scanPic(getActivity(), file);
        for (ImageFolder imageFolder : allFolder) {
            imageFolder.setChecked(false);
        }
        ImageFolder allImageFolder = allFolder.get(0);
        allImageFolder.setChecked(true);
        ImageItem item = new ImageItem(0, file.getAbsolutePath(), file.getName(), System.currentTimeMillis());
        allImageFolder.getImages().add(0, item);
        RxBus.singleton().post(new FolderClickEvent(0, allImageFolder));
    }
    private void handleCameraResult() {
        try {
            File file = CameraHelper.getTakeImageFile();
            CameraHelper.scanPic(getActivity(), file);
            for (ImageFolder imageFolder : allFolder) {
                imageFolder.setChecked(false);
            }
            ImageFolder allImageFolder = allFolder.get(0);
            allImageFolder.setChecked(true);
            ImageItem item = new ImageItem(0, file.getAbsolutePath(), file.getName(), System.currentTimeMillis());
            allImageFolder.getImages().add(0, item);
            RxBus.singleton().post(new FolderClickEvent(0, allImageFolder));
        } catch (Exception e) {
            BaseToast.showToast(getActivity(),"照片存储出错,请重新尝试");
        }
    }

    private void handleResult(ArrayList<ImageItem> data) {
        Intent intent = new Intent();
        intent.putExtra(MEDIA_RESULT, data);
        getActivity().setResult(RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void showAllImage(List<ImageFolder> datas) {
        try {
            allFolder = datas;
            adapter.setData(datas.get(0).getImages());
            adapter.notifyDataSetChanged();
            initPopWindow(datas);
        } catch (Exception e) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!folderClicksubscribe.isDisposed()) {
            folderClicksubscribe.dispose();
        }

        if (!imageItemsubscribe.isDisposed()) {
            imageItemsubscribe.dispose();
        }
    }

    @Override
    public void onClick(View v) {
        if (tvSelectOk == v) {
            int minValue = config.getMinValue();
            ArrayList<ImageItem> checkImage = adapter.getCheckImage();

            if (checkImage.size() < minValue) {
                T.show(getActivity(), getString(R.string.min_image, minValue));
                return;
            }

            handleResult(checkImage);
        } else if (ivSelectPreview == v) {
            ArrayList<ImageItem> checkImage = adapter.getCheckImage();
            if (checkImage.isEmpty()) {
                T.show(getActivity(), getString(R.string.select_one_image));
                return;
            }
            PreviewActivity.start(getActivity(), checkImage);
        }
    }

    @TargetApi(23)
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        } else {
            takePictures();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePictures();
            } else {
                new AppSettingsDialog.Builder(this).
                        setTitle("权限提醒").
                        setRationale("请开启拍照权限,否则将无法为您提供服务").
                        setNegativeButton("取消").
                        setPositiveButton("去开启").
                        build().show();
            }
        }
    }
    private void takePictures() {
        CameraHelper.take(PickerFragment.this, CAMERA_REQUEST);
    }

    private class CameraClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermission();
            } else {
                takePictures();
            }
        }
    }
}
