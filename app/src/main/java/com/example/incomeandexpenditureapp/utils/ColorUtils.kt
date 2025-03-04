package com.example.incomeandexpenditureapp.utils

import android.graphics.Color

object ColorUtils {
    private val fixedColors = listOf(
        Color.parseColor("#FFCDD2"), // Đỏ nhạt
        Color.parseColor("#F8BBD0"), // Hồng nhạt
        Color.parseColor("#E1BEE7"), // Tím nhạt
        Color.parseColor("#D1C4E9"), // Xanh dương nhạt
        Color.parseColor("#C5CAE9"), // Xanh da trời nhạt
        Color.parseColor("#B3E5FC"), // Xanh nước biển nhạt
        Color.parseColor("#B2DFDB"), // Xanh lục nhạt
        Color.parseColor("#C8E6C9"), // Xanh lá cây nhạt
        Color.parseColor("#DCEDC8"), // Xanh lá sáng
        Color.parseColor("#FFF9C4"), // Vàng nhạt
        Color.parseColor("#FFECB3"), // Vàng cam nhạt
        Color.parseColor("#FFE0B2"), // Cam sáng
        Color.parseColor("#FFCCBC"), // Đỏ cam nhạt
        Color.parseColor("#D7CCC8"), // Xám nâu nhạt
        Color.parseColor("#F5F5F5"), // Xám trắng
        Color.parseColor("#CFD8DC"), // Xám xanh nhạt
        Color.parseColor("#F48FB1"), // Hồng pastel
        Color.parseColor("#CE93D8"), // Tím pastel
        Color.parseColor("#B39DDB"), // Xanh tím nhạt
        Color.parseColor("#90CAF9"), // Xanh dương nhạt
        Color.parseColor("#81D4FA"), // Xanh nước biển nhạt
        Color.parseColor("#80DEEA"), // Xanh cyan nhạt
        Color.parseColor("#A5D6A7"), // Xanh lá pastel
        Color.parseColor("#E6EE9C"), // Xanh vàng nhạt
        Color.parseColor("#FFAB91"), // Cam hồng nhạt
        Color.parseColor("#BCAAA4"), // Nâu pastel
        Color.parseColor("#EEEEEE"), // Xám sáng
        Color.parseColor("#B0BEC5"), // Xám xanh dương
        Color.parseColor("#78909C"), // Xanh xám
        Color.parseColor("#8D6E63")  // Nâu xám
    )

    fun getColorForPosition(position: Int): Int {
        return fixedColors[position % fixedColors.size]
    }
}