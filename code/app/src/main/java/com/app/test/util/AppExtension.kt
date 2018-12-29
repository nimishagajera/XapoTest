package com.app.test.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.app.test.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/*
* Binding Adapter for image
* */
@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, resource: String) {
    Glide.with(imageView.context)
            .load(resource)
            .apply(RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)
                    .circleCrop())
            .into(imageView)
}