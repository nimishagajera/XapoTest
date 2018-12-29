package com.app.test.view.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.app.test.TestApplication
import com.app.test.util.FragmentUtils
import com.app.test.util.LoadingViewUtils
import com.app.test.view.MainActivity


abstract class BaseFragment : Fragment(), BaseContractor.BaseView {

    lateinit var mPresenter: BaseContractor.BasePresenter
    protected lateinit var mContext: Context
    protected lateinit var fragmentUtils: FragmentUtils
    private var mainActivity: MainActivity? = null

    /**
     * @param context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentUtils = FragmentUtils(fragmentManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity)
            mainActivity = activity as MainActivity

        TestApplication.appComponent?.inject(this)
        mPresenter = BasePresenter()   // Get instance of Presenter

    }

    protected abstract fun getToolbarTitle(): String?

    protected abstract fun showBackButton(): Boolean

    override fun showLoading() {
        LoadingViewUtils.showLoading(mContext)
    }

    override fun hideLoading() {
        LoadingViewUtils.hideLoading()
    }

    override fun onPause() {
        super.onPause()
        hideLoading()
    }

    override fun onStop() {
        super.onStop()
        hideLoading()
    }
}