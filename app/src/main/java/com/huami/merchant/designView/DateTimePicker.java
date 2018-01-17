package com.huami.merchant.designView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.listener.OnTimePickListener;
import com.huami.merchant.mvpbase.BaseToast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Henry on 2017/9/29.
 */
public class DateTimePicker implements View.OnClickListener{
    private Context context;//上下文对象
    private View view;//主viwe
    private EditText year_show,month_show,day_show,hour_show,minus_show, second_show;
    private Calendar c = Calendar.getInstance();//首先要获取日历对象
    private LayoutInflater inflate;
    private AlertDialog dialog;//弹出框
    private boolean canceledOnTouchOutside;//点击外部是否可以取消
    private boolean cancelable;//点击是否可以取消
    private OnTimePickListener listener;//获取时间回调

    private DateTimePicker(Builder builder) {
        this.canceledOnTouchOutside = builder.canceledOnTouchOutside;
        this.cancelable = builder.cancelable;
        this.listener = builder.listener;
        this.context = builder.context;
        inflate = LayoutInflater.from(context);
        view = inflate.inflate(R.layout.dialog_datetime_picker, null);
    }
    private void setDialog(){
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当日期
        String mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));//时
        String mMinute = String.valueOf(c.get(Calendar.MINUTE));//分
        String mSecond = String.valueOf(c.get(Calendar.SECOND));//秒
        dialog = new AlertDialog.Builder(context, R.style.color_dialog).create();
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.setCancelable(cancelable);
        dialog.setView(view);
        dialog.show();
        dialog.getWindow().setContentView(R.layout.dialog_datetime_picker);
        final TextView dialog_commit = (TextView) dialog.findViewById(R.id.dialog_commit);
        final TextView dialog_dismiss = (TextView) dialog.findViewById(R.id.dialog_dismiss);
        setTimePickClick(dialog);
        setTimerPickEdit(mYear, mMonth, mDay, mHour, mMinute, mSecond, dialog);
        dialog_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String year = year_show.getText().toString();
                String month = month_show.getText().toString();
                String day =day_show.getText().toString();
                String hour = hour_show.getText().toString();
                String minute = minus_show.getText().toString();
                String second =second_show.getText().toString();
                if (TextUtils.isEmpty(year)) {
                    BaseToast.showToast(context,"年份不能为空");
                    return;
                }
                if (TextUtils.isEmpty(month)) {
                    BaseToast.showToast(context,"月份不能为空");
                    return;
                }
                if (TextUtils.isEmpty(day)) {
                    BaseToast.showToast(context,"天数不能为空");
                    return;
                }
                if (TextUtils.isEmpty(hour)||TextUtils.isEmpty(minute)||TextUtils.isEmpty(second)) {
                    BaseToast.showToast(context,"时间不能为空");
                    return;
                }
                if (month.length() == 1) {
                    month = "0" + month;
                }
                if (day.length() == 1) {
                    day = "0" + day;
                }
                if (hour.length() == 1) {
                    hour = "0" + hour;
                }
                if (minute.length() == 1) {
                    minute = "0" + minute;
                }
                if (second.length() == 1) {
                    second = "0" + second;
                }
                String time = year + "年" + month + "月" + day + "日 " + hour + ":" + minute + ":" + second;
                listener.timePick(time);
                dialog.dismiss();

            }
        });
        dialog_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }
    public void show(){
        setDialog();
    }
    /**
     * 设置点击事件
     * @param dialog
     */
    private void setTimePickClick(AlertDialog dialog) {
        List<Integer> ids = new ArrayList<>();
        ids.add(R.id.year_plus);
        ids.add(R.id.year_minus);
        ids.add(R.id.month_minus);
        ids.add(R.id.month_plus);
        ids.add(R.id.day_plus);
        ids.add(R.id.day_minus);
        ids.add(R.id.hour_plus);
        ids.add(R.id.hour_minus);
        ids.add(R.id.minus_plus);
        ids.add(R.id.minus_minus);
        ids.add(R.id.second_plus);
        ids.add(R.id.second_minus);
        setViewOnClick(ids, dialog);
    }
    /**
     * 给dialog的View设置点击事件
     * @param view_id
     * @param dialog
     */
    private void setViewOnClick(List<Integer> view_id, Dialog dialog){
        for (int id : view_id) {
            View view = getView(id, dialog);
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.year_plus:
                int year_p = Integer.parseInt(year_show.getText().toString());
                year_p++;
                year_show.setText(""+year_p);
                break;
            case R.id.year_minus:
                int year_m = Integer.parseInt(year_show.getText().toString());
                year_m--;
                year_show.setText(""+year_m);
                break;
            case R.id.month_plus:
                int month_p = Integer.parseInt(month_show.getText().toString());
                month_p++;
                if (month_p > 12) {
                    month_p = 1;
                }
                month_show.setText(""+month_p);
                break;
            case R.id.month_minus:
                int month_m = Integer.parseInt(month_show.getText().toString());
                month_m--;
                if (month_m < 1) {
                    month_m = 12;
                }
                month_show.setText(""+month_m);
                break;
            case R.id.day_plus:
                int day_p = Integer.parseInt(day_show.getText().toString());
                day_p++;
                int year = Integer.parseInt(year_show.getText().toString());
                boolean is_run=((year%4==0&&year%100!=0)||year%400==0)?true:false;
                int  month = Integer.parseInt(month_show.getText().toString());
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (day_p >31) {
                        day_p = 1;
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day_p > 30) {
                        day_p = 1;
                    }
                } else {
                    if (is_run) {//闰年
                        if (day_p >29) {
                            day_p = 1;
                        }
                    } else {
                        if (day_p >28) {
                            day_p = 1;
                        }
                    }
                }
                day_show.setText(""+day_p);
                break;
            case R.id.day_minus:
                int day_m = Integer.parseInt(day_show.getText().toString());
                day_m--;
                if (day_m < 1) {
                    day_m = 1;
                }
                day_show.setText(""+day_m);
                break;
            case R.id.hour_plus:
                int hour_p = Integer.parseInt(hour_show.getText().toString());
                hour_p++;
                if (hour_p > 23) {
                    hour_p = 0;
                }
                hour_show.setText(""+hour_p);
                break;
            case R.id.hour_minus:
                int hour_m = Integer.parseInt(hour_show.getText().toString());
                hour_m--;
                if (hour_m < 0) {
                    hour_m = 23;
                }
                hour_show.setText(""+hour_m);
                break;
            case R.id.minus_plus:
                int minus_p = Integer.parseInt(minus_show.getText().toString());
                minus_p++;
                if (minus_p > 59) {
                    minus_p = 0;
                }
                minus_show.setText(""+minus_p);
                break;
            case R.id.minus_minus:
                int minus_m = Integer.parseInt(minus_show.getText().toString());
                minus_m--;
                if (minus_m < 0) {
                    minus_m = 59;
                }
                minus_show.setText(""+minus_m);
                break;
            case R.id.second_plus:
                int second_p = Integer.parseInt(second_show.getText().toString());
                second_p++;
                if (second_p > 59) {
                    second_p = 0;
                }
                second_show.setText(""+second_p);
                break;
            case R.id.second_minus:
                int second_m = Integer.parseInt(second_show.getText().toString());
                second_m--;
                if (second_m < 0) {
                    second_m = 59;
                }
                second_show.setText(""+second_m);
                break;
        }
    }

    /**
     * 获取view
     * @param viewId
     * @param group
     * @param <T>
     * @return
     */
    public  <T extends View> T getView(int viewId,Dialog group) {
        View view = group.findViewById(viewId);
        return (T) view;
    }
    /**
     * 对控件进行填充
     * @param mYear
     * @param mMonth
     * @param mDay
     * @param mHour
     * @param mMinute
     * @param mSecond
     * @param dialog
     */
    private void setTimerPickEdit(String mYear, String mMonth, String mDay, String mHour, String mMinute, String mSecond, AlertDialog dialog) {
        year_show = getView(R.id.year_show,dialog);
        year_show.setText(mYear);
        year_show.setSelection(mYear.length());
        month_show = getView(R.id.month_show,dialog);
        year_show.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    year_show.setSelection(s.length());
                }
            }
        });
        if (Integer.valueOf(mMonth) > 0 && Integer.valueOf(mMonth) < 10) {
            month_show.setText("0" + mMonth);
        } else {
            month_show.setText(mMonth);
        }
        month_show.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (Integer.valueOf(s.toString()) > 12) {
                        month_show.setText("12");
                    } else {
                        int content = Integer.valueOf(s.toString());
                        if (s.length() > 2) {
                            month_show.setText(""+content);
                        }
                    }
                    month_show.setSelection(month_show.getText().toString().length());
                }
            }
        });
        day_show = getView(R.id.day_show,dialog);
        if (Integer.valueOf(mDay) > 0 && Integer.valueOf(mDay) < 10) {
            day_show.setText("0" + mDay);
        } else {
            day_show.setText(mDay);
        }
        day_show.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (Integer.valueOf(s.toString()) > 31) {
                        day_show.setText("31");
                    } else {
                        int content = Integer.valueOf(s.toString());
                        if (s.length() > 2) {
                            day_show.setText(""+content);
                        }
                    }
                    day_show.setSelection(day_show.getText().toString().length());
                }
            }
        });
        hour_show = getView(R.id.hour_show,dialog);
        if (Integer.valueOf(mHour) > 0 && Integer.valueOf(mHour) < 10) {
            hour_show.setText("0" + mHour);
        } else {
            hour_show.setText(mHour);
        }
        hour_show.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (Integer.valueOf(s.toString()) > 23) {
                        hour_show.setText("23");
                    }else {
                        int content = Integer.valueOf(s.toString());
                        if (s.length() > 2) {
                            hour_show.setText(""+content);
                        }
                    }
                    hour_show.setSelection(hour_show.getText().toString().length());
                }
            }
        });
        minus_show = getView(R.id.minus_show,dialog);
        if (Integer.valueOf(mMinute) > 0 && Integer.valueOf(mMinute) < 10) {
            minus_show.setText("0" + mMinute);
        } else {
            minus_show.setText(mMinute);
        }
        minus_show.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (Integer.valueOf(s.toString()) > 59) {
                        minus_show.setText("59");
                    } else {
                        int content = Integer.valueOf(s.toString());
                        if (s.length() > 2) {
                            minus_show.setText("" + content);
                        }
                    }
                    minus_show.setSelection(minus_show.getText().toString().length());
                }
            }
        });
        second_show = getView(R.id.second_show,dialog);
        second_show.setText(mSecond);
        if (Integer.valueOf(mSecond) > 0 && Integer.valueOf(mSecond) < 10) {
            second_show.setText("0" + mSecond);
        } else {
            second_show.setText(mSecond);
        }
        second_show.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (Integer.valueOf(s.toString()) > 59) {
                        second_show.setText("59");
                    } else {
                        int content = Integer.valueOf(s.toString());
                        if (s.length() > 2) {
                            second_show.setText("" + content);
                        }
                    }
                    second_show.setSelection(second_show.getText().toString().length());
                }
            }
        });
    }
    public static class Builder{
        private boolean canceledOnTouchOutside;
        private boolean cancelable;
        private OnTimePickListener listener;//获取时间回调
        private Context context;
        public Builder init(Context context){
            this.context = context;
            return this;
        }
        public Builder canceledOnTouchOutside(boolean o){
            this.canceledOnTouchOutside = o;
            return this;
        }
        public Builder cancelable(boolean c){
            this.cancelable = c;
            return this;
        }
        public Builder pickListener(OnTimePickListener listener){
            this.listener = listener;
            return this;
        }
        public DateTimePicker builder(){
            return new DateTimePicker(this);
        }
    }
}
