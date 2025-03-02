package com.example.myapplication.view.categories

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.adapter.categoriesAdapter.ViewPagerCategoryAdapter
import com.example.myapplication.databinding.FragmentAddNewCategoryBinding


class AddNewCategoryFragment : Fragment() {

    private var _binding: FragmentAddNewCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddNewCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupButtonHeader()
    }

    private fun setupButtonHeader() {

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun setupViewPager() {
        viewPager = binding.viewPagerTv

        val adapter = ViewPagerCategoryAdapter(requireActivity())
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
        viewPager.offscreenPageLimit = 3

        binding.tabExpense.setOnClickListener {
            viewPager.currentItem = 0
        }

        binding.tabIncome.setOnClickListener {
            viewPager.currentItem = 1
        }

        binding.tabTransfer.setOnClickListener {
            viewPager.currentItem = 2
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateTabBackground(position + 1)
            }
        })
    }

    private fun updateTabBackground(selectedTabNumber: Int) {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val selectedColor = ContextCompat.getColor(requireContext(), R.color.black)
        val unselectedColor = ContextCompat.getColor(requireContext(), R.color.white)

        when (selectedTabNumber) {
            1 -> {
                binding.leftView.visibility = View.GONE
                binding.rightView.visibility = View.VISIBLE
            }

            2 -> {
                binding.leftView.visibility = View.GONE
                binding.rightView.visibility = View.GONE
            }

            3 -> {
                binding.leftView.visibility = View.VISIBLE
                binding.rightView.visibility = View.GONE
            }
        }

        binding.tabExpense.apply {
            if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                setBackgroundResource(if (selectedTabNumber == 1) R.drawable.round_back_white_left else 0)
                setTextColor(if (selectedTabNumber == 1) selectedColor else unselectedColor)
            } else {
                setBackgroundResource(if (selectedTabNumber == 1) R.drawable.round_back_black_left else 0)
                setTextColor(if (selectedTabNumber == 1) unselectedColor else selectedColor)
            }
        }
        binding.tabIncome.apply {
            if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                setBackgroundResource(if (selectedTabNumber == 2) R.drawable.round_back_white_center else 0)
                setTextColor(if (selectedTabNumber == 2) selectedColor else unselectedColor)
            } else {
                setBackgroundResource(if (selectedTabNumber == 2) R.drawable.round_back_black_center else 0)
                setTextColor(if (selectedTabNumber == 2) unselectedColor else selectedColor)
            }
        }
        binding.tabTransfer.apply {
            if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                setBackgroundResource(if (selectedTabNumber == 3) R.drawable.round_back_white_right else 0)
                setTextColor(if (selectedTabNumber == 3) selectedColor else unselectedColor)
            } else {
                setBackgroundResource(if (selectedTabNumber == 3) R.drawable.round_back_black_right else 0)
                setTextColor(if (selectedTabNumber == 3) unselectedColor else selectedColor)
            }
        }
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            binding.titleSubAAE.setBackgroundResource(R.drawable.round_back_white10_100)
        } else {
            binding.titleSubAAE.setBackgroundResource(R.drawable.round_back_black10_100)
        }
        binding.title.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) R.color.black1 else R.color.yellow
            )
        )
        binding.monthlyPayment.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) R.color.white else R.color.black1
            )
        )

        requireActivity().window.statusBarColor = ContextCompat.getColor(
            requireContext(),
            if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) R.color.black else R.color.yellow
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}