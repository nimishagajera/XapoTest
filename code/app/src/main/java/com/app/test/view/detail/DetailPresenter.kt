package com.app.test.view.detail

import com.app.test.model.FollowersResponse
import com.app.test.view.base.BasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailPresenter(private val view: DetailContractor.View):BasePresenter(), DetailContractor.Presenter {

    override fun fetchFollowersList(url: String) {
        val subscription = apiService.getFollowersList(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response: ArrayList<FollowersResponse> ->
                            view.onFetchFollowersListSuccess(response)
                        },
                        { e: Throwable ->
                            view.onFetchFollowersListFail(e.message)
                        },
                        {
                            view.hideLoading()
                        }
                )
        mSubscription.add(subscription)
    }

    override fun fetchFollowingList(url: String) {
        val subscription = apiService.getFollowingList(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response: ArrayList<FollowersResponse> ->
                            view.hideLoading()
                            view.onFetchFollowingListSuccess(response)
                        },
                        { e: Throwable ->
                            view.hideLoading()
                            view.onFetchFollowingListFail(e.message)
                        },
                        {
                            view.hideLoading()
                        }
                )
        mSubscription.add(subscription)
    }
}