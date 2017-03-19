package cn.sixlab.app.sixlabapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sixlab.app.sixlabapp.ft.DashbordFragment;
import cn.sixlab.app.sixlabapp.ft.HomeFragment;
import cn.sixlab.app.sixlabapp.ft.NotificationsFragment;

public class MainActivity extends AppCompatActivity {

    public Context context;
    public Integer preItemId;
    public Fragment preFt;
    public Resources.Theme theme;
    public int themeId;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.mainContent)
    ScrollView mainContent;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            initView(item);
            return true;
        }

    };

    private void initView(MenuItem item) {
        int itemId = item.getItemId();
        if(preItemId !=null && itemId == preItemId){
            return;
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Class clz;
        switch (itemId) {
            case R.id.navigation_dashboard:
                clz = DashbordFragment.class;
                break;
            case R.id.navigation_home:
                clz = HomeFragment.class;
                break;
            case R.id.navigation_notifications:
            default:
                clz = NotificationsFragment.class;
        }

        if(preFt !=null){
            ft.detach(preFt);
        }

        Bundle bundle = new Bundle();
        bundle.putInt("args", themeId);

        String name = clz.getName();
        Fragment fragment = Fragment.instantiate(context, name, bundle);
        ft.add(R.id.mainContent, fragment, name);
        preFt = fragment;
        preItemId = itemId;
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // SharedPreferences userSettings= getSharedPreferences("setting", 0);
        // themeId = userSettings.getInt("theme", 0);
        // if(themeId!=0){
        //     setTheme(themeId);
        // }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initView(navigation.getMenu().getItem(0));
        this.theme = getTheme();
    }

}

// mTextMessage.setText(R.string.title_dashboard);
//
// LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//         LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
// LayoutInflater inflater = LayoutInflater.from(context);
// View view = inflater.inflate(R.layout.fragment_blank, null);
// view.setLayoutParams(lp);
//
// mainContent.removeAllViews();
// mainContent.addView(view);
//
// Fragment fragment = new BlankFragment();
// FragmentTransaction ft = getFragmentManager().beginTransaction();
// ft.replace(R.id.mainContent, fragment);
// ft.commit();