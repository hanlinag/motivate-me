package com.motivation.team3.motivateme.listener

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnItemTouchListener
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View


class RecyclerItemClickListener(context: Context, listener: OnItemClickListener?) :
    OnItemTouchListener {
    private val context: Context = context
    private val mListener: OnItemClickListener? = listener

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    private var mGestureDetector: GestureDetector =
        GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView: View? = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

}