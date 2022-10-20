package com.example.jopa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    lateinit var emailEdt:EditText
    lateinit var passwordEdt:EditText
    lateinit var loginBtn: Button

    lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        emailEdt = findViewById(R.id.edtEmail)
        passwordEdt = findViewById(R.id.edtPassword)

        auth = FirebaseAuth.getInstance()
        loginBtn = findViewById(R.id.loginButton)

        loginBtn.setOnClickListener {
            login()
        }

    }

    private fun login() {
        val email = emailEdt.text.toString()
        val pass = passwordEdt.text.toString()
        if (emailEdt.text.toString().isNotEmpty() && passwordEdt.text.toString().isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                if(it.isSuccessful) {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                }else {
                    var alertEmpty = AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("Неверный логин или пароль!")
                        .setPositiveButton("ok",null)
                        .create()
                        .show()
                }
            }
        } else {
            var alertEmpty = AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Не все поля заполнены!")
                .setPositiveButton("ok",null)
                .create()
                .show()
        }
    }

    fun signUp(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}