package com.example.jopa

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.widget.addTextChangedListener
import com.google.android.material.internal.TextWatcherAdapter
import java.util.*
import java.util.regex.Pattern

class ProfileActivity : AppCompatActivity() {
    lateinit var birthdayEdt: EditText
    lateinit var numberEdt : EditText

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar?.setCustomView(R.layout.profile_abs_layout);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        birthdayEdt = findViewById(R.id.edtBirthday)
        numberEdt = findViewById(R.id.edtNumber)

        birthdayEdt.setOnClickListener{
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    birthdayEdt.setText(dat)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        numberEdt.addTextChangedListener(object : TextWatcher {
            private var mSelfChange = false
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 == null || mSelfChange) {
                    return
                }

                val preparedStr = p0.replace(Regex("(\\D*)"), "")
                var resultStr = ""
                for (i in preparedStr.indices) {
                    resultStr = when (i) {
                        0 -> resultStr.plus("+7 (")
                        1 -> resultStr.plus(preparedStr[i])
                        2 -> resultStr.plus(preparedStr[i])
                        3 -> resultStr.plus(preparedStr[i])
                        4 -> resultStr.plus(") ".plus(preparedStr[i]))
                        5 -> resultStr.plus(preparedStr[i])
                        6 -> resultStr.plus(preparedStr[i])
                        7 -> resultStr.plus("-".plus(preparedStr[i]))
                        8 -> resultStr.plus(preparedStr[i])
                        9 -> resultStr.plus("-".plus(preparedStr[i]))
                        10 -> resultStr.plus(preparedStr[i])
                        else -> resultStr
                    }
                }

                mSelfChange = true
                val oldSelectionPos = numberEdt.selectionStart
                val isEdit = numberEdt.selectionStart != numberEdt.length()
                numberEdt.setText(resultStr)
                if (isEdit) {
                    numberEdt.setSelection(if (oldSelectionPos > resultStr.length) resultStr.length else oldSelectionPos)
                } else {
                    numberEdt.setSelection(resultStr.length)
                }
                mSelfChange = false
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        var numberPhone:String = numberEdt.getText().toString().replace("^(\\+\\)|\\D+","")
    }

    override fun onSupportNavigateUp() : Boolean{
        finish()
        return true
    }

    fun saveChanges(view: View) {
        findViewById<TextView>(R.id.test).text = numberEdt.text
    }


}