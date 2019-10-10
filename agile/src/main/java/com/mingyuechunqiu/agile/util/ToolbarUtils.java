package com.mingyuechunqiu.agile.util;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import static com.mingyuechunqiu.agile.constants.CommonConstants.NO_RESOURCE_ID;

/**
 * <pre>
 *     author : xyj
 *     e-mail : xiyujieit@163.com
 *     time   : 2018/10/19
 *     desc   : 活动条工具类
 *     version: 1.0
 * </pre>
 */
public final class ToolbarUtils {

    /**
     * 初始化活动条
     *
     * @param toolbar     工具条
     * @param actionBar   活动条
     * @param toolbarBean 工具条信息对象
     */
    public static void initToolbar(@Nullable Toolbar toolbar, @Nullable ActionBar actionBar, @Nullable ToolbarBean toolbarBean) {
        if (toolbar == null || toolbarBean == null) {
            return;
        }
        if (toolbarBean.getNavigationIcon() != null) {
            toolbar.setNavigationIcon(toolbarBean.getNavigationIcon());
        }
        if (toolbarBean.getNavigationIconResId() != NO_RESOURCE_ID) {
            toolbar.setNavigationIcon(toolbarBean.getNavigationIconResId());
        }
        if (toolbarBean.getLogoResId() != NO_RESOURCE_ID) {
            toolbar.setLogo(toolbarBean.getLogoResId());
        }
        if (toolbarBean.getLogoDrawable() != null) {
            toolbar.setLogo(toolbarBean.getLogoDrawable());
        }
        if (toolbarBean.getOnIconClickListener() != null) {
            toolbar.setNavigationOnClickListener(toolbarBean.getOnIconClickListener());
        }
        //判断是否显示toolbar自身的标题
        boolean isHasCustomTitle = false;
        if (!TextUtils.isEmpty(toolbarBean.getTitle())) {
            //因为在onCreate()中修改title的值，都会被重置成android:label的值
            toolbar.setTitle(toolbarBean.getTitle());
            if (toolbarBean.getTitleTextAppearance() == NO_RESOURCE_ID) {

                if (toolbarBean.getTitleColor() != NO_RESOURCE_ID) {
                    toolbar.setTitleTextColor(toolbarBean.getTitleColor());
                }

            } else {
                toolbar.setTitleTextAppearance(toolbar.getContext(), toolbarBean.getTitleTextAppearance());
            }
            isHasCustomTitle = true;
        }
        if (!TextUtils.isEmpty(toolbarBean.getSubTitle())) {
            toolbar.setSubtitle(toolbarBean.getSubTitle());
            if (toolbarBean.getSubTitleTextAppearance() == NO_RESOURCE_ID) {

                if (toolbarBean.getSubTitleColor() != NO_RESOURCE_ID) {
                    toolbar.setSubtitleTextColor(toolbarBean.getSubTitleColor());
                }

            } else {
                toolbar.setSubtitleTextAppearance(toolbar.getContext(), toolbarBean.getSubTitleTextAppearance());
            }
            isHasCustomTitle = true;
        }

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(toolbarBean.isEnableDisplayHomeAsUp());
            //禁止活动条自身的标题显示
            actionBar.setDisplayShowTitleEnabled(!toolbarBean.isHideDisplayTitle() || isHasCustomTitle);
        }

        if (toolbarBean.getOverflowIcon() != null) {
            toolbar.setOverflowIcon(toolbarBean.getOverflowIcon());
        }
        if (toolbarBean.isImmerse()) {
            //因为沉侵式布局会让活动条侵入到状态栏中，为了不影响活动条显示内容，
            //让活动条高度增加并且内容下移
            int statusBarHeight = ScreenUtils.getStatusBarHeight(toolbar.getContext());
            toolbar.getLayoutParams().height = toolbar.getLayoutParams().height + statusBarHeight;
            toolbar.setPadding(0, statusBarHeight, 0, 0);
        }
        if (toolbarBean.getOnMenuItemClickListener() != null) {
            toolbar.setOnMenuItemClickListener(toolbarBean.getOnMenuItemClickListener());
        }
    }

    public static class ToolbarBean {

        private Builder mBuilder;

        public ToolbarBean() {
            this(new Builder());
        }

        public ToolbarBean(@NonNull Builder builder) {
            mBuilder = builder;
        }

        public int getNavigationIconResId() {
            return mBuilder.navigationIconResId;
        }

        public void setNavigationIconResId(@DrawableRes int navigationIconResId) {
            mBuilder.navigationIconResId = navigationIconResId;
        }

        public Drawable getNavigationIcon() {
            return mBuilder.navigationIcon;
        }

        public void setNavigationIcon(Drawable navigationIcon) {
            mBuilder.navigationIcon = navigationIcon;
        }

        public boolean isEnableDisplayHomeAsUp() {
            return mBuilder.enableDisplayHomeAsUp;
        }

        public void setEnableDisplayHomeAsUp(boolean enableDisplayHomeAsUp) {
            mBuilder.enableDisplayHomeAsUp = enableDisplayHomeAsUp;
        }

        public int getLogoResId() {
            return mBuilder.logoResId;
        }

        public void setLogoResId(@DrawableRes int logoResId) {
            mBuilder.logoResId = logoResId;
        }

        public Drawable getLogoDrawable() {
            return mBuilder.logoDrawable;
        }

        public void setLogoDrawable(Drawable logoDrawable) {
            mBuilder.logoDrawable = logoDrawable;
        }

        public View.OnClickListener getOnIconClickListener() {
            return mBuilder.onIconClickListener;
        }

        public void setOnIconClickListener(@Nullable View.OnClickListener onIconClickListener) {
            mBuilder.onIconClickListener = onIconClickListener;
        }

        public boolean isHideDisplayTitle() {
            return mBuilder.hideDisplayTitle;
        }

        public void setHideDisplayTitle(boolean hideDisplayTitle) {
            mBuilder.hideDisplayTitle = hideDisplayTitle;
        }

        public String getTitle() {
            return mBuilder.title;
        }

        public void setTitle(@Nullable String title) {
            mBuilder.title = title;
        }

        public int getTitleColor() {
            return mBuilder.titleColor;
        }

        public void setTitleColor(@ColorInt int titleColor) {
            mBuilder.titleColor = titleColor;
        }

        public String getSubTitle() {
            return mBuilder.subTitle;
        }

        public void setSubTitle(@Nullable String subTitle) {
            mBuilder.subTitle = subTitle;
        }

        public int getSubTitleColor() {
            return mBuilder.subTitleColor;
        }

        public void setSubTitleColor(@ColorInt int subTitleColor) {
            mBuilder.subTitleColor = subTitleColor;
        }

        public int getTitleTextAppearance() {
            return mBuilder.titleTextAppearance;
        }

        public void setTitleTextAppearance(@StyleRes int titleTextAppearance) {
            mBuilder.titleTextAppearance = titleTextAppearance;
        }

        public int getSubTitleTextAppearance() {
            return mBuilder.subTitleTextAppearance;
        }

        public void setSubTitleTextAppearance(@StyleRes int subTitleTextAppearance) {
            mBuilder.subTitleTextAppearance = subTitleTextAppearance;
        }

        public boolean isImmerse() {
            return mBuilder.immerse;
        }

        public void setImmerse(boolean immerse) {
            mBuilder.immerse = immerse;
        }

        public Toolbar.OnMenuItemClickListener getOnMenuItemClickListener() {
            return mBuilder.onMenuItemClickListener;
        }

        public void setOnMenuItemClickListener(@Nullable Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
            mBuilder.onMenuItemClickListener = onMenuItemClickListener;
        }

        public int getMenuResId() {
            return mBuilder.menuResId;
        }

        public void setMenuResId(@MenuRes int menuResId) {
            mBuilder.menuResId = menuResId;
        }

        public boolean isClearActivityMenu() {
            return mBuilder.clearActivityMenu;
        }

        public void setClearActivityMenu(boolean clearActivityMenu) {
            mBuilder.clearActivityMenu = clearActivityMenu;
        }

        public Drawable getOverflowIcon() {
            return mBuilder.overflowIcon;
        }

        public void setOverflowIcon(@Nullable Drawable overflowIcon) {
            mBuilder.overflowIcon = overflowIcon;
        }

        //链式调用
        public static class Builder {

            @DrawableRes
            private int navigationIconResId;//左侧导航图标资源ID

            private Drawable navigationIcon;//左侧导航图标

            private boolean enableDisplayHomeAsUp;//是否启用默认系统返回键

            @DrawableRes
            private int logoResId;//左侧logo图标资源ID

            private Drawable logoDrawable;//左侧logo图片

            private View.OnClickListener onIconClickListener;//图标点击事件

            private boolean hideDisplayTitle;//隐藏系统默认标题

            private String title;//标题

            @ColorInt
            private int titleColor;//标题文本颜色

            private String subTitle;//副标题

            @ColorInt
            private int subTitleColor;//副标题文本颜色

            private @StyleRes
            int titleTextAppearance;//标题文本样式

            private @StyleRes
            int subTitleTextAppearance;//副标题文本样式

            private boolean immerse;//标记是否是沉浸式

            private Toolbar.OnMenuItemClickListener onMenuItemClickListener;//菜单监听器

            @MenuRes
            private int menuResId;//菜单资源ID

            private boolean clearActivityMenu;//用于fragment是否清楚activity的toolbar菜单资源

            private Drawable overflowIcon;//溢出图标

            public Builder() {
                hideDisplayTitle = true;
            }

            public ToolbarBean build() {
                return new ToolbarBean(this);
            }

            public int getNavigationIconResId() {
                return this.navigationIconResId;
            }

            public Builder setNavigationIconResId(@DrawableRes int navigationIconResId) {
                this.navigationIconResId = navigationIconResId;
                return this;
            }

            public Drawable getNavigationIcon() {
                return this.navigationIcon;
            }

            public Builder setNavigationIcon(Drawable navigationIcon) {
                this.navigationIcon = navigationIcon;
                return this;
            }

            public boolean isEnableDisplayHomeAsUp() {
                return enableDisplayHomeAsUp;
            }

            public Builder setEnableDisplayHomeAsUp(boolean enableDisplayHomeAsUp) {
                this.enableDisplayHomeAsUp = enableDisplayHomeAsUp;
                return this;
            }

            public int getLogoResId() {
                return this.logoResId;
            }

            public Builder setLogoResId(@DrawableRes int logoResId) {
                this.logoResId = logoResId;
                return this;
            }

            public Drawable getLogoDrawable() {
                return this.logoDrawable;
            }

            public Builder setLogoDrawable(Drawable logoDrawable) {
                this.logoDrawable = logoDrawable;
                return this;
            }

            public View.OnClickListener getOnIconClickListener() {
                return this.onIconClickListener;
            }

            public Builder setOnIconClickListener(@Nullable View.OnClickListener iconClickListener) {
                this.onIconClickListener = iconClickListener;
                return this;
            }

            public boolean isHideDisplayTitle() {
                return hideDisplayTitle;
            }

            public Builder setHideDisplayTitle(boolean hideDisplayTitle) {
                this.hideDisplayTitle = hideDisplayTitle;
                return this;
            }

            public String getTitle() {
                return this.title;
            }

            public Builder setTitle(@Nullable String title) {
                this.title = title;
                return this;
            }

            public int getTitleColor() {
                return this.titleColor;
            }

            public Builder setTitleColor(@ColorInt int titleColor) {
                this.titleColor = titleColor;
                return this;
            }

            public String getSubTitle() {
                return this.subTitle;
            }

            public Builder setSubTitle(@Nullable String subTitle) {
                this.subTitle = subTitle;
                return this;
            }

            public int getSubTitleColor() {
                return this.subTitleColor;
            }

            public Builder setSubTitleColor(@ColorInt int subTitleColor) {
                this.subTitleColor = subTitleColor;
                return this;
            }

            public int getTitleTextAppearance() {
                return this.titleTextAppearance;
            }

            public Builder setTitleTextAppearance(@StyleRes int titleTextAppearance) {
                this.titleTextAppearance = titleTextAppearance;
                return this;
            }

            public int getSubTitleTextAppearance() {
                return this.subTitleTextAppearance;
            }

            public Builder setSubTitleTextAppearance(@StyleRes int subTitleTextAppearance) {
                this.subTitleTextAppearance = subTitleTextAppearance;
                return this;
            }

            public boolean isImmerse() {
                return this.immerse;
            }

            public Builder setImmerse(boolean immerse) {
                this.immerse = immerse;
                return this;
            }

            public Toolbar.OnMenuItemClickListener getOnMenuItemClickListener() {
                return this.onMenuItemClickListener;
            }

            public Builder setOnMenuItemClickListener(@Nullable Toolbar.OnMenuItemClickListener listener) {
                this.onMenuItemClickListener = listener;
                return this;
            }

            public int getMenuResId() {
                return this.menuResId;
            }

            public Builder setMenuResId(@MenuRes int menuResId) {
                this.menuResId = menuResId;
                return this;
            }

            public boolean isClearActivityMenu() {
                return this.clearActivityMenu;
            }

            public Builder setClearActivityMenu(boolean clearActivityMenu) {
                this.clearActivityMenu = clearActivityMenu;
                return this;
            }

            public Drawable getOverflowIcon() {
                return overflowIcon;
            }

            public Builder setOverflowIcon(@Nullable Drawable overflowIcon) {
                this.overflowIcon = overflowIcon;
                return this;
            }
        }
    }
}
