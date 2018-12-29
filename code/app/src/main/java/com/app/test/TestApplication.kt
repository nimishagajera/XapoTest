package com.app.test

import android.app.Application
import com.app.test.di.component.AppComponent
import com.app.test.di.component.DaggerAppComponent
import com.app.test.di.module.ContextModule
import com.app.test.di.module.NetworkModule

class TestApplication: Application() {

    companion object {
        var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        initComponent()
    }
    /**
     * init component
     */
    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
                .contextModule(ContextModule(applicationContext))
                .networkModule(NetworkModule())
                .build()
    }
}