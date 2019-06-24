package com.leonardoalves.ametest.custom

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.leonardoalves.ametest.R

class ListDividerItemDecoration(context: Context) : androidx.recyclerview.widget.DividerItemDecoration(context, VERTICAL) {
    init {
        ContextCompat.getDrawable(context, R.drawable.shape_list_inner_boundary)?.let { setDrawable(it) }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == parent.adapter?.itemCount?.minus(1)) {
            outRect.setEmpty()
        } else {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }
}