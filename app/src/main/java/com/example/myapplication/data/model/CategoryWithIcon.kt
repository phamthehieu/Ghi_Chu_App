package com.example.myapplication.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithIcon(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "idIcon",
        entityColumn = "id"
    )
    val icon: Icons
)

