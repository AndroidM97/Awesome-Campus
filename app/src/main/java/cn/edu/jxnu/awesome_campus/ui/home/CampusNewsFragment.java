package cn.edu.jxnu.awesome_campus.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.edu.jxnu.awesome_campus.InitApp;
import cn.edu.jxnu.awesome_campus.R;
import cn.edu.jxnu.awesome_campus.event.EVENT;
import cn.edu.jxnu.awesome_campus.event.EventModel;
import cn.edu.jxnu.awesome_campus.model.home.CampusNewsModel;
import cn.edu.jxnu.awesome_campus.support.adapter.home.CampusNewsAdapter;
import cn.edu.jxnu.awesome_campus.ui.base.BaseListFragment;

/**
 * Created by MummyDing on 16-1-31.
 * GitHub: https://github.com/MummyDing
 * Blog: http://blog.csdn.net/mummyding
 */
public class CampusNewsFragment extends BaseListFragment {

    private CampusNewsModel model;
    @Override
    public String getTitle() {
        return InitApp.AppContext.getString(R.string.compus_news);
    }

    @Override
    public void cardViewTransition() {

    }

    @Override
    public void bindAdapter() {
        model = new CampusNewsModel();
        adapter = new CampusNewsAdapter(getContext(),model);
        recyclerView.setAdapter(adapter);
        model.loadFromNet();
        displayLoading();
    }

    @Override
    public void addHeader() {

    }

    @Override
    public void initView() {
    }

    @Override
    protected void onNetworkBtnClick() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Log.d("register","create");
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    @Override
    public void onEventComing(EventModel eventModel) {
        super.onEventComing(eventModel);
        List list = eventModel.getDataList();
        switch (eventModel.getEventCode()){
            case EVENT.CAMPUS_NEWS_REFRESH_SUCCESS:
                adapter.newList(list);
                hideLoading();
                Log.d("fragment","success");
                break;
            case EVENT.CAMPUS_NEWS_REFRESH_FAILURE:
                Log.d("fragment","fail");
                break;
        }
    }

}
