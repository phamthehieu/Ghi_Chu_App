package com.example.incomeandexpenditureapp.view.categories

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.incomeandexpenditureapp.R
import com.example.incomeandexpenditureapp.adapter.categories.IconListAddNewCategoriesAdapter
import com.example.incomeandexpenditureapp.data.model.Icon
import com.example.incomeandexpenditureapp.databinding.FragmentAddNewCategoriesBinding
import com.example.incomeandexpenditureapp.utils.FancyToast
import com.example.incomeandexpenditureapp.viewmodel.CategoryViewModel
import com.example.incomeandexpenditureapp.viewmodel.CategoryViewModelFactory
import com.example.incomeandexpenditureapp.viewmodel.IconViewModel
import com.example.incomeandexpenditureapp.viewmodel.IconViewModelFactory
import com.google.android.material.snackbar.Snackbar

class AddNewCategoriesFragment : Fragment(), IconListAddNewCategoriesAdapter.OnIconSelectedListener {

    private var _binding: FragmentAddNewCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private var method = 0

    private var iconSelected: Icon? = null

    private val iconViewModel: IconViewModel by viewModels {
        IconViewModelFactory(requireActivity().application)
    }

    private val categoryViewModel: CategoryViewModel by viewModels {
        CategoryViewModelFactory(requireActivity().application)
    }

    private lateinit var adapter: IconListAddNewCategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewCategoriesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpHeader()

        setUpRecyclerView()

        updateTabBackground(1)
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 5)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.getItemViewType(position) == IconListAddNewCategoriesAdapter.TYPE_HEADER) {
                    5
                } else {
                    1
                }
            }
        }

        binding.recyclerViewIconAddCategory.layoutManager = layoutManager

        binding.imageIconSelected.setColorFilter(Color.BLACK)

        iconViewModel.allIcons.observe(requireActivity(), Observer { icons ->

            val groupedIcons = groupIconsByType(icons)
            val flatIconsList = groupedIcons.values.flatten()

            adapter = IconListAddNewCategoriesAdapter(groupedIcons, this, 1)
            binding.recyclerViewIconAddCategory.adapter = adapter

        })

    }

    private fun setUpHeader() {
        navController = findNavController()

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.tabExpenseSetting.setOnClickListener {
            method = 1
            updateTabBackground(1)
        }

        binding.tabIncomeSetting.setOnClickListener {
            method = 2
            updateTabBackground(2)
        }

        binding.addNewCategory.setOnClickListener {
            val inputText = binding.nameCategoryEt.text.toString().trim()

            if (inputText.isEmpty()) {
                FancyToast.makeText(requireContext(), getString(R.string.title_error_message), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
            }
        }

    }

    private fun updateTabBackground(selectedTabNumber: Int) {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val selectedColor = ContextCompat.getColor(requireContext(), R.color.black)
        val unselectedColor = ContextCompat.getColor(requireContext(), R.color.white)
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_YES -> {

                binding.tabExpenseSetting.apply {
                    setBackgroundResource(if (selectedTabNumber == 1) R.drawable.round_back_white_left else 0)
                    setTextColor(if (selectedTabNumber == 1) selectedColor else unselectedColor)
                }
                binding.tabIncomeSetting.apply {
                    setBackgroundResource(if (selectedTabNumber == 2) R.drawable.round_back_white_right else 0)
                    setTextColor(if (selectedTabNumber == 2) selectedColor else unselectedColor)
                }
                binding.titleSubAAE.setBackgroundResource(R.drawable.round_back_white10_100)
                binding.titleBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black1
                    )
                )
            }

            Configuration.UI_MODE_NIGHT_NO -> {

                binding.tabExpenseSetting.apply {
                    setBackgroundResource(if (selectedTabNumber == 1) R.drawable.round_back_white_left_black else 0)
                    setTextColor(if (selectedTabNumber == 1) selectedColor else unselectedColor)
                }
                binding.tabIncomeSetting.apply {
                    setBackgroundResource(if (selectedTabNumber == 2) R.drawable.round_back_white_right_black else 0)
                    setTextColor(if (selectedTabNumber == 2) selectedColor else unselectedColor)
                }
                binding.titleSubAAE.setBackgroundResource(R.drawable.round_back_white10_100_black)
                binding.titleBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.yellow
                    )
                )
                binding.addNewCategory.setColorFilter(Color.BLACK)
                val color = ContextCompat.getColor(requireContext(), R.color.yellow)
                requireActivity().window.statusBarColor = color
            }
        }
    }

    private fun groupIconsByType(icons: List<Icon>): Map<String, List<Icon>> {
        return icons.groupBy { it.type }
    }

    override fun onIconSelected(icon: Icon) {
        iconSelected = icon
        Glide.with(requireContext())
            .load("file:///android_asset/${icon.iconResource}")
            .into(binding.imageIconSelected)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}