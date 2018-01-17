package com.huami.merchant.mvpbase;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Henry on 2017/5/3.
 */
public class BaseConsts {
    public static class SharePreference{
        public static String PUBLIC_KEY_STORE="PUBLIC_KEY_STORE";
    }
    public static Properties props;
    static {
        props = new Properties();
        try {
            InputStream in = BaseApplication.getContext().getAssets().open("appconfig");
            props.load(in);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    private static final String DEBUG_BASE_URl=props.getProperty("DEBUG_BASE_URl");

    private static final String RELEASE_BASE_URl = props.getProperty("RELEASE_BASE_URl");


    public static final String BASE_URL = isApkDebug(BaseApplication.getContext()) == true ? DEBUG_BASE_URl : RELEASE_BASE_URl;
    /**
     * 用户登录  推出
     */
    public static final String BASE_URL_USER = BASE_URL+"/mine/login"; //email  password
    /**
     * 获取商户任务列表
     */
    public static final String BASE_URL_TASK = BASE_URL + "/bosstask/selbossTask";
    /**
     * 获取用户带审核列表
     */
    public static final String BASE_URL_PREVIEW_TASK = BASE_URL + "/bosstask/revieweTask";
    /**
     * 获取用户已审核列表
     */
    public static final String BASE_URL_ALREADY_REVIEW_TASK = BASE_URL + "/bosstask/alreadyRevieweTaskList";
    /**
     * 获取调研问卷列表
     */
    public static final String BASE_URL_TASK_PAPER_LIST = BASE_URL + "/bosstask/selTaskPaperListTemp";
    /**
     * 获取商户门店
     */
    public static final String BASE_URL_TASK_POINT_SHOP = BASE_URL + "/bosstask/selectMerShop";
    /**
     * 获取任务点
     */
    public static final String BASE_URL_TASK_POINT = BASE_URL + "/bosstask/bossTaskPoint";
    /**
     * 获取已审任务详情
     */
    public static final String BASE_URL_TASK_ReviewResult = BASE_URL + "/bosstask/bossReviewResult";
    /**
     * 审核通过
     */
    public static final String BASE_URL_TASK_PASS = BASE_URL + "/bossquestion/bossQuestionCheckPass";
    /**
     * 审核不通过
     */
    public static final String BASE_URL_TASK_NO_PASS = BASE_URL + "/bossquestion/bossQuestionNoPass";
    /**
     * 获取待审任务详情
     */
    public static final String BASE_URL_TASK_ReviewPending = BASE_URL + "/questionnaire/selTaskPaperInfo";
    /**
     * 获取最大审核次数
     */
    public static final String BASE_URL_TASK_getMaxAudit = BASE_URL + "/questionnaire/getMaxAudit";
    /**
     * 个人中心数据字段
     */
    public class USER_CENTER{
        public static final String USER_ID ="USER_ID";
        public static final String USER_EMAIL ="USER_EMAIL";
        public static final String USER_REAL_NAME ="USER_REAL_NAME";
        public static final String USER_HEAD_PIC ="USER_HEAD_PIC";
        public static final String USER_TOKEN ="USER_TOKEN";
    }
    /**
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     * @return
     */
    public static boolean isApkDebug(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {
        }
        return false;
    }
}
