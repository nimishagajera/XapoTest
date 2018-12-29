package com.app.test.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.test.R
import com.app.test.databinding.ActivityMainBinding
import com.app.test.util.FragmentUtils
import com.app.test.view.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var fragmentUtils: FragmentUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        fragmentUtils = FragmentUtils(supportFragmentManager)
        fragmentUtils?.addFragment(R.id.container, ListFragment())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val count = supportFragmentManager.backStackEntryCount
        if (count == 1)
            finish()
        else
            supportFragmentManager.popBackStack()
    }
}
