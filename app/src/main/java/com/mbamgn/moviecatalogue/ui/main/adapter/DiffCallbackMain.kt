package com.mbamgn.moviecatalogue.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mbamgn.moviecatalogue.data.DataItem

class DiffCallbackMain(
    private val mOldDataItemList: List<DataItem>,
    private val mNewDataItemList: List<DataItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = mOldDataItemList.size
    override fun getNewListSize(): Int = mNewDataItemList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldDataItemList[oldItemPosition].id == mNewDataItemList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList = mOldDataItemList[oldItemPosition]
        val newList = mNewDataItemList[newItemPosition]

        return oldList.id == newList.id
    }

}
