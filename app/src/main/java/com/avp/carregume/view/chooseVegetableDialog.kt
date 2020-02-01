package com.avp.carregume.view

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import com.avp.carregume.R

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.*


class chooseVegetableDialog : DialogFragment() {


    private lateinit var vetetable1Layout: TextInputLayout
    private lateinit var vetetable2Layout: TextInputLayout

    private lateinit var vetetable1EditText: TextInputEditText
    private lateinit var vetetable2EditText: TextInputEditText

    private lateinit var vetetable1: String
    private lateinit var vetetable2: String

    private lateinit var rootView: View
    private lateinit var activity: GameActivity

    private lateinit var text_view: TextInputLayout
    private lateinit var text_view2: TextInputLayout

    // Initializing a String Array

    val vegetables = arrayOf("salade", "tomate", "ognion")
    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner

    companion object {
        fun newInstance(gameActivity: GameActivity): chooseVegetableDialog {
            val dialog = chooseVegetableDialog()
            dialog.activity = gameActivity
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initViews()


        val alertDialog = AlertDialog.Builder(context!!)
            .setView(rootView)
            .setTitle(R.string.game_dialog_title)
            .setCancelable(false)
            .setPositiveButton(R.string.done, null)
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.setOnShowListener {
            onDialogShow(alertDialog)
        }

        return alertDialog



    }


    private fun initViews() {

        rootView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_vegetable_begin, null, false)
        /*
        vetetable1Layout = rootView.findViewById(R.id.layout_player1)
        vetetable2Layout = rootView.findViewById(R.id.layout_player2)

        vetetable1EditText = rootView.findViewById(R.id.et_player1)

        vetetable2EditText = rootView.findViewById(R.id.et_player2)
        */
        spinner = rootView.findViewById(R.id.spinner)

        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, vegetables)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    vetetable1 = vegetables[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }
        spinner2 = rootView.findViewById(R.id.spinner2)
        if (spinner2 != null) {
            val arrayAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, vegetables)
            spinner2.adapter = arrayAdapter

            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    vetetable2 =   vegetables[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }
    }

    private fun onDialogShow(dialog: AlertDialog) {
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener { onDoneClicked() }
    }

    private fun isAValidName(layout: TextInputLayout, name: String): Boolean {
        if (TextUtils.isEmpty(name)) {
            layout.isErrorEnabled = true
            layout.error = getString(R.string.game_dialog_empty_name)
            return false
        }

        layout.isErrorEnabled = false
        layout.error = ""
        return true
    }

    private fun onDoneClicked() {
       // if (isAValidName(text_view, vetetable1) and isAValidName(text_view2, vetetable2)) {
            activity.onVegetableSet(vetetable1, vetetable2)
            dismiss()
        //}
    }


}
