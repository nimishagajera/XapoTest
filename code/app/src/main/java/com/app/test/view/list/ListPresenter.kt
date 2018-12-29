package com.app.test.view.list

import com.app.test.model.RepositoryModel
import com.app.test.view.base.BasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListPresenter(private val view: ListContractor.View): BasePresenter(), ListContractor.Presenter {

    override fun fetchRepositoryList() {
        val subscription = apiService.getRepositories(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response: ArrayList<RepositoryModel> ->
                            view.onFetchRepositoryListSuccess(response)
                        },
                        { e: Throwable ->
                            view.onFetchRepositoryListFail(e.message)
                        },
                        {
                            view.hideLoading()
                        }
                )
        mSubscription.add(subscription)


    }
}