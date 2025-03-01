package com.example.incomeandexpenditureapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.incomeandexpenditureapp.data.model.Category
import com.example.incomeandexpenditureapp.data.model.Icon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(
    entities = [Icon::class, Category::class], version = 3, exportSchema = false)
abstract class IncomeAndExpenditureDatabase : RoomDatabase() {
    abstract fun iconDao(): IconsDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: IncomeAndExpenditureDatabase? = null
        private var initialized = false

        fun getDatabase(context: Context, scope: CoroutineScope): IncomeAndExpenditureDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IncomeAndExpenditureDatabase::class.java,
                    "category_database"
                ).addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    if (!initialized) {
                        scope.launch(Dispatchers.IO) { insertSampleData( database.categoryDao(), database.iconDao())
                        }
                        initialized = true
                    }
                }
            }
        }

        private suspend fun insertSampleData(categoryDao: CategoryDao, iconDao: IconsDao) {
            val iconList = mutableListOf<Icon>()

            for (i in 1..15) {
                iconList.add(Icon(iconResource = "icons/giaiTri/giaiTri$i.png", type = "GiaiTri"))
                iconList.add(Icon(iconResource = "icons/doAn/doAn$i.png", type = "DoAn"))
                iconList.add(Icon(iconResource = "icons/muaSam/muaSam$i.png", type = "MuaSam"))
                iconList.add(Icon(iconResource = "icons/cuocSong/cuocSong$i.png", type = "CuocSong"))
                iconList.add(Icon(iconResource = "icons/caNhan/caNhan$i.png", type = "CaNhan"))
                iconList.add(Icon(iconResource = "icons/duLich/duLich$i.png", type = "DuLich"))
                iconList.add(Icon(iconResource = "icons/giaoDuc/giaoDuc$i.png", type = "GiaoDuc"))
                iconList.add(Icon(iconResource = "icons/ngayHoi/ngayHoi$i.png", type = "NgayHoi"))
                iconList.add(Icon(iconResource = "icons/sucKhoe/sucKhoe$i.png", type = "SucKhoe"))
                iconList.add(Icon(iconResource = "icons/taiChinh/taiChinh$i.png", type = "TaiChinh"))
                iconList.add(Icon(iconResource = "icons/theThao/theThao$i.png", type = "TheThao"))
                iconList.add(Icon(iconResource = "icons/vanPhong/vanPhong$i.png", type = "VanPhong"))
                iconList.add(Icon(iconResource = "icons/phuongTien/phuongTien$i.png", type = "PhuongTien"))
                iconList.add(Icon(iconResource = "icons/khac/khac$i.png", type = "Khac"))
                iconList.add(Icon(iconResource = "icons/thuCung/thuCung$i.png", type = "ThuCung"))
                iconList.add(Icon(iconResource = "icons/treEm/treEm$i.png", type = "TreEm"))
            }

            for (i in 1..21) {
                iconList.add(Icon(iconResource = "icons/iconDanhMucMau/mau$i.png", type = "Mau"))
            }

            val sampleCategories = listOf(
                Category(name = "Mua sắm", idIcon = 250, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Đồ ăn", idIcon = 244, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Điện thoại", idIcon = 243, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Giải trí", idIcon = 246, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Giáo dục", idIcon = 247, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Sắc đẹp", idIcon = 252, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Thể thao", idIcon = 254, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Xã hội", idIcon = 261, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Vận tải", idIcon = 257, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Quần áo", idIcon = 251, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Xe hơi", idIcon = 253, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Rượu", idIcon = 260, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Thuốc lá", idIcon = 242, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Thiết bị điện tử", idIcon = 245, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Du lịch", idIcon = 256, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Sức khoẻ", idIcon = 248, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Thú cưng", idIcon = 259, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Sửa chữa", idIcon = 255, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Hoa quả", idIcon = 249, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Rau quả", idIcon = 258, type = "admin", source = "ChiTieu", budget = "0"),
                Category(name = "Trẻ em", idIcon = 241, type = "admin", source = "ChiTieu", budget = "0"),
            )

            withContext(Dispatchers.IO) {
                iconDao.insertAll(iconList)
                categoryDao.insertAllCategory(sampleCategories)
            }
        }

    }
}