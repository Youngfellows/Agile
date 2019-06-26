package com.mingyuechunqiu.agile.base.model.part.dao.remote;

import android.support.annotation.StringRes;

import com.mingyuechunqiu.agile.base.framework.IBaseListener;

import retrofit2.Response;

/**
 * <pre>
 *     author : xyj
 *     Github : https://github.com/MingYueChunQiu
 *     e-mail : xiyujieit@163.com
 *     time   : 2019/6/26
 *     desc   : Retrofit网络Dao类
 *              继承自BaseAbstractNetworkDao
 *     version: 1.0
 * </pre>
 */
public class RetrofitDao<I extends IBaseListener> extends BaseAbstractNetworkDao<I> {

    protected OnModelDaoCallback mCallback;

    public RetrofitDao(I listener, OnModelDaoCallback callback) {
        super(listener);
        mCallback = callback;
    }

    @Override
    public void release() {
        super.release();
        mCallback = null;
    }

    /**
     * Model层Dao对象回调
     */
    public interface OnModelDaoCallback {

        /**
         * 检测Retrofit的网络响应是否为空
         *
         * @param response 网络响应
         * @return 如果网络响应为空返回true，否则返回false
         */
        boolean checkRetrofitResponseIsNull(Response response);

        /**
         * 根据网络响应返回码，进行不同处理
         *
         * @param code     网络响应返回码
         * @param errorMsg 网络请求错误信息
         * @return 返回true表示响应成功，否则返回false失败
         */
        boolean handleNetworkResponseCode(int code, String errorMsg);

        /**
         * 处理Retrofit网络响应失败事件
         *
         * @param t                抛出的异常
         * @param errorStringResId 错误提示字符串资源ID
         */
        void onNetworkResponseFailed(Throwable t, @StringRes int errorStringResId);
    }
}
