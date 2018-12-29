package com.app.test.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.app.test.R

/*
* Fragment handle class
* */
class FragmentUtils(private val fragmentManager: FragmentManager?) {

    /**
     * add fragment method for adding fragment without back stack
     * @param container
     * @param fragment
     */
    fun addFragment(container: Int, fragment: Fragment) {
        fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                ?.add(container, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }

}
