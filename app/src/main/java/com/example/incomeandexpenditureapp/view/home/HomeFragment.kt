package com.example.incomeandexpenditureapp.view.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.incomeandexpenditureapp.R
import com.example.incomeandexpenditureapp.databinding.FragmentHomeBinding
import com.example.incomeandexpenditureapp.viewmodel.IconViewModel
import com.example.incomeandexpenditureapp.viewmodel.IconViewModelFactory
import java.io.IOException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var doubleBackPressed = false

    private val iconViewModel: IconViewModel by viewModels {
        IconViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackPressed) {
                    requireActivity().finish()
                } else {
                    doubleBackPressed = true
                    Toast.makeText(requireContext(), "Back lần nữa để thoát", Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleBackPressed = false
                    }, 2000)
                }
            }
        })

        iconViewModel.allIcons.observe(requireActivity(), Observer { icons ->
            Log.d("Hieu56", "Icons: $icons")
//            if (icons.isNotEmpty()) {
//
//                val icon = icons[0]
//
//                try {
//
//                    context?.assets?.open(icon.iconResource)?.use { inputStream ->
//                        // Tạo drawable từ inputStream
//                        val drawable = Drawable.createFromStream(inputStream, null)
//                        // Set drawable cho ImageView
//                        binding.imageArtists.setImageDrawable(drawable)
//                    }
//
//                    // Hiển thị tên loại icon
//                    binding.nameArtistsEt.text = icon.type
//
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                    Log.e("Hieu56", "Không thể tải icon: ${e.message}")
//                    binding.imageArtists.setImageResource(R.drawable.test1)
//                }
//            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
