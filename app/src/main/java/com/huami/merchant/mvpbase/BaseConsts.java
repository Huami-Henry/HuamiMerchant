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
    private static final String DEBUG_BASE_URl=props.getProperty("DEBUG_BASE_URl");//url
    private static final String BASE_HTML_URL=props.getProperty("BASE_HTML_URL");//url
    private static final String DEBUG_BASE_IMAGE=props.getProperty("DEBUG_BASE_IMAGE");//image
    private static final String RELEASE_BASE_IMAGE=props.getProperty("RELEASE_BASE_IMAGE");//image

    private static final String RELEASE_BASE_URl = props.getProperty("RELEASE_BASE_URl");


    public static final String BASE_URL = isApkDebug(BaseApplication.getContext()) == true ? DEBUG_BASE_URl : RELEASE_BASE_URl;
    public static final String BASE_URL_IMAGE = isApkDebug(BaseApplication.getContext()) == true ? DEBUG_BASE_IMAGE : RELEASE_BASE_IMAGE;
    /**
     * 用户登录  推出
     */
    public static final String BASE_URL_USER = BASE_URL+"/mine/login"; //email  password
    /**
     * 用户 推出
     */
    public static final String BASE_URL_LOGIN_OUT = BASE_URL+"/mine/loginOut";
    /**
     * 个人信息
     */
    public static final String BASE_URL_USER_CENTER = BASE_URL+"/mine/userInfo"; //email  password
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
    public static final String BASE_URL_TASK_PAPER_LIST = BASE_URL + "/bossquestion/selTaskPaperListTemp";
    /**
     * 获取培训列表
     */
    public static final String BASE_URL_TRAIN_LIST = BASE_URL + "/bosstask/selPublishTraList";
    /**
     * 获取培训问卷列表
     */
    public static final String BASE_URL_TRAIN_PAPER_LIST = BASE_URL + "/bossquestion/selTraPaperList";
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
     * 审核不通过原因
     */
    public static final String BASE_URL_TASK_FAILURE_REASON = BASE_URL + "/bosstask/checkTaskFailure";
    /**
     * 获取待审任务详情
     */
    public static final String BASE_URL_TASK_ReviewPending = BASE_URL + "/questionnaire/selTaskPaperInfo";
    /**
     * 获取最大审核次数
     */
    public static final String BASE_URL_TASK_getMaxAudit = BASE_URL + "/questionnaire/getMaxAudit";
    /**
     * 获取审核标签
     */
    public static final String BASE_URL_TASK_TAG = BASE_URL + "/questionnaire/selectUcaCheckResult";
    /**
     * 获取编辑任务详情
     */
    public static final String BASE_URL_TASK_DETAIL_INFO = BASE_URL + "/bosstask/bossTaskInfo";
    /**
     * (编辑)发布任务
     */
    public static final String BASE_URL_TASK_PUBLISH = BASE_URL + "/bosstask/publishTaskInfo";
    /**
     * 增值服务类别
     */
    public static final String BASE_URL_VALUE_SERVICE_TYPE = BASE_URL + "/appreciation/selectServPackageTypeList";
    /**
     * 增值服务
     */
    public static final String BASE_URL_VALUE_SERVICE = BASE_URL + "/appreciation/selectIncrementList";
    /**
     * 购买增值服务
     */
    public static final String BASE_URL_VALUE_SERVICE_PURCHASE = BASE_URL + "/bosstask/purchaseServPackage";
    /**
     * 增值服务详情
     */
    public static final String BASE_URL_VALUE_SERVICE_DETAIL = BASE_URL + "/appreciation/selectServPackageInfo?packageId=";
    /**
     * 增值单项服务包
     */
    public static final String BASE_URL_SINGLE_VALUE_SERVICE = BASE_URL + "/appreciation/selectEntryPackageList";
    /**
     * 计算购买金额
     */
    public static final String BASE_URL_SINGLE_VALUE_SERVICE_PRICE = BASE_URL + "/bosstask/getTotallAmount";//packageId  number  merUserId uuid
    /**
     * 问卷预览
     */
    public static final String TASK_PAPER_PREVIEW = BASE_URL + "/taskpaper/paperPreview.html?taskPaperId=";
    /**
     * 培训预览
     */
    public static final String TRAIN_PREVIEW = BASE_URL + "/trainpaper/trainPreview.html?trainId=";
    /**
     * 数据统计
     */
    public static final String DATA_STATISTICS = BASE_URL + "/data/count.html?taskId=";
    /**
     * 付款增值服务
     */
    public static final String PAY_SERVICE = BASE_URL + "/bosstask/purchaseServPackage";//packageId number cellPhone  contact merUserId
    /**
     * 获取已购买增值服务
     */
    public static final String ALREADY_BUY = BASE_URL + "/bosstask/selectMerServPackageList";//merUserId  uuid
    /**
     * 使用帮助
     */
    public static final String USER_HELP = "http://media.darenlaiye.com/darenlaiye/appExt/cooperation/cooperation.html";
    /**
     * 个人中心数据字段
     */
    /**
     * 首页的轮播图  发布的时候需要改
     */
    public static final String MAIN_TOPIC="http://interface.darenlaiye.com/darenlaiyeApp/home/topicRecommendWopingjia";



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
