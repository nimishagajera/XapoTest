package com.app.test.di.component


import com.app.test.di.module.ContextModule
import com.app.test.di.module.NetworkModule
import com.app.test.view.base.BaseFragment
import com.app.test.view.base.BasePresenter
import dagger.Component
import javax.inject.Singleton
/*
* AppComponent class used for dagger injection
* For inject module and use with @Inject
* */
@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(fragment: BaseFragment)

    fun inject(presenter: BasePresenter)
}