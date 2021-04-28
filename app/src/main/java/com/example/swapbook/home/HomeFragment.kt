package com.example.swapbook.home

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.swapbook.R
import com.example.swapbook.databinding.HomeFragmentBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.swapbook.ChatActivity
import com.example.swapbook.MainActivity
import com.example.swapbook.ProfileActivity
import com.example.swapbook.database.BookDatabase
import com.example.swapbook.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_users_list.*
import kotlinx.android.synthetic.main.activtiy_profile.*
import kotlinx.android.synthetic.main.book_detail_fragment.*
import kotlinx.android.synthetic.main.book_detail_fragment.button
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

//    private lateinit var firebaseUser: FirebaseUser
//    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        firebaseUser = FirebaseAuth.getInstance().currentUser!!
//
//        databaseReference =
//            FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)

        val binding: HomeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false)

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)

        val homeViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(HomeViewModel::class.java)

        binding.homeViewModel= homeViewModel

        binding.lifecycleOwner = this

//        val navHostFragment =
//                getActivity()?.getSupportFragmentManager()?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController

        binding.setLifecycleOwner(this)

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            homeViewModel.displayPostDetails(it)
        })

        homeViewModel.navigateToSelectedPost.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToBookDetailFragment(it))
                homeViewModel.displayPostDetailsComplete()
            }
        })

//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(getActivity(), error.message, Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val user = snapshot.getValue(User::class.java)
//
//                if (user!!.profileImage == "") {
//                    binding.userImage.setImageResource(R.drawable.profile_image)
//                } else {
//                    getActivity()?.let { Glide.with(it).load(user!!.profileImage)
//                        .apply(
//                            RequestOptions()
//                                .placeholder(R.drawable.loading_animation)
//                                .error(R.drawable.ic_broken_image))
//                        .into(binding.userImage) }
//                }
//            }
//        })
//
//        binding.userImage.setOnClickListener {
//            activity?.let{
//                val intent = Intent (it, ProfileActivity::class.java)
//                it.startActivity(intent)
//            }
//        }

        setHasOptionsMenu(true)
        return binding.root
    }



}