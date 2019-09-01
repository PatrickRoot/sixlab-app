package cn.sixlab.app.sixlabapp.ft;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import cn.sixlab.app.sixlabapp.R;
import cn.sixlab.app.sixlabapp.bean.SixlabShow;
import cn.sixlab.app.sixlabapp.http.SixlabService;
import cn.sixlab.app.sixlabapp.util.HttpUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WatchedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WatchedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WatchedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WatchedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WatchingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WatchedFragment newInstance(String param1, String param2) {
        WatchedFragment fragment = new WatchedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ft_watching, container, false);

        ButterKnife.bind(this,view);

        searchShow();

        searchInput.setHint("看过的");

        return view;
        // return inflater.inflate(R.layout.ft_watching, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // -------------------
    @BindView(R.id.mainContent)
    LinearLayout mainContent;
    @BindView(R.id.searchInput)
    EditText searchInput;
    @BindView(R.id.addShowBtn)
    TextView addShowBtn;
    private boolean searching = false;

    @OnEditorAction(R.id.searchInput)
    public boolean searchInput (TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            searchShow();
            return true;
        }
        return false;
    }

    @OnClick(R.id.searchBtn)
    public void searchBtn () {
        searchShow();
    }

    @OnClick(R.id.addShowLink)
    public void addShowLink () {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                (view, year, month, day) -> {

                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        datePickerDialog.show();
    }

    public void searchShow(){
        if(!searching){
            searching = true;

            mainContent.removeAllViews();
            String showText = searchInput.getText().toString();

            // final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(),
            //         SweetAlertDialog.PROGRESS_TYPE);
            // pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
            // pDialog.setTitleText("Loading");
            // pDialog.setCancelable(false);
            // pDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HttpUtil.getSixlab())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(HttpUtil.apiToken())
                    .build();

            final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建

            Call<Map> call = service.queryWatched(showText.toString());//获取一个Call,才可以执行请求

            call.enqueue(new Callback<Map>() {
                @Override
                public void onResponse(Call<Map> call, Response<Map> response) {
                    // // pDialog.hide();
                    Log.e("sixlab", "isSuccessful@:" + response.isSuccessful());
                    if(response.isSuccessful()){
                        Map map = response.body();
                        Log.e("sixlab", "response@:" + map);

                        int num = (int) map.get("num");


                        if(num>0){
                            //if(jsonObject.getBoolean("success") && jsonObject.getIntValue("num")>0){
                            LayoutInflater inflater = LayoutInflater.from(getActivity());

                            ArrayList shows = (ArrayList) map.get("shows");

                            for (Object object : shows) {
                                final SixlabShow show = new ObjectMapper().convertValue(object,SixlabShow.class);
                                SwipeLayout view = (SwipeLayout) inflater.inflate(R.layout.show_search_result, null);

                                TextView resultName = (TextView) view.findViewById(R.id.result_name);
                                // resultName.setOnClickListener(v -> viewShow(show));
                                // resultName.setText(Html.fromHtml("<u>" + show.getShowName()+ "</u>"));
                                resultName.setText(show.getShowName());

                                TextView resultSeason = (TextView) view.findViewById(R.id.result_season);
                                resultSeason.setText("S"+show.getShowSeason());
                                resultSeason.setOnClickListener(v->addSeason(show));

                                TextView resultEpisode = (TextView) view.findViewById(R.id.result_episode);
                                resultEpisode.setText("E"+show.getShowEpisode());
                                resultEpisode.setOnClickListener(v->addEpisode(show));

                                ImageView changeBtn = (ImageView) view.findViewById(R.id.btn_change);
                                if("20".equals(show.getViewStatus())){
                                    changeBtn.setImageResource(R.mipmap.btn_pause);
                                }else{
                                    changeBtn.setImageResource(R.mipmap.btn_play);
                                }

                                view.addSwipeListener(new SwipeLayout.SwipeListener() {
                                    @Override
                                    public void onStartOpen(SwipeLayout layout) {

                                    }

                                    @Override
                                    public void onOpen(SwipeLayout layout) {
                                        changeStatus(show.getId(),show.getViewStatus());
                                    }

                                    @Override
                                    public void onStartClose(SwipeLayout layout) {

                                    }

                                    @Override
                                    public void onClose(SwipeLayout layout) {

                                    }

                                    @Override
                                    public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

                                    }

                                    @Override
                                    public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

                                    }
                                });

                                mainContent.addView(view);
                            }
                        }else{
                            TastyToast.makeText(getContext(), "未查询到结果",
                                    TastyToast.LENGTH_LONG, TastyToast.WARNING);
                            // new SweetAlertDialog(getActivity())
                            //         .setTitleText("提示")
                            //         .setContentText("未查询到结果")
                            //         .show();
                        }
                    }else{
                        int status = response.code();
                        ResponseBody responseBody = response.errorBody();
                        Log.e("sixlab", "status@:"+status);
                        Log.e("sixlab", "error@:"+responseBody);
                    }
                    searching = false;
                }

                @Override
                public void onFailure(Call<Map> call, Throwable t) {
                    // // pDialog.hide();
                    Log.e("sixlab", "error@:" + t.getMessage());
                    searching = false;
                }
            });
        }
    }

    private void changeStatus(Integer id, String viewStatus) {
        // final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(),
        //         SweetAlertDialog.PROGRESS_TYPE);
        // pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
        // pDialog.setTitleText("更改状态中");
        // pDialog.setCancelable(false);
        // pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUtil.getSixlab())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(HttpUtil.apiToken())
                .build();

        final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建

        String status = "20";
        if("20".equals(viewStatus)){
            status = "30";
        }
        Call<Map> call = service.changeViewStatus(id,status);//获取一个Call,才可以执行请求
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                // // pDialog.hide();
                Log.e("sixlab", "isSuccessful@:" + response.isSuccessful());
                if(response.isSuccessful()){
                    Map map = response.body();
                    Log.e("sixlab", "response@:" + map);
                    searchShow();
                }else{
                    int status = response.code();
                    ResponseBody responseBody = response.errorBody();
                    Log.e("sixlab", "status@:"+status);
                    Log.e("sixlab", "error@:"+responseBody);
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                // // pDialog.hide();
                Log.e("sixlab", "error@:" + t.getMessage());
            }
        });
    }

    private void addSeason(SixlabShow show) {
        // final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(),
        //         SweetAlertDialog.PROGRESS_TYPE);
        // pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
        // pDialog.setTitleText("Loading");
        // pDialog.setCancelable(false);
        // pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUtil.getSixlab())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(HttpUtil.apiToken())
                .build();//在这里可以添加 Gson转换器等;

        final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建

        Call<Map> call = service.addSeason(show.getId());//获取一个Call,才可以执行请求

        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                // pDialog.hide();
                Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
                if(response.isSuccessful()){
                    Map map = response.body();
                    Log.e("sixlab", "response:" + map);
                    //                    if(Map.getBoolean("success")){
                    searchShow();
                    //                    }else{
                    //                        new SweetAlertDialog(getContext())
                    //                                .setTitleText("提示")
                    //                                .setContentText("服务器处理异常")
                    //                                .show();
                    //                    }
                }else{
                    int status = response.code();
                    ResponseBody responseBody = response.errorBody();
                    Log.e("sixlab", "status:"+status);
                    Log.e("sixlab", "error:"+responseBody);
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                // pDialog.hide();
                Log.e("sixlab", "error" + t.getMessage());
            }
        });
    }

    private void addEpisode(SixlabShow show) {
        // final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(),
        //         SweetAlertDialog.PROGRESS_TYPE);
        // pDialog.getProgressHelper().setBarColor(R.color.sweetAlertColor);
        // pDialog.setTitleText("Loading");
        // pDialog.setCancelable(false);
        // pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUtil.getSixlab())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(HttpUtil.apiToken())
                .build();//在这里可以添加 Gson转换器等;

        final SixlabService service = retrofit.create(SixlabService.class);//使用上面声明的接口创建

        Call<Map> call = service.addEpisode(show.getId());//获取一个Call,才可以执行请求

        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                // pDialog.hide();
                Log.e("sixlab", "isSuccessful:" + response.isSuccessful());
                if(response.isSuccessful()){
                    Map map = response.body();
                    Log.e("sixlab", "response:" + map);
                    searchShow();
                }else{
                    int status = response.code();
                    ResponseBody responseBody = response.errorBody();
                    Log.e("sixlab", "status:"+status);
                    Log.e("sixlab", "error:"+responseBody);
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                // pDialog.hide();
                Log.e("sixlab", "error" + t.getMessage());
            }
        });
    }
}
