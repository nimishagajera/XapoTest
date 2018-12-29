package com.app.test.view.base


interface BaseContractor {

    interface BaseView {
        /**
         * show loading method
         */
        fun showLoading()

        /**
         * hide loading
         */
        fun hideLoading()
    }

    interface BasePresenter {

        /**
         * subscribe for adding subscriptions
         */
        fun subscribe()

        /**
         * unsubscribe for removing subscriptions
         */
        fun unSubscribe()
    }
}


