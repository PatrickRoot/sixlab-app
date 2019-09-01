package cn.sixlab.app.sixlabapp

import android.app.Fragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import cn.sixlab.app.sixlabapp.ft.AddFragment
import cn.sixlab.app.sixlabapp.ft.SearchFragment
import cn.sixlab.app.sixlabapp.ft.WatchedFragment
import cn.sixlab.app.sixlabapp.ft.WatchingFragment
import cn.sixlab.app.sixlabapp.util.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),FragmentListener {
    override fun onFragmentInteraction(uri: Uri?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var context: Context? = null
    var preItemId: Int? = null
    var preFt: Fragment? = null
    var themeId: Int = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        initView(item)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.formatDate(navigation)
        initView(navigation.menu.getItem(0))
    }

    private fun initView(item: MenuItem) {
        val itemId = item.itemId
        if (preItemId != null && itemId == preItemId) {
            return
        }
        val ft = fragmentManager.beginTransaction()
        val clz: Class<*>
        when (itemId) {
            R.id.navigation_watching -> clz = WatchingFragment::class.java
            R.id.navigation_search -> clz = SearchFragment::class.java
            R.id.navigation_watched -> clz = WatchedFragment::class.java
            R.id.navigation_add -> clz = AddFragment::class.java
            else -> clz = WatchingFragment::class.java
        }

        if (preFt != null) {
            ft.detach(preFt)
        }

        val bundle = Bundle()
        bundle.putInt("args", themeId)

        val name = clz.name
        val fragment = Fragment.instantiate(context, name, bundle)
        ft.add(R.id.ftContainer, fragment, name)
        preFt = fragment
        preItemId = itemId
        ft.commit()
    }
}
