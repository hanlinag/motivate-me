package com.motivation.team3.motivateme.fragment

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.motivation.team3.motivateme.R

class TellBeadsFragment : Fragment() {
    var text: TextView? = null
    var countText: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.tell_beads_layout, container, false)
        val btClick = rootView.findViewById<View>(R.id.btClick) as Button
        val btReset = rootView.findViewById<View>(R.id.btReset) as Button
        text = rootView.findViewById<View>(R.id.tvclickcount) as TextView
        countText = rootView.findViewById<View>(R.id.tvroundcount) as TextView
        btClick.setOnClickListener {
            val num = text!!.text.toString()
            var intNum = num.toInt()
            intNum++
            text!!.text = intNum.toString()
            if (intNum == 108) {
                val countNumber = countText!!.text.toString().trim { it <= ' ' }
                var intcountNumber = countNumber.toInt()
                intcountNumber++
                countText!!.text = intcountNumber.toString()
                text!!.text = "0"
            }
        }
        btReset.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext(), R.style.AppCompatAlertDialogStyle)
            builder.setTitle("Confirm")
            builder.setMessage("Are you sure want to reset?")
            builder.setPositiveButton("OK", Reset())
            builder.setNegativeButton("Cancel", null)
            builder.show()
        }
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private inner class Reset : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, which: Int) {
            text!!.text = "0"
            countText!!.text = "0"
        }
    }
}