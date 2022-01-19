package com.stevejonnunez.hingehomework.adapters

import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stevejonnunez.hingehomework.GlideApp
import com.stevejonnunez.hingehomework.databinding.ListItemHingeUserBinding
import com.stevejonnunez.hingehomework.model.User


class HingeProfileAdapter :
    ListAdapter<User, HingeProfileAdapter.HingeProfileViewHolder>(UserDiffCallback()) {

    private var configList: List<String> = emptyList()

    class HingeProfileViewHolder(
        private val binding: ListItemHingeUserBinding, private val configList: List<String>
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {

            setupTextView(binding.nameTextView, user.name, binding.nameLinearLayout)
            setupTextView(binding.genderTextView, user.gender?.longString, binding.genderLinearLayout)
            setupTextView(binding.aboutTextView, user.about, binding.aboutLinearLayout)
            setupTextView(binding.schoolTextView, user.school, binding.schoolLinearLayout)
            setupTextView(binding.schoolTextView, user.school, binding.schoolLinearLayout)
            setupTextView(binding.hobbiesTextView, user.hobbies, binding.hobbiesLinearLayout)
            setupImageView(binding.photoImageView, user.photoURL, binding.photoLinearLayout)

            for(layoutOrder in configList) {
                addViewToLinearLayoutContainer(layoutContainerMap[layoutOrder])
            }
        }

        private fun addViewToLinearLayoutContainer(view: View?) {
            if (view != null) {
                (view.parent as ViewGroup).removeView(view)
                binding.layoutContainer.addView(view)
            }
        }

        private fun setupTextView(textView: TextView, text: String?, linearLayout: LinearLayout) {
            if (text != null) {
                textView.text = text
                linearLayout.visibility = View.VISIBLE
            } else {
                linearLayout.visibility = View.GONE
            }
        }

        private fun setupTextView(textView: TextView, list: List<String>?, linearLayoutContainer: LinearLayout) {
            if (list != null) {
                textView.text = list.joinToString()
                linearLayoutContainer.visibility = View.VISIBLE
            } else {
                linearLayoutContainer.visibility = View.GONE
            }
        }

        private fun setupImageView(imageView: ImageView, photoUrl: String?, linearLayoutContainer: LinearLayout) {
            if (photoUrl != null) {
                Glide.with(binding.root)
                    .load(photoUrl)
                    .into(imageView)
                linearLayoutContainer.visibility = View.VISIBLE
            } else {
                linearLayoutContainer.visibility = View.GONE
            }
        }

        private val layoutContainerMap = mapOf(
            "name" to binding.nameLinearLayout,
            "photo" to binding.photoLinearLayout,
            "gender" to binding.genderLinearLayout,
            "about" to binding.aboutLinearLayout,
            "school" to binding.schoolLinearLayout,
            "hobbies" to binding.hobbiesLinearLayout
        )

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HingeProfileViewHolder {
        val binding =
            ListItemHingeUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HingeProfileViewHolder(binding, configList)
    }

    override fun onBindViewHolder(holder: HingeProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun submitUserAndConfig(userList: List<User>, configList: List<String>) {
        submitList(userList)
        this.configList = configList
    }

}

private class UserDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}