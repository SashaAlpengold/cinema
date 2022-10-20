package com.example.jopa

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*


class SignUpActivity : AppCompatActivity() {
    lateinit var filePath: Uri

    lateinit var backButton: ImageButton
    lateinit var avatarCard: CardView
    lateinit var avatarImage: ImageView
    lateinit var birthdayEdt: EditText
    lateinit var numberEdt: EditText
    lateinit var nameEdt: EditText
    lateinit var surnameEdt: EditText
    lateinit var dadNameEdt: EditText
    lateinit var emailEdt: EditText
    lateinit var passwordEdt: EditText
    lateinit var passwordRptEdt: EditText
    private lateinit var signUpButton: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var authLogin: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        backButton = findViewById(R.id.button_back)
        avatarCard = findViewById(R.id.image_card)
        avatarImage = findViewById(R.id.image_avatar)
        birthdayEdt = findViewById(R.id.edtBirthday)
        numberEdt = findViewById(R.id.edtNumber)
        emailEdt = findViewById(R.id.edtEmail)
        passwordEdt = findViewById(R.id.edtPassword)
        passwordRptEdt = findViewById(R.id.edtRptPassword)
        signUpButton = findViewById(R.id.signUp)
        nameEdt = findViewById(R.id.edtName)
        surnameEdt = findViewById(R.id.edtSurname)
        dadNameEdt = findViewById(R.id.edtDadName)


        backButton.setOnClickListener{
            finish()
        }
        avatarCard.setOnClickListener{
            chooseImage()
        }

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

        auth = Firebase.auth
        authLogin = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        if (::signUpButton.isInitialized) {
            signUpButton.setOnClickListener {
                signUpUser()
            }
        }

    }

    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 333)
    }

    private fun uploadImage(uid: String) {
        if (filePath != null) {
//            val progressDialog = ProgressDialog(this)
//            progressDialog.setTitle("Uploading...")
//            progressDialog.show()
            val ref = storageReference.child("images/avatars/$uid")
            ref.putFile(filePath)
                .addOnSuccessListener {
//                    progressDialog.dismiss()
                    Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
//                    progressDialog.dismiss()
                    Toast.makeText(this, "Failed " + e.message, Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnProgressListener { taskSnapshot ->
//                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot
//                        .totalByteCount
//                    progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent? ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 333 && resultCode == RESULT_OK && data != null && data.data != null) {
            val pathFile = data?.data//The uri with the location of the file
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, pathFile)
            avatarImage.setImageBitmap(bitmap)
            if (pathFile != null) {
                filePath = pathFile
            }
        }
    }

    private fun signUpUser() {
        val email = emailEdt.text.toString()
        val password = passwordEdt.text.toString()
        val rptPassword = passwordRptEdt.text.toString()

        // check pass
        if (email.isBlank() || password.isBlank() || rptPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != rptPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                login()
                if (auth.currentUser !== null) {
                    var uid = auth.currentUser?.uid
                    uploadImage(uid.toString())
                }
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login() {
        val email = emailEdt.text.toString()
        val pass = passwordEdt.text.toString()
        authLogin.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
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
    }
}