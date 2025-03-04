package com.example.incomeandexpenditureapp.adapter.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.incomeandexpenditureapp.R
import com.example.incomeandexpenditureapp.base.interfaces.OnIconSelectedListener
import com.example.incomeandexpenditureapp.data.model.Icon

class IconListAddNewCategoriesAdapter(
    private val groupedIconMap: Map<String, List<Icon>>,
    private val iconSelectedListener: OnIconSelectedListener,
    private val selectedIcon: Int?
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    interface OnIconSelectedListener {
        fun onIconSelected(icon: Icon)
    }


    private var selectedPosition = RecyclerView.NO_POSITION

    private val dataList: List<Any> = mutableListOf<Any>().apply {
        groupedIconMap.forEach { (type, icons) ->
            add(type)
            addAll(icons)
        }
    }

    init {
        if (dataList.isNotEmpty()) {
            if (selectedIcon != null) {
                selectedPosition = selectedIcon
            }
            notifyItemChanged(selectedPosition)
            iconSelectedListener.onIconSelected(dataList[selectedPosition] as Icon)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] is String) TYPE_HEADER else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_add_new_category_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item_list_icon_add_new_category, parent, false)
            IconViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bind(dataList[position] as String)
        } else if (holder is IconViewHolder) {
            holder.bind(dataList[position] as Icon, position == selectedPosition)
        }

        holder.itemView.setOnClickListener {
            if (holder is IconViewHolder) {
                notifyItemChanged(selectedPosition)
                selectedPosition = holder.adapterPosition
                notifyItemChanged(selectedPosition)
                iconSelectedListener.onIconSelected(dataList[position] as Icon)
            }
        }
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerTextView: TextView = itemView.findViewById(R.id.headerTextView)

        fun bind(title: String) {
            val translatedTitle = when (title) {
                "GiaiTri" -> "Giải Trí"
                "DoAn" -> "Đồ ăn"
                "MuaSam" -> "Mua sắm"
                "CuocSong" -> "Cuộc sống"
                "CaNhan" -> "Cá nhân"
                "GiaoDuc" -> "Giáo dục"
                "NgayHoi" -> "Ngày hội"
                "TheThao" -> "Thể thao"
                "VanPHong" -> "Văn phòng"
                "VanTai" -> "Vận tải"
                "SucKhoe" -> "Sức khỏe"
                "DuLich" -> "Du lịch"
                "TaiChinh" -> "Tài chính"
                "VanPhong" -> "Văn phòng"
                "PhuongTien" -> "Phương tiện"
                "Khac" -> "Khác"
                "Mau" -> "Mẫu"
                else -> title
            }

            headerTextView.text = translatedTitle
        }
    }

    class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.imageArtists)

        fun bind(icon: Icon, isSelected: Boolean) {
            Glide.with(itemView.context)
                .load("file:///android_asset/${icon.iconResource}")
                .into(iconImageView)
            iconImageView.setColorFilter(itemView.context.getColor(R.color.white))

            if (isSelected) {
                iconImageView.setColorFilter(itemView.context.getColor(R.color.black))
            } else {
                iconImageView.setColorFilter(itemView.context.getColor(R.color.white))
            }

            itemView.isSelected = isSelected
        }
    }

}