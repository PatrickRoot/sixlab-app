// package cn.sixlab.app.sixlabapp.ft;
//
// import android.app.Fragment;
// import android.content.Context;
// import android.content.SharedPreferences;
// import android.net.Uri;
// import android.os.Bundle;
// import android.view.LayoutInflater;
// import android.view.View;
// import android.view.ViewGroup;
// import android.widget.Button;
//
// import butterknife.BindView;
// import butterknife.ButterKnife;
// import cn.sixlab.app.sixlabapp.R;
//
// /**
//  * A simple {@link Fragment} subclass.
//  * Activities that contain this fragment must implement the
//  * {@link LatelyFragment.OnFragmentInteractionListener} interface
//  * to handle interaction events.
//  * Use the {@link LatelyFragment#newInstance} factory method to
//  * create an instance of this fragment.
//  */
// public class LatelyFragment extends Fragment {
//     // TODO: Rename parameter arguments, choose names that match
//     // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//     private static final String ARG_PARAM1 = "param1";
//     private static final String ARG_PARAM2 = "param2";
//
//     // TODO: Rename and change types of parameters
//     private String mParam1;
//     private String mParam2;
//
//     @BindView(R.id.btn) Button btn;
//     @BindView(R.id.btn06) Button btn06;
//     @BindView(R.id.btn07) Button btn07;
//     @BindView(R.id.btn08) Button btn08;
//     @BindView(R.id.btn09) Button btn09;
//     @BindView(R.id.btn10) Button btn10;
//     @BindView(R.id.btn11) Button btn11;
//     @BindView(R.id.btn13) Button btn13;
//     @BindView(R.id.btn14) Button btn14;
//     @BindView(R.id.btn15) Button btn15;
//     @BindView(R.id.btn16) Button btn16;
//     @BindView(R.id.btn17) Button btn17;
//     @BindView(R.id.btn18) Button btn18;
//     @BindView(R.id.btn19) Button btn19;
//
//     private OnFragmentInteractionListener mListener;
//
//     public LatelyFragment() {
//         // Required empty public constructor
//     }
//
//     /**
//      * Use this factory method to create a new instance of
//      * this fragment using the provided parameters.
//      *
//      * @param param1 Parameter 1.
//      * @param param2 Parameter 2.
//      * @return A new instance of fragment LatelyFragment.
//      */
//     // TODO: Rename and change types and number of parameters
//     public static LatelyFragment newInstance(String param1, String param2) {
//         LatelyFragment fragment = new LatelyFragment();
//         Bundle args = new Bundle();
//         args.putString(ARG_PARAM1, param1);
//         args.putString(ARG_PARAM2, param2);
//         fragment.setArguments(args);
//         return fragment;
//     }
//
//     @Override
//     public void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         if (getArguments() != null) {
//             mParam1 = getArguments().getString(ARG_PARAM1);
//             mParam2 = getArguments().getString(ARG_PARAM2);
//         }
//     }
//
//     @Override
//     public View onCreateView(LayoutInflater inflater, final ViewGroup container,
//                              Bundle savedInstanceState) {
//         // Inflate the layout for this fragment
//         View view = inflater.inflate(R.layout.ft_lately, container, false);
//         ButterKnife.bind(this,view);
//
//         btn.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.AppTheme);}});
//         btn06.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t06);}});
//         btn07.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t07);}});
//         btn08.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t08);}});
//         btn09.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t09);}});
//         btn10.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t10);}});
//         btn11.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t11);}});
//         btn13.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t13);}});
//         btn14.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t14);}});
//         btn15.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t15);}});
//         btn16.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t16);}});
//         btn17.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t17);}});
//         btn18.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t18);}});
//         btn19.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {doSth(R.style.t19);}});
//
//         return view;
//     }
//
//     private void doSth(int appTheme) {
//         SharedPreferences userSettings = getActivity().getSharedPreferences("setting", 0);
//         SharedPreferences.Editor editor = userSettings.edit();
//         editor.putInt("theme",appTheme);
//         editor.commit();
//     }
//
//     // TODO: Rename method, update argument and hook method into UI event
//     public void onButtonPressed(Uri uri) {
//         if (mListener != null) {
//             mListener.onFragmentInteraction(uri);
//         }
//     }
//
//     @Override
//     public void onAttach(Context context) {
//         super.onAttach(context);
//         // if (context instanceof OnFragmentInteractionListener) {
//         //     mListener = (OnFragmentInteractionListener) context;
//         // } else {
//         //     throw new RuntimeException(context.toString()
//         //             + " must implement OnFragmentInteractionListener");
//         // }
//     }
//
//     @Override
//     public void onDetach() {
//         super.onDetach();
//         mListener = null;
//     }
//
//     /**
//      * This interface must be implemented by activities that contain this
//      * fragment to allow an interaction in this fragment to be communicated
//      * to the activity and potentially other fragments contained in that
//      * activity.
//      * <p>
//      * See the Android Training lesson <a href=
//      * "http://developer.android.com/training/basics/fragments/communicating.html"
//      * >Communicating with Other Fragments</a> for more information.
//      */
//     public interface OnFragmentInteractionListener {
//         // TODO: Update argument type and name
//         void onFragmentInteraction(Uri uri);
//     }
// }
