package com.app.test.view.detail

import com.app.test.model.FollowersResponse
import com.app.test.view.base.BaseContractor

interface DetailContractor {

    interface View: BaseContractor.BaseView {

        fun onFetchFollowersListSuccess(list: ArrayList<FollowersResponse>)

        fun onFetchFollowersListFail(strError: String?)

        fun onFetchFollowingListSuccess(list: ArrayList<FollowersResponse>)

        fun onFetchFollowingListFail(strError: String?)
    }

    interface Presenter:BaseContractor.BasePresenter {

        fun fetchFollowersList(url: String)

        fun fetchFollowingList(url: String)

    }
}