package com.example.myapplication.adapter.categoriesAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.view.categories.expense.AddNewExpenseFragment
import com.example.myapplication.view.categories.income.AddNewIncomeFragment
import com.example.myapplication.view.categories.transfer.AddNewTransferFragment

class ViewPagerCategoryAdapter( activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = AddNewExpenseFragment()
                fragment
            }

            1 -> {
                val fragment = AddNewIncomeFragment()
                fragment
            }

            2 -> {
                val fragment = AddNewTransferFragment()
                fragment
            }

            else -> AddNewExpenseFragment()
        }
    }
}