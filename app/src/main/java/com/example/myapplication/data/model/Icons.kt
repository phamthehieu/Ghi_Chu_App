package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "icon_table")
data class Icons(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val iconResource: String,
    val type: String
)
