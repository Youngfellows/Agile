package com.mingyuechunqiu.agile.ui.diaglogfragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mingyuechunqiu.agile.feature.loading.data.Constants;
import com.mingyuechunqiu.agile.feature.loading.data.LoadingDialogFragmentOption;
import com.mingyuechunqiu.agile.feature.loading.provider.LoadingDfgProvideFactory;
import com.mingyuechunqiu.agile.feature.loading.provider.LoadingDfgProviderable;

/**
 * <pre>
 *     author : xyj
 *     e-mail : yujie.xi@ehailuo.com
 *     time   : 2018/06/14
 *     desc   : 所有BaseDialogFragment的基类
 *              继承自AppCompatDialogFragment
 *     version: 1.0
 * </pre>
 */
public abstract class BaseDialogFragment extends AppCompatDialogFragment {

    private Toast mToast;
    private LoadingDfgProviderable mLoadingDfgProvider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去掉对话框的背景，以便设置自已样式的背景
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        dismissLoadingDialog();
        super.onDestroyView();
        releaseOnDestroyView();
        mToast = null;
        mLoadingDfgProvider = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseOnDestroy();
    }

    /**
     * 显示提示信息
     *
     * @param hint 提示文本
     */
    protected void showToast(@Nullable String hint) {
        if (TextUtils.isEmpty(hint)) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), hint, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(hint);
        }
        mToast.show();
    }

    /**
     * 根据资源id显示提示信息
     *
     * @param stringResourceId 提示文本资源id
     */
    protected void showToast(@StringRes int stringResourceId) {
        showToast(getString(stringResourceId));
    }

    /**
     * 显示加载对话框
     *
     * @param hint       提示文本
     * @param cancelable 是否可以取消
     */
    protected void showLoadingDialog(@Nullable String hint, boolean cancelable) {
        LoadingDialogFragmentOption option = getLoadingDialog().getLoadingFragmentOption();
        option.setText(hint);
        option.setCancelWithOutside(cancelable);
        showLoadingDialog(interceptLoadingFragmentOption(option, Constants.ModeType.TYPE_DIALOG));
    }

    /**
     * 显示加载Fragment
     *
     * @param option 加载配置参数信息对象
     */
    protected void showLoadingDialog(@Nullable LoadingDialogFragmentOption option) {
        LoadingDialogFragmentOption temp = interceptLoadingFragmentOption(option, Constants.ModeType.TYPE_DIALOG);
        if (mLoadingDfgProvider == null) {
            mLoadingDfgProvider = LoadingDfgProvideFactory.newInstance(temp);
        } else {
            //在这儿默认逻辑为如果option为空，代表不变
            if (temp != null) {
                mLoadingDfgProvider.setLoadingFragmentOption(temp);
            }
            if (mLoadingDfgProvider.showLoadingDialog()) {
                return;
            }
        }
        //除隐藏对话框再显示用getDialog().show()，其他都直接用show()
        mLoadingDfgProvider.showLoadingDialog(getFragmentManager());
    }

    /**
     * 关闭加载对话框
     */
    protected void dismissLoadingDialog() {
        if (mLoadingDfgProvider != null) {
            mLoadingDfgProvider.dismissLoadingDialog(true);
        }
    }

    /**
     * 添加显示加载对话框
     *
     * @param manager     Fragment管理器
     * @param containerId 对话框所属布局ID
     * @param option      加载对话框配置信息对象
     */
    protected void addOrShowLoadingDialog(FragmentManager manager, @IdRes int containerId, LoadingDialogFragmentOption option) {
        getLoadingDialog().addOrShowLoadingDialog(manager, containerId,
                interceptLoadingFragmentOption(option, Constants.ModeType.TYPE_FRAGMENT));
    }

    /**
     * 隐藏加载对话框
     *
     * @param manager Fragment管理器
     */
    protected void hideLoadingDialog(FragmentManager manager) {
        getLoadingDialog().hideLoadingDialog(manager);
    }

    /**
     * 移除加载对话框
     *
     * @param manager Fragment管理器
     */
    protected void removeLoadingDialog(FragmentManager manager) {
        getLoadingDialog().removeLoadingDialog(manager);
    }

    /**
     * 获取加载Fragment实例
     *
     * @return 返回加载Fragment实例
     */
    @NonNull
    protected LoadingDfgProviderable getLoadingDialog() {
        if (mLoadingDfgProvider == null) {
            mLoadingDfgProvider = LoadingDfgProvideFactory.newInstance();
        }
        return mLoadingDfgProvider;
    }

    /**
     * 拦截加载对话框配置信息对象
     *
     * @param option   加载对话框配置信息对象
     * @param modeType 加载对话框模式
     * @return 返回进行过拦截处理的加载对话框配置信息对象
     */
    protected LoadingDialogFragmentOption interceptLoadingFragmentOption(
            @Nullable LoadingDialogFragmentOption option, Constants.ModeType modeType) {
        return modeType == Constants.ModeType.TYPE_NOT_SET ? null : option;
    }

    /**
     * 释放资源（在onDestroyView时调用）
     */
    protected abstract void releaseOnDestroyView();

    /**
     * 释放资源（在onDestroy时调用）
     */
    protected abstract void releaseOnDestroy();

    /**
     * 由子类重写控件的初始化方法
     *
     * @param inflater           布局填充器
     * @param container          填充的布局所在父布局
     * @param savedInstanceState 界面销毁时保存的状态数据实例
     * @return 返回创建的填充View
     */
    protected abstract View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 供Activity实现的回调接口，实现对Fragment的调用
     */
    public interface Callback {

        /**
         * 供Activity使用的回调方法
         *
         * @param fragment 传递Fragment自身给其所在的Activity使用
         * @param bundle   用于Fragment向Activity传递数据
         */
        void onCall(DialogFragment fragment, Bundle bundle);
    }
}