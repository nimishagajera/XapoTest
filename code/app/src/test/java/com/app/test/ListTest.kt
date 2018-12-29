package com.app.test

import com.app.test.model.Owner
import com.app.test.model.RepositoryModel
import com.app.test.network.APIService
import com.app.test.view.list.ListContractor
import com.app.test.view.list.ListPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable


open class ListTest {

    @Mock
    val apiService: APIService? = null

    @Mock
    private val view: ListContractor.View? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetchRepositoryListIntoView() {

        val owner = Owner("", "","", "", "", "", "")
        val model = RepositoryModel(0, "", "",false,owner,"","")
        val list = ArrayList<RepositoryModel>()
        list.add(model)
        `when`(apiService?.getRepositories(1))
                .thenReturn(Observable.just(list))

        val presenter = ListPresenter(this.view!!)
        presenter.fetchRepositoryList()

        val inOrder = Mockito.inOrder(view)
        inOrder.verify(view, times(1)).onFetchRepositoryListFail("Data loading failed")
        inOrder.verify(view, times(1)).onFetchRepositoryListSuccess(list)
    }

    @Test
    fun fetchErrorShouldReturnErrorToView() {

        val exception = Exception()

        `when`(apiService?.getRepositories(1))
                .thenReturn(Observable.error<ArrayList<RepositoryModel>>(exception))

        val mainPresenter = ListPresenter(this.view!!)

        mainPresenter.fetchRepositoryList()

        val inOrder = Mockito.inOrder(view)
        inOrder.verify(view, times(1)).onFetchRepositoryListFail(exception.toString())
        verify(view, never()).onFetchRepositoryListSuccess(ArrayList())
    }
}