package com.app.test.view.list


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.R
import com.app.test.databinding.FragmentRepositoryListBinding
import com.app.test.model.RepositoryModel
import com.app.test.view.base.BaseFragment

class ListFragment : BaseFragment(), ListContractor.View {

    private lateinit var binding:FragmentRepositoryListBinding
    private lateinit var presenter: ListContractor.Presenter
    private lateinit var adapter: ListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository_list, container, false)
        presenter = ListPresenter(this)
        adapter = ListAdapter(fragmentUtils)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.txtToolbarTitle.text = getString(R.string.label_list)

        showLoading()
        presenter.fetchRepositoryList()
        binding.recyclerRepository.layoutManager = LinearLayoutManager(mContext)
        binding.recyclerRepository.adapter = adapter

        binding.btnRetry.setOnClickListener {
            showLoading()
            presenter.fetchRepositoryList()
        }
    }

    override fun getToolbarTitle(): String? = "Repository List"

    override fun showBackButton(): Boolean = false

    override fun onFetchRepositoryListSuccess(repositoryList: ArrayList<RepositoryModel>) {
        hideLoading()
        adapter.setRepositoryData(repositoryList)
        adapter.notifyDataSetChanged()
    }

    override fun onFetchRepositoryListFail(strError: String?) {
        hideLoading()
        binding.groupError.visibility = View.VISIBLE
        binding.recyclerRepository.visibility = View.GONE
    }
}
