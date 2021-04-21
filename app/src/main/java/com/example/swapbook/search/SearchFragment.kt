package com.example.swapbook.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.swapbook.R
import com.example.swapbook.database.BookDatabase
import com.example.swapbook.databinding.SearchFragmentBinding
import com.example.swapbook.network.Post
import java.util.*

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding: SearchFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.search_fragment, container, false)

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao
        val viewModelFactory = SearchViewModelFactory(dataSource, application)

        val searchViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(SearchViewModel::class.java)

        binding.searchViewModel= searchViewModel

        binding.lifecycleOwner = this

        binding.setLifecycleOwner(this)

        binding.postSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val resultList = mutableListOf<Post>()
                for(row in searchViewModel.postsSearch){
                    if (newText != null) {
                        if (row.title.toLowerCase(Locale.ROOT).contains(newText.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                }
                searchViewModel.setPosts(resultList.toList())
                Log.i("msg", resultList.toString())
                return false
            }

        })

        binding.postList.adapter = PostListAdapter(PostListAdapter.OnClickListener {
            searchViewModel.displayPostDetails(it)
        }
        )





        searchViewModel.navigateToSelectedPost.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToBookDetailFragment(it))
                searchViewModel.displayPostDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root}




}