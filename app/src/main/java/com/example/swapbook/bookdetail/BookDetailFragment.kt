package com.example.swapbook.bookdetail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.swapbook.ChatActivity
import com.example.swapbook.MainActivity
import com.example.swapbook.R
import com.example.swapbook.databinding.BookDetailFragmentBinding
import com.example.swapbook.network.ResponseModel
import com.example.swapbook.network.SwapBookApi
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_insertion.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class BookDetailFragment : Fragment() {
    private var auth: FirebaseAuth? = null
    var showFAB: Boolean = false
    @SuppressLint("ResourceAsColor", "ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        val firebaseUser = auth!!.currentUser!!

        val application = requireNotNull(activity).application
        val binding = BookDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val post = BookDetailFragmentArgs.fromBundle(arguments!!).selectedPost
        val viewModelFactory = BookDetailViewModelFactory(post, application)

        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(BookDetailViewModel::class.java)

        binding.button.setOnClickListener {
//            val intent = Intent (getActivity(), ChatActivity::class.java)
//            getActivity()?.startActivity(intent)

            activity?.let{
                val intent = Intent(it, ChatActivity::class.java)
                intent.putExtra("userId", binding.etVendorNameText.contentDescription)
                it.startActivity(intent)
            }
        }

        binding.imgBack.setOnClickListener {
            Log.i("id1", firebaseUser.uid)
            Log.i("id2", binding.etVendorNameText.contentDescription.toString())
        }

//        binding.bookDetailScrollView.setOnTouchListener { v, event ->
//            if(binding.editFAB.visibility != View.VISIBLE) {
//                if (firebaseUser.uid == binding.etVendorNameText.contentDescription.toString()) {
//                    binding.editFAB.visibility = View.VISIBLE
//                }
//            }
//
//            true
//        }
        binding.mainPhotoImage.setOnTouchListener { v, event ->
            if(binding.editFAB.visibility != View.VISIBLE && showFAB == false) {
                if (firebaseUser.uid == binding.etVendorNameText.contentDescription.toString()) {
                    binding.editFAB.visibility = View.VISIBLE
                    showFAB = true
                }
            }
            true
        }

        binding.editFAB.setOnClickListener {
            binding.etBookTitleText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etBookAuthorText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etCityText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etConditionText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etDescriptionText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etGenreText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etHeightText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etLenghtText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etWidthText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etPriceText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etPublishingHouseText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etPublishingYearText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etProvinceText.backgroundTintList = ColorStateList.valueOf(R.color.black)
            binding.etVendorNameText.backgroundTintList = ColorStateList.valueOf(R.color.black)

            binding.imgDone.visibility = View.VISIBLE
            binding.imgDelete.visibility = View.VISIBLE
            binding.etGenreText.visibility = View.INVISIBLE
            binding.spinnerGenre.visibility = View.VISIBLE
            binding.editFAB.visibility = View.INVISIBLE

            val stringArray = resources.getStringArray(R.array.genres)
            binding.spinnerGenre.setSelection(stringArray.indexOf(binding.etGenreText.text))
            showFAB = true
        }

        binding.imgDone.setOnClickListener {
            val bookTitle = binding.etBookTitleText.text.toString()
            val authorName = binding.etBookAuthorText.text.toString()
            val genre = binding.spinnerGenre.selectedItem.toString()
            val description = binding.etCityText.text.toString()
            val publishingYear = binding.etConditionText.text.toString()
            val publishingHouse = binding.etDescriptionText.text.toString()
            val physicalDescription = binding.etGenreText.text.toString()
            val condition = binding.etConditionText.toString()
            val lenght = binding.etLenghtText.toString()
            val height = binding.etWidthText.toString()
            val width = binding.etPriceText.toString()
            val city = binding.etPublishingHouseText.toString()
            val province = binding.etPublishingYearText.toString()
            val price = binding.etProvinceText.toString()

            var imgUrl: String = ""
            if(binding.mainPhotoImage.contentDescription != null) {
                imgUrl = binding.mainPhotoImage.contentDescription!!.toString()
            }

            val updateData: Call<ResponseModel?>? = SwapBookApi.retrofitService.updateData(
                "UPDATE", binding.etBookIdText.text.toString()
                ,binding.etPostIdText.text.toString(), imgUrl,  bookTitle,
                authorName,
                genre,
                description, publishingYear, publishingHouse,physicalDescription,condition,
                lenght,height,width,city,province,price
            )

            updateData!!.enqueue(object : Callback<ResponseModel?> {
                override fun onResponse(
                    call: Call<ResponseModel?>?,
                    response: Response<ResponseModel?>?
                ) {

                    var myResponseCode = ""
                    if (response?.body() != null) {
                        myResponseCode = response.body()!!.code!!
                    }
                    when {
                        myResponseCode == "1" -> {
                            Log.i(
                                "msg:", """SUCCESS:
                 1. Data deleted Successfully.
                 2. ResponseCode: $myResponseCode"""
                            )
                        }
                        myResponseCode.equals("2", ignoreCase = true) -> {
                            Log.i(
                                "msg:",
                                "UNSUCCESSFUL However Good Response. 1. CONNECTION TO SERVER WAS SUCCESSFUL 2. WE ATTEMPTED POSTING DATA BUT ENCOUNTERED ResponseCode: $myResponseCode 3. Most probably the problem is with your PHP Code."
                            )
                        }
                        myResponseCode.equals("3", ignoreCase = true) -> {
                            Log.i(
                                "msg:",
                                "NO MYSQL CONNECTION Your PHP Code is unable to connect to mysql database. Make sure you have supplied correct database credentials."
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseModel?>?, t: Throwable) {
                    Log.i(
                        "msg:",
                        "FAILURE FAILURE THROWN DURING DELETE." + " ERROR Message: " + t.message
                    )
                }
            })

            activity?.let{
                val intent = Intent(it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }

        binding.imgDelete.setOnClickListener{
            val deleteData: Call<ResponseModel?>? = SwapBookApi.retrofitService.deleteData(
                "DELETE", binding.imgDelete.contentDescription.toString()
            )

            deleteData!!.enqueue(object : Callback<ResponseModel?> {
                override fun onResponse(
                    call: Call<ResponseModel?>?,
                    response: Response<ResponseModel?>?
                ) {

                    var myResponseCode = ""
                    if (response?.body() != null) {
                        myResponseCode = response.body()!!.code!!
                    }
                    when {
                        myResponseCode == "1" -> {
                            Log.i(
                                "msg:", """SUCCESS:
                 1. Data deleted Successfully.
                 2. ResponseCode: $myResponseCode"""
                            )
                        }
                        myResponseCode.equals("2", ignoreCase = true) -> {
                            Log.i(
                                "msg:",
                                "UNSUCCESSFUL However Good Response. 1. CONNECTION TO SERVER WAS SUCCESSFUL 2. WE ATTEMPTED POSTING DATA BUT ENCOUNTERED ResponseCode: $myResponseCode 3. Most probably the problem is with your PHP Code."
                            )
                        }
                        myResponseCode.equals("3", ignoreCase = true) -> {
                            Log.i(
                                "msg:",
                                "NO MYSQL CONNECTION Your PHP Code is unable to connect to mysql database. Make sure you have supplied correct database credentials."
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseModel?>?, t: Throwable) {
                    Log.i(
                        "msg:",
                        "FAILURE FAILURE THROWN DURING DELETE." + " ERROR Message: " + t.message
                    )
                }
            })

            activity?.let{
                val intent = Intent(it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }


        return binding.root
    }


}