package com.example.myrecipes.ui.extension

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

fun Fragment.showDialog() {
    AlertDialog.Builder(requireContext())
        .setTitle("Permission required")
        .setMessage("Please grant all permissions to use this app")
        .setNegativeButton("Cancel") { dialog: DialogInterface, _ ->
            dialog.dismiss()
            activity?.finish()
        }
        .setPositiveButton("Ok") { dialog: DialogInterface, _ ->
            dialog.dismiss()
//            requestPermission()
        }.show()
}