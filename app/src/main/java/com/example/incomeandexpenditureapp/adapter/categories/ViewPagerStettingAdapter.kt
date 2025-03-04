package com.example.incomeandexpenditureapp.adapter.categories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.incomeandexpenditureapp.view.categories.expense.DirectorySettingExpenseFragment
import com.example.incomeandexpenditureapp.view.categories.income.DirectorySettingIncomeFragment

class ViewPagerStettingAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val fragments: List<Fragment> = listOf(
        DirectorySettingExpenseFragment(),
        DirectorySettingIncomeFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}