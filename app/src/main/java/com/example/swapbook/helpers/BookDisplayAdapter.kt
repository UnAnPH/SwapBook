package com.example.swapbook.helpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swapbook.database.BookDetail
import com.example.swapbook.databinding.BookDisplayHomeItemBinding
import com.example.swapbook.databinding.GridViewItemBinding
import com.example.swapbook.network.MarsProperty

//class BookDisplayAdapter: ListAdapter<BookDetail, BookDisplayAdapter.ViewHolder>(HomeDiffCallback()) {
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent)
//    }
//
//
//    class ViewHolder private constructor(val binding: BookDisplayHomeItemBinding) : RecyclerView.ViewHolder(binding.root){
//        //        val bookPrice: TextView = itemView.findViewById(R.id.book_price)
////        val bookImage: ImageView = itemView.findViewById(R.id.book_location)
////        val bookLocation: TextView = itemView.findViewById(R.id.book_location)
//
//        fun bind(item: BookDetail) {
//            binding.book = item
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//
//                val binding =
//                        BookDisplayHomeItemBinding.inflate(layoutInflater, parent, false)
//
//                return ViewHolder(binding)
//            }
//        }
//    }
//}
//
//class HomeDiffCallback : DiffUtil.ItemCallback<BookDetail>() {
//    override fun areItemsTheSame(oldItem: BookDetail, newItem: BookDetail): Boolean {
//        return oldItem.bookID == newItem.bookID
//    }
//
//    override fun areContentsTheSame(oldItem: BookDetail, newItem: BookDetail): Boolean {
//        return oldItem == newItem
//    }
//}

/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class PhotoGridAdapter : ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

    /**
     * The MarsPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsProperty] information.
     */
    class MarsPropertyViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(marsProperty: MarsProperty) {
            binding.property = marsProperty
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [MarsProperty]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }
}