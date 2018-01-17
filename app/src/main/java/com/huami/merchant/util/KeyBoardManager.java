package com.huami.merchant.util;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Henry on 2017/9/21.
 */

public class KeyBoardManager {
    public static void hideSoftInputKeyBoard(Context context, IBinder binder){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binder,0);
    }
}
