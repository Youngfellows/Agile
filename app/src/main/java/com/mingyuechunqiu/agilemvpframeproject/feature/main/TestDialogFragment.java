package com.mingyuechunqiu.agilemvpframeproject.feature.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingyuechunqiu.agilemvpframe.feature.loading.data.LoadingDialogFragmentOption;
import com.mingyuechunqiu.agilemvpframe.feature.loading.provider.LoadingDfgProviderable;
import com.mingyuechunqiu.agilemvpframe.ui.diaglogfragment.BasePresenterDialogFragment;
import com.mingyuechunqiu.agilemvpframeproject.R;

/**
 * <pre>
 *     author : xyj
 *     Github : https://github.com/MingYueChunQiu
 *     e-mail : xiyujieit@163.com
 *     time   : 2019/5/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestDialogFragment extends BasePresenterDialogFragment<MainContract.View<MainContract.Presenter>, MainContract.Presenter>
        implements MainContract.View<MainContract.Presenter> {

    @Override
    public void showLoadingDialog(@Nullable String hint, boolean cancelable) {

    }

    @Override
    public void showLoadingDialog(@Nullable LoadingDialogFragmentOption option) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void addOrShowLoadingDialog(int containerId, LoadingDialogFragmentOption option) {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public LoadingDfgProviderable getLoadingDialog() {
        return null;
    }

    @Override
    protected void releaseOnDestroyView() {

    }

    @Override
    protected void releaseOnDestroy() {

    }

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setWindowAnimations(R.style.PopupDialogAnim);
        }
        return inflater.inflate(R.layout.activity_test, container, false);
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showToast(@Nullable String hint) {

    }

    @Override
    public void showToast(int stringResourceId) {

    }

    @Override
    public Context getCurrentContext() {
        return null;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter();
    }
}
