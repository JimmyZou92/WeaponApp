package com.weapon.joker.app.message;

import android.app.Activity;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import com.weapon.joker.lib.mvvm.common.PublicActivity;
import com.weapon.joker.lib.net.data.PushNewsData;
import com.weapon.joker.lib.net.model.PushNewsModel;

/**
 * MessageViewModel 消息的VM
 * author:张冠之
 * time: 2017/10/12 下午1:45
 * e-mail: guanzhi.zhang@sojex.cn
 */

public class MessageViewModel extends MessageContact.ViewModel {

    /**
     * 公告通知数量
     */
    @Bindable
    public int postNum;
    /**
     * 公告红点是否可见
     */
    @Bindable
    public int postRedVisible;
    /**
     * 官方服务红点是否可见
     */
    @Bindable
    public int serviceRedVisible;
    /**
     * 公告内容
     */
    @Bindable
    public String postContent;

    public void setPostNum(int postNum) {
        this.postNum = postNum;
        notifyPropertyChanged(com.weapon.joker.app.message.BR.postNum);
    }

    public void setPostRedVisible(int postRedVisible) {
        this.postRedVisible = postRedVisible;
        notifyPropertyChanged(com.weapon.joker.app.message.BR.postRedVisible);
    }

    public void setServiceRedVisible(int serviceRedVisible) {
        this.serviceRedVisible = serviceRedVisible;
        notifyPropertyChanged(com.weapon.joker.app.message.BR.serviceRedVisible);
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    /**
     * 获取公告消息
     */
    @Override
    public void getPostNews() {
        PushNewsModel model = PushNewsData.getInstance().getPushNewsData(getContext().getApplicationContext());
        if (model == null || model.data == null || model.data.size() <= 0) {
            setPostRedVisible(View.GONE);
            setServiceRedVisible(View.GONE);
            setPostContent("暂无公告");
        } else {
            setPostNum(model.data.size());
            setPostRedVisible(View.VISIBLE);
            setPostContent(model.data.get(0).content);
        }
    }

    /**
     * 公告点击事件处理
     *
     * @param view
     */
    public void onPostClick(View view) {
        if (postRedVisible == View.VISIBLE) {
            PublicActivity.startActivity((Activity) getContext(), "com.weapon.joker.app.message.post.PostFragment");
        }
    }

    /**
     * 官方服务点击事件处理
     *
     * @param view
     */
    public void onServiceClick(View view) {
        if (serviceRedVisible == View.VISIBLE) {
            Toast.makeText(getContext(), "进入官方服务聊天界面", Toast.LENGTH_SHORT).show();
        }
    }

}
