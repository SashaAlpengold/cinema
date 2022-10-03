package com.example.jopa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class SignInActivity : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var password:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        email = findViewById(R.id.edtEmail)
        password = findViewById(R.id.edtPassword)
    }

    fun login(view: View) {
        if (email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
            if (email.text.toString() == "admin" && password.text.toString() == "admin") {

            } else{
                var alert2 = AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Неверный логин или пароль")
                    .setPositiveButton("ok",null)
                    .create()
                    .show()
            }
        } else {
            var alert1 = AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Не все поля заполнены")
                .setPositiveButton("ok",null)
                .create()
                .show()
        }
    }
}