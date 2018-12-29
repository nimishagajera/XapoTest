package com.app.test.view.base

import com.app.test.TestApplication
import com.app.test.network.APIService
import rx.subscriptions.CompositeSubscription

import javax.inject.Inject

open class BasePresenter : BaseContractor.BasePresenter {

    protected lateinit var mSubscription: CompositeSubscription

    @Inject
    protected lateinit var apiService: APIService

    init {
        TestApplication.appComponent?.inject(this)
        subscribe()
    }

    final override fun subscribe() {
        mSubscription = CompositeSubscription()
    }

    override fun unSubscribe() {
        if (mSubscription != null) {
            mSubscription.clear()

        }
    }
}
