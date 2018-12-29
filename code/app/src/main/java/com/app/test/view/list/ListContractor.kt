package com.app.test.view.list

import com.app.test.model.RepositoryModel
import com.app.test.view.base.BaseContractor

interface ListContractor {

    interface View: BaseContractor.BaseView {

        fun onFetchRepositoryListSuccess(repositoryList: ArrayList<RepositoryModel>)

        fun onFetchRepositoryListFail(strError: String?)

    }

    interface Presenter:BaseContractor.BasePresenter {

        fun fetchRepositoryList()
    }
}