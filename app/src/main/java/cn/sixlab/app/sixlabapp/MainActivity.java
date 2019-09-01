// package cn.sixlab.app.sixlabapp;
//
// import android.app.Fragment;
// import android.app.FragmentTransaction;
// import android.content.Context;
// import android.content.res.Resources;
// import android.net.Uri;
// import android.os.Bundle;
// import android.support.design.widget.BottomNavigationView;
// import android.support.v7.app.AppCompatActivity;
// import android.view.MenuItem;
//
// import butterknife.BindView;
// import butterknife.ButterKnife;
// import cn.sixlab.app.sixlabapp.ft.HomeFragment;
// import cn.sixlab.app.sixlabapp.ft.SearchFragment;
// import cn.sixlab.app.sixlabapp.ft.WatchedFragment;
// import cn.sixlab.app.sixlabapp.ft.WatchingFragment;
//
// public class MainActivity extends AppCompatActivity implements FragmentInteractionListener{
//
//     public Context context;
//     public Integer preItemId;
//     public Fragment preFt;
//     public Resources.Theme theme;
//     public int themeId;
//
//     @BindView(R.id.navigation)
//     BottomNavigationView navigation;
//
//     private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//             = item -> {
//                 initView(item);
//                 return true;
//             };
//
//     private void initView(MenuItem item) {
//         int itemId = item.getItemId();
//         if(preItemId !=null && itemId == preItemId){
//             return;
//         }
//         FragmentTransaction ft = getFragmentManager().beginTransaction();
//         Class clz;
//         switch (itemId) {
//             case R.id.navigation_watched:
//                 clz = WatchedFragment.class;
//                 break;
//             case R.id.navigation_add:
//                 clz = HomeFragment.class;
//                 break;
//             case R.id.navigation_watching:
//                 clz = WatchingFragment.class;
//                 break;
//             case R.id.navigation_search:
//             default:
//                 clz = SearchFragment.class;
//         }
//
//         if(preFt !=null){
//             ft.detach(preFt);
//         }
//
//         Bundle bundle = new Bundle();
//         bundle.putInt("args", themeId);
//
//         String name = clz.getName();
//         Fragment fragment = Fragment.instantiate(context, name, bundle);
//         ft.add(R.id.ftContainer, fragment, name);
//         preFt = fragment;
//         preItemId = itemId;
//         ft.commit();
//     }
//
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         // SharedPreferences userSettings= getSharedPreferences("setting", 0);
//         // themeId = userSettings.getInt("theme", 0);
//         // if(themeId!=0){
//         //     setTheme(themeId);
//         // }
//
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_main);
//         context = this;
//
//         ButterKnife.bind(this);
//
//         navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//         initView(navigation.getMenu().getItem(0));
//         this.theme = getTheme();
//     }
//
//     @Override
//     public void onFragmentInteraction(Uri uri) {
//         System.out.println(uri);
//     }
// }
//
// // mTextMessage.setText(R.string.title_dashboard);
// //
// // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
// //         LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
// //
// // LayoutInflater inflater = LayoutInflater.from(context);
// // View view = inflater.inflate(R.layout.fragment_blank, null);
// // view.setLayoutParams(lp);
// //
// // mainContent.removeAllViews();
// // mainContent.addView(view);
// //
// // Fragment fragment = new BlankFragment();
// // FragmentTransaction ft = getFragmentManager().beginTransaction();
// // ft.replace(R.id.mainContent, fragment);
// // ft.commit();