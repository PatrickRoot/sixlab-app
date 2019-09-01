package cn.sixlab.app.sixlabapp

import cn.sixlab.app.sixlabapp.ft.AddFragment
import cn.sixlab.app.sixlabapp.ft.SearchFragment
import cn.sixlab.app.sixlabapp.ft.WatchedFragment
import cn.sixlab.app.sixlabapp.ft.WatchingFragment

/**
 * Created by patrick on 2017/7/4.
 */
interface FragmentListener:
        SearchFragment.OnFragmentInteractionListener
        ,WatchingFragment.OnFragmentInteractionListener
        ,WatchedFragment.OnFragmentInteractionListener
        ,AddFragment.OnFragmentInteractionListener