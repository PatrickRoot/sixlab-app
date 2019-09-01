// package cn.sixlab.app.sixlabapp.tools;
//
// import android.app.DatePickerDialog;
// import android.os.Bundle;
// import android.support.v7.app.AppCompatActivity;
// import android.util.Log;
// import android.view.KeyEvent;
// import android.view.LayoutInflater;
// import android.view.inputmethod.EditorInfo;
// import android.widget.EditText;
// import android.widget.ImageView;
// import android.widget.LinearLayout;
// import android.widget.TextView;
//
// import com.daimajia.swipe.SwipeLayout;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.sdsmdg.tastytoast.TastyToast;
//
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.Map;
//
// import butterknife.BindView;
// import butterknife.ButterKnife;
// import butterknife.OnClick;
// import butterknife.OnEditorAction;
// import cn.sixlab.app.sixlabapp.R;
// import cn.sixlab.app.sixlabapp.bean.SixlabShow;
// import cn.sixlab.app.sixlabapp.http.SixlabService;
// import cn.sixlab.app.sixlabapp.util.HttpUtil;
// import okhttp3.ResponseBody;
// import retrofit2.Call;
// import retrofit2.Callback;
// import retrofit2.Response;
// import retrofit2.Retrofit;
// import retrofit2.converter.jackson.JacksonConverterFactory;
//
// public class ShowActivity extends AppCompatActivity {
//
//     @BindView(R.id.mainContent) LinearLayout mainContent;
//     @BindView(R.id.searchInput) EditText searchInput;
//     @BindView(R.id.addShowBtn) TextView addShowBtn;
//     private boolean searching = false;
//
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_show);
//
//         ButterKnife.bind(this);
//
//         searchShow();
//     }
//
//     public void searchShow(){
//         if(!searching){
//             searching = true;
//
//             mainContent.removeAllViews();
//             String showText = searchInput.getText().toString();
//
//             // final SweetAlertDialog pDialog = new SweetAlertDialog(ShowActivity.this,
//             //         SweetAlertDialog.PROGRESS_TYPE);
//             // pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
//             // pDialog.setTitleText("Loading");
//             // pDialog.setCancelable(false);
//             // pDialog.show();
//
//             Retrofit retrofit = new Retrofit.Builder()
//                     .baseUrl(HttpUtil.getSixlab())
//                     .addConverterFactory(JacksonConverterFactory.create())
//                     .client(HttpUtil.apiToken())
//                     .build();
//
//             final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建
//
//             Call<Map> call = service.queryShow(showText.toString());//获取一个Call,才可以执行请求
//
//             call.enqueue(new Callback<Map>() {
//                 @Override
//                 public void onResponse(Call<Map> call, Response<Map> response) {
//                     // pDialog.hide();
//                     Log.e("sixlab", "isSuccessful@:" + response.isSuccessful());
//                     if(response.isSuccessful()){
//                         Map map = response.body();
//                         Log.e("sixlab", "response@:" + map);
//
//                         int num = (int) map.get("num");
//
//
//                         if(num>0){
//                             //if(jsonObject.getBoolean("success") && jsonObject.getIntValue("num")>0){
//                             LayoutInflater inflater = LayoutInflater.from(ShowActivity.this);
//
//                             ArrayList shows = (ArrayList) map.get("shows");
//
//                             for (Object object : shows) {
//                                 final SixlabShow show = new ObjectMapper().convertValue(object,SixlabShow.class);
//                                 SwipeLayout view = (SwipeLayout) inflater.inflate(R.layout.show_search_result, null);
//
//                                 TextView resultName = (TextView) view.findViewById(R.id.result_name);
//                                 // resultName.setOnClickListener(v -> viewShow(show));
//                                 // resultName.setText(Html.fromHtml("<u>" + show.getShowName()+ "</u>"));
//                                 resultName.setText(show.getShowName());
//
//                                 TextView resultSeason = (TextView) view.findViewById(R.id.result_season);
//                                 resultSeason.setText("S"+show.getShowSeason());
//                                 resultSeason.setOnClickListener(v->addSeason(show));
//
//                                 TextView resultEpisode = (TextView) view.findViewById(R.id.result_episode);
//                                 resultEpisode.setText("E"+show.getShowEpisode());
//                                 resultEpisode.setOnClickListener(v->addEpisode(show));
//
//                                 ImageView changeBtn = (ImageView) view.findViewById(R.id.btn_change);
//                                 if("20".equals(show.getViewStatus())){
//                                     changeBtn.setImageResource(R.mipmap.btn_pause);
//                                 }else{
//                                     changeBtn.setImageResource(R.mipmap.btn_play);
//                                 }
//
//                                 view.addSwipeListener(new SwipeLayout.SwipeListener() {
//                                     @Override
//                                     public void onStartOpen(SwipeLayout layout) {
//
//                                     }
//
//                                     @Override
//                                     public void onOpen(SwipeLayout layout) {
//                                         changeStatus(show.getId(),show.getViewStatus());
//                                     }
//
//                                     @Override
//                                     public void onStartClose(SwipeLayout layout) {
//
//                                     }
//
//                                     @Override
//                                     public void onClose(SwipeLayout layout) {
//
//                                     }
//
//                                     @Override
//                                     public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
//
//                                     }
//
//                                     @Override
//                                     public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
//
//                                     }
//                                 });
//
//                                 mainContent.addView(view);
//                             }
//                         }else{
//                             TastyToast.makeText(ShowActivity.this, "未查询到结果",
//                                     TastyToast.LENGTH_LONG, TastyToast.WARNING);
//                             // new SweetAlertDialog(ShowActivity.this)
//                             //         .setTitleText("提示")
//                             //         .setContentText("未查询到结果")
//                             //         .show();
//                         }
//                     }else{
//                         int status = response.code();
//                         ResponseBody responseBody = response.errorBody();
//                         Log.e("sixlab", "status@:"+status);
//                         Log.e("sixlab", "error@:"+responseBody);
//                     }
//                     searching = false;
//                 }
//
//                 @Override
//                 public void onFailure(Call<Map> call, Throwable t) {
//                     // pDialog.hide();
//                     Log.e("sixlab", "error@:" + t.getMessage());
//                     searching = false;
//                 }
//             });
//         }
//     }
//
//     private void changeStatus(Integer id, String viewStatus) {
//         // final SweetAlertDialog pDialog = new SweetAlertDialog(ShowActivity.this,
//         //         SweetAlertDialog.PROGRESS_TYPE);
//         // pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
//         // pDialog.setTitleText("更改状态中");
//         // pDialog.setCancelable(false);
//         // pDialog.show();
//
//         Retrofit retrofit = new Retrofit.Builder()
//                 .baseUrl(HttpUtil.getSixlab())
//                 .addConverterFactory(JacksonConverterFactory.create())
//                 .client(HttpUtil.apiToken())
//                 .build();
//
//         final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建
//
//         String status = "20";
//         if("20".equals(viewStatus)){
//             status = "30";
//         }
//         Call<Map> call = service.changeViewStatus(id,status);//获取一个Call,才可以执行请求
//         call.enqueue(new Callback<Map>() {
//             @Override
//             public void onResponse(Call<Map> call, Response<Map> response) {
//                 // pDialog.hide();
//                 Log.e("sixlab", "isSuccessful@:" + response.isSuccessful());
//                 if(response.isSuccessful()){
//                     Map map = response.body();
//                     Log.e("sixlab", "response@:" + map);
//                     searchShow();
//                 }else{
//                     int status = response.code();
//                     ResponseBody responseBody = response.errorBody();
//                     Log.e("sixlab", "status@:"+status);
//                     Log.e("sixlab", "error@:"+responseBody);
//                 }
//             }
//
//             @Override
//             public void onFailure(Call<Map> call, Throwable t) {
//                 // pDialog.hide();
//                 Log.e("sixlab", "error@:" + t.getMessage());
//             }
//         });
//     }
//
//     private void addSeason(SixlabShow show) {
//         // final SweetAlertDialog pDialog = new SweetAlertDialog(ShowActivity.this,
//         //         SweetAlertDialog.PROGRESS_TYPE);
//         // pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
//         // pDialog.setTitleText("Loading");
//         // pDialog.setCancelable(false);
//         // pDialog.show();
//
//         Retrofit retrofit = new Retrofit.Builder()
//                 .baseUrl(HttpUtil.getSixlab())
//                 .addConverterFactory(JacksonConverterFactory.create())
//                 .client(HttpUtil.apiToken())
//                 .build();//在这里可以添加 Gson转换器等;
//
//         final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建
//
//         Call<Map> call = service.addSeason(show.getId());//获取一个Call,才可以执行请求
//
//         call.enqueue(new Callback<Map>() {
//             @Override
//             public void onResponse(Call<Map> call, Response<Map> response) {
//                 // pDialog.hide();
//                 Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
//                 if(response.isSuccessful()){
//                     Map map = response.body();
//                     Log.e("sixlab", "response:" + map);
//                     //                    if(Map.getBoolean("success")){
//                     searchShow();
//                     //                    }else{
//                     //                        new SweetAlertDialog(getContext())
//                     //                                .setTitleText("提示")
//                     //                                .setContentText("服务器处理异常")
//                     //                                .show();
//                     //                    }
//                 }else{
//                     int status = response.code();
//                     ResponseBody responseBody = response.errorBody();
//                     Log.e("sixlab", "status:"+status);
//                     Log.e("sixlab", "error:"+responseBody);
//                 }
//             }
//
//             @Override
//             public void onFailure(Call<Map> call, Throwable t) {
//                 // pDialog.hide();
//                 Log.e("sixlab", "error" + t.getMessage());
//             }
//         });
//     }
//
//     private void addEpisode(SixlabShow show) {
//         // final SweetAlertDialog pDialog = new SweetAlertDialog(ShowActivity.this,
//         //         SweetAlertDialog.PROGRESS_TYPE);
//         // pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
//         // pDialog.setTitleText("Loading");
//         // pDialog.setCancelable(false);
//         // pDialog.show();
//
//         Retrofit retrofit = new Retrofit.Builder()
//                 .baseUrl(HttpUtil.getSixlab())
//                 .addConverterFactory(JacksonConverterFactory.create())
//                 .client(HttpUtil.apiToken())
//                 .build();//在这里可以添加 Gson转换器等;
//
//         final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建
//
//         Call<Map> call = service.addEpisode(show.getId());//获取一个Call,才可以执行请求
//
//         call.enqueue(new Callback<Map>() {
//             @Override
//             public void onResponse(Call<Map> call, Response<Map> response) {
//                 // pDialog.hide();
//                 Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
//                 if(response.isSuccessful()){
//                     Map map = response.body();
//                     Log.e("sixlab", "response:" + map);
//                     searchShow();
//                 }else{
//                     int status = response.code();
//                     ResponseBody responseBody = response.errorBody();
//                     Log.e("sixlab", "status:"+status);
//                     Log.e("sixlab", "error:"+responseBody);
//                 }
//             }
//
//             @Override
//             public void onFailure(Call<Map> call, Throwable t) {
//                 // pDialog.hide();
//                 Log.e("sixlab", "error" + t.getMessage());
//             }
//         });
//     }
//
//     @OnClick(R.id.changeStatus)
//     public void changeStatus () {
//         Calendar cal = Calendar.getInstance();
//         DatePickerDialog datePickerDialog = new DatePickerDialog(ShowActivity.this,
//                 (view, year, month, day) -> {
//
//                 }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
//         datePickerDialog.show();
//     }
//
//     @OnEditorAction(R.id.searchInput)
//     public boolean searchInput (TextView v, int actionId, KeyEvent event) {
//
//         if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
//             searchShow();
//             return true;
//         }
//         return false;
//     }
//
//     @OnClick(R.id.searchBtn)
//     public void searchBtn () {
//         searchShow();
//     }
//
//     @OnClick(R.id.addShowLink)
//     public void addShowLink () {
//         Calendar cal = Calendar.getInstance();
//         DatePickerDialog datePickerDialog = new DatePickerDialog(ShowActivity.this,
//                 (view, year, month, day) -> {
//
//                 }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
//         datePickerDialog.show();
//     }
// }
