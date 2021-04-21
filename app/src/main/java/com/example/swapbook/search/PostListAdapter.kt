package com.example.swapbook.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swapbook.databinding.ListViewItemBinding
import com.example.swapbook.network.Post
import kotlinx.android.synthetic.main.list_view_item.view.*
import kotlinx.android.synthetic.main.search_fragment.view.*
import java.util.*
import kotlin.collections.ArrayList

class PostListAdapter(private val onClickListener: OnClickListener
) : ListAdapter<Post,
        PostListAdapter.PostViewHolder>(DiffCallback)
{


    init {

    }

    class PostViewHolder(private var binding:
                         ListViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ListViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(post)
        }
        holder.bind(post)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.ID_post == newItem.ID_post
        }

    }

    class OnClickListener(val clickListener: (post: Post) -> Unit) {
        fun onClick(post: Post) = clickListener(post)
    }

}
