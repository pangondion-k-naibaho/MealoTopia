package com.mealotopia.client.view.advanced_ui

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window
import androidx.core.content.ContextCompat
import com.mealotopia.client.R
import com.mealotopia.client.databinding.PopupLayoutBinding

interface PopupDialogListener{
    fun onClickListener()
}

fun Activity.showPopupDialog(
    textDesc: String,
    backgroundImage: Int,
    listener: PopupDialogListener?= null
){
    val dialog = Dialog(this)
    val binding = PopupLayoutBinding.bind(layoutInflater.inflate(R.layout.popup_layout, null))
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setContentView(binding.root)
    dialog.setCancelable(listener == null)
    binding.apply {
        tvPopupDescription.text = textDesc
        ivPopupImage.background = ContextCompat.getDrawable(this@showPopupDialog, backgroundImage)
        btnPopupAction.setOnClickListener{
            listener?.onClickListener()
            dialog.dismiss()
        }
        if(!isFinishing) dialog.show()
    }
}