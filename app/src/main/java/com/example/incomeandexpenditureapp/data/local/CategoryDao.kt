package com.example.incomeandexpenditureapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.incomeandexpenditureapp.data.model.Category
import com.example.incomeandexpenditureapp.data.model.CategoryWithIcon
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Transaction
    @Query("SELECT * FROM category_table")
    fun getAllCategory(): Flow<List<CategoryWithIcon>>
    @Transaction
    @Query("""SELECT * FROM category_table WHERE type = "user" """)
    fun getAllCategoryUpdate(): Flow<List<Category>>

    @Insert
    fun insertAllCategory(category: List<Category>)

    @Delete
    fun deleteCategory(category: Category): Void

    @Update
    fun updateCategory(category: Category): Void

    @Query("""DELETE FROM category_table WHERE type = "user" """)
    fun deleteAllCategoryType():Void
}