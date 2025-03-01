package com.example.incomeandexpenditureapp.adapter.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.incomeandexpenditureapp.view.categories.expense.AddNewExpenseFragment
import com.example.incomeandexpenditureapp.view.categories.income.AddNewIncomeFragment
import com.example.incomeandexpenditureapp.view.categories.transfer.AddNewTranferFragment

class ViewPagerCategoryAdapter( activity: FragmentActivity ): FragmentStateAdapter(activity) {

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
                val fragment = AddNewTranferFragment()
                fragment
            }

            else -> AddNewExpenseFragment()
        }
    }
}