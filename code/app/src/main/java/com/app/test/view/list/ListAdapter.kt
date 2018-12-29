package com.app.test.view.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.BR
import com.app.test.R
import com.app.test.databinding.ListItemRepositoryBinding
import com.app.test.model.RepositoryModel
import com.app.test.util.FragmentUtils
import com.app.test.view.detail.DetailFragment

class ListAdapter(private val fragmentUtils: FragmentUtils): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var repositoryList = ArrayList<RepositoryModel>()

    fun setRepositoryData(repositoryList:ArrayList<RepositoryModel>) {
            this.repositoryList = repositoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_repository, parent, false))
    }

    override fun getItemCount(): Int = repositoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositoryList[position]

        holder.binding?.swPrivate?.isChecked = repository.private
        holder.binding?.txvName?.text = repository.name
        holder.binding?.txvDescription?.text = repository.description

        holder.binding?.setVariable(BR.repository,repository)
        holder.binding?.executePendingBindings()

        holder.binding?.layoutData?.setOnClickListener {
            val fragment = DetailFragment()
            val  bundle = Bundle()
            bundle.putParcelable("repository",repository)
            fragment.arguments = bundle
            fragmentUtils.addFragment(R.id.container, fragment)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: ListItemRepositoryBinding?= DataBindingUtil.bind(view)
    }
}