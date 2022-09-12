package fr.damienc.astrologics

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SignItemDecoration : RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = 10
        outRect.top = 10
        outRect.left = 50
    }
}