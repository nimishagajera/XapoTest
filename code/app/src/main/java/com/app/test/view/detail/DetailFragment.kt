package com.app.test.view.detail


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.R
import com.app.test.databinding.FragmentDetailBinding
import com.app.test.model.FollowersResponse
import com.app.test.model.RepositoryModel
import com.app.test.view.base.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseFragment(), DetailContractor.View {

    private var repositoryModel: RepositoryModel? = null
    private lateinit var binding: FragmentDetailBinding
    private lateinit var presenter: DetailContractor.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        presenter = DetailPresenter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }

        if (arguments != null)
            repositoryModel = arguments?.getParcelable("repository") as RepositoryModel

        Glide.with(activity!!).load(repositoryModel?.owner?.avatar_url)
                .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(binding.image)

        binding.layoutContent.txtName.text = repositoryModel?.full_name
        binding.layoutContent.txtDescription.text = repositoryModel?.description
        binding.layoutContent.txtUserType.text = repositoryModel?.owner?.type
        binding.layoutContent.txtLink.text = repositoryModel?.url

        binding.collapsingToolbar.title = repositoryModel?.full_name
        presenter.fetchFollowersList(repositoryModel?.owner?.followers_url!!)
        presenter.fetchFollowingList(repositoryModel?.owner?.following_url!!)
    }

    override fun getToolbarTitle(): String? = null

    override fun showBackButton(): Boolean = false

    override fun onFetchFollowersListSuccess(list: ArrayList<FollowersResponse>) {
        binding.layoutContent.txtFollowers.text = "${list.size}"
    }

    override fun onFetchFollowersListFail(strError: String?) {
        binding.layoutContent.txtFollowers.text = "-"
    }

    override fun onFetchFollowingListSuccess(list: ArrayList<FollowersResponse>) {
        binding.layoutContent.txtFollowing.text = "${list.size}"
    }

    override fun onFetchFollowingListFail(strError: String?) {
        binding.layoutContent.txtFollowing.text = "-"
    }
}
