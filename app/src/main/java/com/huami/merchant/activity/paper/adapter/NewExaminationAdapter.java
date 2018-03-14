package com.huami.merchant.activity.paper.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.huami.merchant.R;
import com.huami.merchant.bean.AlreadyBean;
import com.huami.merchant.bean.QuestionSinglePostil;
import com.huami.merchant.bean.TaskPaperAlreadyPending.TaskPaperAlreadyPendingData.TaskPaperAlreadyPendingHistoryPostil;
import com.huami.merchant.bean.TaskPaperPendingBean;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.TaskAnswer;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.TaskPaper.ExaminationInner;
import com.huami.merchant.designView.recycle.FullyLinearLayoutManager;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.util.DisplayUtil;
import com.huami.merchant.util.VideoPlayManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henry on 2017/5/22.
 */

public class NewExaminationAdapter extends RecyclerView.Adapter<NewExaminationAdapter.NewExaminationHolder>{
    private List<TaskPaperPendingBean.TaskPaperData.TaskPaper.ExaminationInner> examinations;
    private OnRecycleItemClickListener listener;
    private List<TaskAnswer> answers;
    private List<TaskPaperAlreadyPendingHistoryPostil> postils;
    private AlreadyBean already;
    public NewExaminationAdapter(List<ExaminationInner> examinations, List<TaskAnswer> answers, List<TaskPaperAlreadyPendingHistoryPostil> postils, AlreadyBean already, OnRecycleItemClickListener listener) {
        this.examinations = examinations;
        this.listener = listener;
        this.answers = answers;
        this.postils = postils;
        this.already = already;
    }
    public NewExaminationAdapter(List<ExaminationInner> examinations, List<TaskAnswer> answers,OnRecycleItemClickListener listener) {
        this.examinations = examinations;
        this.listener = listener;
        this.answers = answers;
    }
    @Override
    public NewExaminationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam_singlesel, parent, false);
        NewExaminationHolder holder = new NewExaminationHolder(view);
        return holder;
    }
    private int page = 0;
    /**
     * 设置要显示的页数
     * @param page
     */
    public void setPage(int page){
        this.page = page;
    }
    @Override
    public void onBindViewHolder(NewExaminationHolder holder, final int position) {
        Log.e("我的size", "--->"+examinations.size());
        final ExaminationInner examinationInner = examinations.get(position);
        holder.exam_title.setText((position + 1) + ":" + examinationInner.getTitle().replace("\n", "").replace("\r", ""));//题号加题目标题
        holder.templet.removeAllViews();
        List<ExaminationInner.Body> examination_body = examinationInner.getBody();
        for (ExaminationInner.Body body : examination_body) {
            switch (body.getType()) {
                case "radio":
                    inspectSingleRadio(holder, position, body);
                    break;
                case "textarea":
                    inspectTextArea(holder, position, body);
                    break;
                case "photo":
                    inspectPhoto(holder, position, body);
                    break;
                case "number":
                    inspectNumber(holder,body);
                    break;
                case "datetime":
                    inspectTime(holder, position, body);
                    break;
                case "date":
                    inspectDate(holder, position, body);
                    break;
                case "star":
                    inspectStar(holder,body);
                    break;
                case "checkbox":
                    inspectCheckBox(holder,position,body);
                    break;
                case "sort":
                    inspectSort(holder,position,body);
                    break;
                case "audio":
                    inspectAudio(holder,position,body);
                    break;
                case "video":
                    inspectVideo(holder,position,body);
                    break;
            }
        }
        if (!already.isAlready()) {
            holder.postil.setVisibility(View.GONE);
            holder.add_postil.setVisibility(View.VISIBLE);
            holder.add_postil.setText(examinationInner.getPostil());
            if (!TextUtils.isEmpty(examinationInner.getPostil())) {
                holder.add_postil.setSelection(examinationInner.getPostil().length());
            }
            holder.add_postil.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    examinationInner.setPostil(s.toString());
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } else {
            holder.add_postil.setVisibility(View.GONE);
            holder.postil.setVisibility(View.GONE);
            if (postils.size()!=0) {
                for (TaskPaperAlreadyPendingHistoryPostil postil : postils) {
                    if (examinationInner.getqId() == postil.getQuestion_id()) {
                        holder.postil.setText(""+postil.getQuestion_content());
                        holder.postil.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    /**
     * 检查video 插入操作
     *
     * @param holder
     * @param position
     * @param body
     */
    private void inspectVideo(NewExaminationHolder holder, final int position, ExaminationInner.Body body) {
        int width = DisplayUtil.getScreenWidthPixels();
        int bodyId = body.getId();
        final TaskAnswer answerInfo = getAnswerFromId(bodyId);
        final View item_video = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_video_check, null);
        holder.templet.addView(item_video);
        ImageView add_video = (ImageView) item_video.findViewById(R.id.add_video);
        LinearLayout.LayoutParams params_base = new LinearLayout.LayoutParams(width / 5 - 20, width / 5 - 20);
        add_video.setLayoutParams(params_base);
        add_video.setTag(body.getId());
        add_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position);
            }
        });
        String content = answerInfo.getAnswer();
        if (!TextUtils.isEmpty(content)) {
            LinearLayout add_select_photo = (LinearLayout) item_video.findViewById(R.id.add_select_video);
            String[] split = content.split(",");
            int i = 0;
            for (String url : split) {
                View view = LayoutInflater.from(item_video.getContext()).inflate(R.layout.item_single_video_view, null);
                View iv_out = view.findViewById(R.id.play_video_liner);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 5 - 20, width / 5 - 20);
                params.setMargins(0, 20, 20, 20);
                view.setLayoutParams(params);
                add_select_photo.addView(view, 0);
                iv_out.setTag(url);
                ImageView video_photo = (ImageView) view.findViewById(R.id.video_photo);
                String thumbnail = VideoPlayManager.exist(url);
                if (thumbnail != null) {
                    Glide.with(video_photo.getContext()).load(Uri.fromFile(new File(thumbnail))).placeholder(R.mipmap.video_file).error(R.mipmap.video_file).into(video_photo);
                } else {
                    Glide.with(video_photo.getContext()).load(R.mipmap.video_file).asBitmap().into(video_photo);
                }
                i++;
                iv_out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(v, position);
                    }
                });
            }
        }
    }

    /**
     * 检查音频 插入操作
     *
     * @param holder
     * @param position
     * @param body
     */
    private void inspectAudio(NewExaminationHolder holder, final int position,ExaminationInner.Body body) {
        int width = DisplayUtil.getScreenWidthPixels();
        int bodyId = body.getId();
        final TaskAnswer answerInfo = getAnswerFromId(bodyId);
        final View item_audio = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_audio_check, null);
        holder.templet.addView(item_audio);
        ImageView audio_add = (ImageView) item_audio.findViewById(R.id.audio_add);
        LinearLayout.LayoutParams params_base = new LinearLayout.LayoutParams(width / 5 - 20, width / 5 - 20);
        audio_add.setLayoutParams(params_base);
        audio_add.setTag(body.getId());
        audio_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position);
            }
        });
        String content = answerInfo.getAnswer();
        if (!TextUtils.isEmpty(content)) {
            LinearLayout add_select_photo = (LinearLayout) item_audio.findViewById(R.id.add_sel_audio);
            String[] split = content.split(",");
            int i = 0;
            for (String url : split) {
                View view = LayoutInflater.from(item_audio.getContext()).inflate(R.layout.item_single_audio_view, null);
                View iv_out = view.findViewById(R.id.play_audio);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 5 - 20, width / 5 - 20);
                params.setMargins(0, 20, 20, 20);
                view.setLayoutParams(params);
                add_select_photo.addView(view, 0);
                iv_out.setTag(url);
                i++;
                iv_out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(v, position);
                    }
                });
            }
        }
    }

    private List<ItemTouchHelper> helpers = new ArrayList<>();
    private RecyclerView sort_recycle;
    private FullyLinearLayoutManager manager;
    private ExaminationSortAdapter adapter;
    private int index;
    /**
     * 排序域的操作
     * @param holder
     * @param position
     * @param body
     */
    private void inspectSort(NewExaminationHolder holder, int position, final ExaminationInner.Body body) {
        int bodyId = body.getId();
        final TaskPaperPendingBean.TaskPaperData.TaskAnswer answerInfo = getAnswerFromId(bodyId);
        final View item_sort = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_sort_recycle, null);
        sort_recycle = (RecyclerView) item_sort.findViewById(R.id.sort_recycle);
        manager = new FullyLinearLayoutManager(holder.exam_title.getContext());
        sort_recycle.setLayoutManager(manager);
        adapter = new ExaminationSortAdapter(body.getOptions(),answerInfo);
        adapter.setPosition(index);
        sort_recycle.setAdapter(adapter);
        index++;
        holder.templet.addView(item_sort);
    }

    /**
     * 多选域的操作
     * @param holder
     * @param position
     * @param body
     */
    private void inspectCheckBox(NewExaminationHolder holder, final int position,ExaminationInner.Body body) {
        int bodyId = body.getId();
        final TaskAnswer answerInfo = getAnswerFromId(bodyId);
        String response = answerInfo.getAnswer();
        List<String> check = new ArrayList<>();//主要用来判断哪个被选中了
        if (!TextUtils.isEmpty(response)) {
            for (String item : response.split(",")) {
                check.add(item);
            }
        }
        int i = 0;
        for (ExaminationInner.Body.Options info:body.getOptions()) {
            i++;
            View item_multiple_choice_inner = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_multiple_choice_inner, null);
            holder.templet.addView(item_multiple_choice_inner);
            ((TextView) item_multiple_choice_inner.findViewById(R.id.selection_name)).setText(info.getContent().replace("\n","").replace("\r",""));
            final ImageView checkBox = (ImageView) item_multiple_choice_inner.findViewById(R.id.multiple_selection_check);
            checkBox.setTag(info.getId());
            if (!check.contains(String.valueOf(i))) {
                checkBox.setImageResource(R.mipmap.multiple_choice);
            } else {
                checkBox.setImageResource(R.mipmap.multiple_choice_sel);
            }
        }
    }

    /**
     * 日期域设置
     *
     * @param holder
     * @param position
     * @param body
     */
    private void inspectDate(NewExaminationHolder holder, final int position,ExaminationInner.Body body) {
        View data_view = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_date_single, null);
        TextView date = (TextView) data_view.findViewById(R.id.get_date);
        date.setTag(body.getId());
        int bodyId = body.getId();
        TaskAnswer answerInfo = getAnswerFromId(bodyId);
        String content_time = answerInfo.getAnswer();
        if (!TextUtils.isEmpty(content_time)) {
            date.setText(content_time);
        }
        holder.templet.addView(data_view);
    }

    private TextView move_layout;
    /**
     * 量表域设置
     * @param holder
     * @param body
     */
    private void inspectStar(NewExaminationHolder holder, final ExaminationInner.Body body) {
        View item_seek = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_seek, null);
        holder.templet.addView(item_seek);
        move_layout = (TextView) item_seek.findViewById(R.id.move_layout);
        TextView seek_pre= (TextView) item_seek.findViewById(R.id.seek_pre);
        TextView seek_next= (TextView) item_seek.findViewById(R.id.seek_next);
        int min = body.getMin();
        int max = body.getMax();
        String descs = body.getDescs();
        if (!TextUtils.isEmpty(descs)) {
            String[] split = descs.split(",");
            if (split.length == 2) {
                seek_pre.setText(split[0]);
                seek_next.setText(split[1]);
            } else if (split.length == 1) {
                seek_pre.setText(split[0]);
            }
        }
        SeekBar play_progress = (SeekBar) item_seek.findViewById(R.id.play_progress);
        play_progress.setEnabled(false);
        int bodyId = body.getId();
        final TaskAnswer answerInfo = getAnswerFromId(bodyId);
        int progress_pb =min;//用来设置进度
        if (!TextUtils.isEmpty(answerInfo.getAnswer())) {
            progress_pb = Integer.valueOf(answerInfo.getAnswer());
        }
        move_layout.setText(""+progress_pb);
        int progress = (int) (progress_pb*1.0 / (max - min + 1) * 100);
        play_progress.setProgress(progress);
    }
    /**
     * 时间域设置
     * @param holder
     * @param position
     * @param body
     */
    private void inspectTime(NewExaminationHolder holder, final int position,ExaminationInner.Body body) {
        View time_view = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_time_single, null);
        TextView timer = (TextView) time_view.findViewById(R.id.get_time);
        timer.setTag(body.getId());
        int bodyId = body.getId();
        TaskAnswer answerInfo = getAnswerFromId(bodyId);
        String content_time = answerInfo.getAnswer();
        if (!TextUtils.isEmpty(content_time)) {
            timer.setText(content_time);
        }
        holder.templet.addView(time_view);
    }
    /**
     * number域的操作
     * @param holder
     * @param body
     */
    private void inspectNumber(NewExaminationHolder holder, final ExaminationInner.Body body) {
        int min = body.getMin();
        final int max = body.getMax();
        View input_single_view = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_text_single, null);
        final TextView input_single = (TextView) input_single_view.findViewById(R.id.input_single);
        input_single.clearFocus();
        input_single.setHint("输入范围:("+min+"~~"+max+")");
        holder.templet.addView(input_single_view);
        final int bodyId = body.getId();
        String input_single_content = getAnswerFromId(bodyId).getAnswer();//拿到保存的文本
        if (!TextUtils.isEmpty(input_single_content)) {
            input_single.setText(input_single_content);
        }
    }

    /**
     * 获取当前题目的答案项
     * @return 当前答案项
     */
    public TaskAnswer getAnswerFromId(int bodyId) {
        TaskAnswer info = null;
        for (TaskAnswer examinationAnswerInfo : answers) {
            if (examinationAnswerInfo.getBody_id() == bodyId) {
                info = examinationAnswerInfo;
                break;
            }
        }
        return info;
    }
    /**
     * 设置单选按钮的状态
     * @param holder
     * @param position
     */
    private void inspectSingleRadio(NewExaminationHolder holder, final int position,ExaminationInner.Body body) {
        List<ExaminationInner.Body.Options> options = body.getOptions();
        View single_inner=null;
        int bodyId = body.getId();
        TaskAnswer answerInfo =getAnswerFromId(bodyId);
        int selIndex = 0;
        for (final ExaminationInner.Body.Options info:options) {
            selIndex++;
            single_inner= LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_single_selection_inner, null);
            final ImageView single_selection_check = (ImageView) single_inner.findViewById(R.id.single_selection_check);
            single_selection_check.setTag(info.getId());
            TextView selection_name = (TextView) single_inner.findViewById(R.id.selection_name);
            selection_name.setText(info.getContent().replace("\n","").replace("\r",""));
            //判断选中状态需要根据答案表来判断
            try {
                if (!TextUtils.isEmpty(answerInfo.getAnswer())&&Integer.valueOf(answerInfo.getAnswer())==selIndex) {
                    single_selection_check.setImageResource(R.mipmap.single_check_sel);
                } else {
                    single_selection_check.setImageResource(R.mipmap.single_check);
                }
            } catch (Exception e) {

            }
            holder.templet.addView(single_inner);
        }
    }
    @Override
    public int getItemCount() {
        return examinations.size();
    }

    /**
     * 文本域操作
     *
     * @param holder
     * @param position
     * @param body
     */
    private void inspectTextArea(NewExaminationHolder holder, final int position, final ExaminationInner.Body body) {
        final int bodyId = body.getId();
        final TaskAnswer answerInfo = getAnswerFromId(bodyId);

        View item_textArea = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_textarea, null);
        holder.templet.addView(item_textArea);

        final TextView content_tv = (TextView) item_textArea.findViewById(R.id.input_area);
        final TextView tip_count = (TextView) item_textArea.findViewById(R.id.tip_count);
        content_tv.setTag(body.getId());
        if (!TextUtils.isEmpty(answerInfo.getAnswer())) {
            content_tv.setText(answerInfo.getAnswer());
            tip_count.setText(answerInfo.getAnswer().length()+"/"+body.getMin());
        } else {
            tip_count.setText("0/"+body.getMin());
        }
    }
    /**
     * 图片域操作
     * @param holder
     * @param position
     * @param body
     */
    private void inspectPhoto(NewExaminationHolder holder, final int position,ExaminationInner.Body body) {
        int bodyId = body.getId();
        TaskAnswer answerInfo = getAnswerFromId(bodyId);
        int width = DisplayUtil.getScreenWidthPixels();
        View item_photo_check = LayoutInflater.from(holder.exam_title.getContext()).inflate(R.layout.item_photo_check, null);
        holder.templet.addView(item_photo_check);
        String content = answerInfo.getAnswer();//获取所有照片
        if (!TextUtils.isEmpty(content)) {
            LinearLayout add_select_photo = (LinearLayout) item_photo_check.findViewById(R.id.add_select_photo);
            String[] split = content.split(",");
            int i = 0;
            for (String url : split) {
                View view = LayoutInflater.from(item_photo_check.getContext()).inflate(R.layout.item_single_photo, null);
                View iv_out = view.findViewById(R.id.iv_out);
                ImageView iv = (ImageView) view.findViewById(R.id.iv_photo);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 5 - 10, width / 5 - 10);
                params.setMargins(0,0,20,0);
                view.setLayoutParams(params);
                Glide.with(iv.getContext()).load(url).asBitmap().placeholder(R.mipmap.rectangle_seize_pic).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.mipmap.rectangle_seize_pic).centerCrop().into(iv);
                add_select_photo.addView(view, 0);
                iv_out.setTag(url);
                i++;
            }
        }
    }
    public class NewExaminationHolder extends RecyclerView.ViewHolder {
        private View content;
        private TextView exam_title;
        private LinearLayout templet;
        private TextView postil;
        private EditText add_postil;
        public NewExaminationHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            exam_title = (TextView) itemView.findViewById(R.id.exam_title);
            postil = (TextView) itemView.findViewById(R.id.postil);
            templet = (LinearLayout) itemView.findViewById(R.id.templet);
            add_postil = (EditText) itemView.findViewById(R.id.add_postil);
        }
    }
}
