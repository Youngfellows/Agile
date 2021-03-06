package com.mingyuechunqiu.agile.feature.statusview.ui

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import com.mingyuechunqiu.agile.feature.statusview.bean.StatusViewOption
import com.mingyuechunqiu.agile.feature.statusview.bean.StatusViewTextOption
import com.mingyuechunqiu.agile.feature.statusview.constants.StatusViewConstants
import com.mingyuechunqiu.agile.feature.statusview.constants.StatusViewConstants.StatusType
import com.mingyuechunqiu.agile.ui.widget.DaisyLoadingView
import java.lang.IllegalArgumentException

/**
 * <pre>
 *      Project:    Agile
 *
 *      Author:     xiyujie
 *      Github:     https://github.com/MingYueChunQiu
 *      Email:      xiyujieit@163.com
 *      Time:       2019-11-24 19:38
 *      Desc:       状态视图代理类
 *                  实现IStatusViewDelegate
 *      Version:    1.0
 * </pre>
 */
internal class StatusViewDelegate(private val mOption: StatusViewOption) : IStatusViewDelegate {

    private var mModeType: StatusViewConstants.ModeType = StatusViewConstants.ModeType.TYPE_INVALID
    private var mStatusType = StatusType.TYPE_LOADING

    override fun getStatusViewOption(): StatusViewOption {
        return mOption
    }

    override fun setModeType(type: StatusViewConstants.ModeType) {
        mModeType = type
    }

    override fun getModeType(): StatusViewConstants.ModeType {
        return mModeType
    }

    override fun setStatusType(type: StatusType) {
        mStatusType = type
    }

    override fun getStatusType(): StatusType {
        return mStatusType
    }

    override fun release() {
        mModeType = StatusViewConstants.ModeType.TYPE_INVALID
    }

    override fun applyOption(vContainer: View?, vProgress: View?, ivIcon: ImageView?, tvContent: TextView?, tvReload: TextView?) {
        applyContainerConfigure(vContainer)
        applyProgressConfigure(vProgress)
        applyIconConfigure(ivIcon)
        applyTextConfigure(tvContent, tvReload)
    }

    private fun applyProgressConfigure(vProgress: View?) {
        vProgress?.visibility = if (mOption.isShowProgressView) View.VISIBLE else View.GONE
        val progressOption = mOption.progressOption ?: return
        when (progressOption.progressStyle) {
            StatusViewConstants.ProgressStyle.STYLE_SYSTEM -> {
                vProgress?.takeIf { it is ProgressBar }?.let {
                    val pbProgress = it as ProgressBar
                    progressOption.progressDrawable?.let { drawable ->
                        pbProgress.progressDrawable = drawable
                    }
                    progressOption.progressSize.takeIf { size -> size > 0 }
                            ?.let { size ->
                                pbProgress.layoutParams.width = size
                                pbProgress.layoutParams.height = size
                            }
                }
            }
            StatusViewConstants.ProgressStyle.STYLE_DAISY -> {
                vProgress?.takeIf { it is DaisyLoadingView }?.let {
                    val dlvProgress = it as DaisyLoadingView
                    progressOption.progressSize.takeIf { size -> size > 0 }
                            ?.let { size ->
                                dlvProgress.size = size.toFloat()
                            }
                    dlvProgress.color = progressOption.daisyColor
                }
            }
            else -> {
                throw IllegalArgumentException("progressStyle params error")
            }
        }

    }

    private fun applyIconConfigure(ivIcon: ImageView?) {
        ivIcon?.visibility = if (mOption.isShowReloadIcon) View.VISIBLE else View.GONE
        ivIcon?.let {
            mOption.reloadDrawable?.let { drawable ->
                it.setImageDrawable(drawable)
            }
            mOption.reloadDrawableResId.takeIf { it != 0 }?.let { id ->
                it.setImageResource(id)
            }
        }
    }

    private fun applyTextConfigure(tvContent: TextView?, tvReload: TextView?) {
        tvContent?.visibility = if (mOption.isShowContentText) View.VISIBLE else View.GONE
        if (mOption.isShowContentText) {
            initTextButton(tvContent, mOption.contentOption)
        }
        tvReload?.visibility = if (mOption.isShowReloadText) View.VISIBLE else View.GONE
        if (mOption.isShowReloadText) {
            initTextButton(tvReload, mOption.reloadOption)
        }
    }

    private fun applyContainerConfigure(vContainer: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mOption.containerElevation != 0F) {
                vContainer?.elevation = mOption.containerElevation
            }
        }
        mOption.containerBackgroundResId.takeIf { it != 0 }?.let {
            vContainer?.setBackgroundResource(it)
        }
        mOption.containerBackground?.let {
            vContainer?.background = it
        }
    }

    /**
     * 初始化文本控件
     *
     * @param textView 文本控件
     * @param option 文本控件配置信息对象
     */
    private fun initTextButton(textView: TextView?, @Nullable option: StatusViewTextOption?) {
        textView?.let {
            option?.let { textOption ->
                if (!textOption.text.isNullOrEmpty()) {
                    it.text = textOption.text
                }
                textOption.textSize.takeIf { size ->
                    size > 0
                }?.let { size ->
                    it.textSize = size
                }
                textOption.textColor.takeIf { color ->
                    color != 0
                }?.let { color ->
                    it.setTextColor(color)
                }
                it.background?.let { drawable ->
                    it.background = drawable
                }
                textOption.backgroundResId.takeIf { id ->
                    id != 0
                }?.let { id ->
                    it.setBackgroundResource(id)
                }
            }
        }
    }
}