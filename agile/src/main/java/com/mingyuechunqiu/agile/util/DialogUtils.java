package com.mingyuechunqiu.agile.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.mingyuechunqiu.agile.R;

import pub.devrel.easypermissions.AppSettingsDialog;

/**
 * <pre>
 *     author : 明月春秋
 *     e-mail : xiyujieit@163.com
 *     time   : 2018/05/12
 *     desc   : 对话框工具类
 *     version: 1.0
 * </pre>
 */
public final class DialogUtils {

    private DialogUtils() {
    }

    /**
     * 显示警示对话框
     *
     * @param context          上下文
     * @param title            标题
     * @param message          内容
     * @param positiveListener 确认按钮监听器
     * @param negativeListener 取消按钮监听器
     */
    public static void showAlertDialog(@NonNull Context context, @NonNull String title, @Nullable String message,
                                       @NonNull DialogInterface.OnClickListener positiveListener,
                                       @Nullable DialogInterface.OnClickListener negativeListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.confirm, positiveListener)
                .setNegativeButton(R.string.cancel, negativeListener)
                .create()
                .show();
    }

    /**
     * 获取加载对话框
     *
     * @param context    上下文
     * @param hint       加载提示文字
     * @param cancelable 点击界面对话框是否消失
     * @return 返回生成的对话框
     */
    public static Dialog getLoadingDialog(@NonNull Context context, @Nullable String hint, boolean cancelable) {
        if (TextUtils.isEmpty(hint)) {
            hint = context.getString(R.string.agile_prompt_loading);
        }
        View view = View.inflate(context, R.layout.agile_dialog_fragment_status_view, null);
        AppCompatTextView tvMsg = view.findViewById(R.id.tv_agile_dfg_status_view_content);
        tvMsg.setText(hint);
        return new AlertDialog.Builder(context, R.style.Dialog_Loading)
                .setView(view)
                .setCancelable(cancelable)
                .create();
    }

    /**
     * 让对话框消失
     *
     * @param dialog 对话框
     */
    public static void disappearDialog(@Nullable Dialog dialog) {
        if (dialog == null) {
            return;
        }
        dialog.dismiss();
    }

    /**
     * 显示权限设置对话框
     *
     * @param activity 界面
     */
    public static void showSetPermissionsDialog(@Nullable Activity activity, @StringRes int rationaleResId) {
        if (activity == null) {
            return;
        }
        new AppSettingsDialog.Builder(activity)
                .setTitle(R.string.set_permission)
                .setRationale(rationaleResId)
                .setPositiveButton(R.string.set)
                .setNegativeButton(R.string.cancel)
                .build().show();
    }

    /**
     * 显示权限设置对话框
     *
     * @param fragment 界面
     */
    public static void showSetPermissionsDialog(@Nullable Fragment fragment, @StringRes int rationaleResId) {
        if (fragment == null) {
            return;
        }
        new AppSettingsDialog.Builder(fragment)
                .setTitle(R.string.set_permission)
                .setRationale(rationaleResId)
                .setPositiveButton(R.string.set)
                .setNegativeButton(R.string.cancel)
                .build().show();
    }
}
