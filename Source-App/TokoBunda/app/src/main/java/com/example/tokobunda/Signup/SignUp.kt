package com.example.tokobunda.Signup

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tokobunda.Login.Login
import com.example.tokobunda.R
import com.google.android.material.textfield.TextInputEditText
import com.vishnusivadas.advanced_httpurlconnection.PutData

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val textInputEditTextFullname = findViewById<TextInputEditText>(R.id.fullname)
        val textInputEditTextUsername = findViewById<TextInputEditText>(R.id.username)
        val textInputEditTextEmail = findViewById<TextInputEditText>(R.id.email)
        val textInputEditTextPassword = findViewById<TextInputEditText>(R.id.password)
        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        val textViewLogin = findViewById<TextView>(R.id.loginText)
        val progressBar = findViewById<ProgressBar>(R.id.progress)
        textViewLogin.setOnClickListener{
            val intent = Intent(applicationContext, SignUp::class.java)
            startActivity(intent)
            finish()
        };
        buttonSignUp.setOnClickListener(View.OnClickListener {
            val fullname: String
            val username: String
            val password: String
            val email: String
            fullname = textInputEditTextFullname.getText().toString()
            username = textInputEditTextUsername.getText().toString()
            password = textInputEditTextPassword.getText().toString()
            email = textInputEditTextEmail.getText().toString()
            if (fullname != "" && username != "" && password != "" && email != "") {
                progressBar.setVisibility(View.VISIBLE)
                //Start ProgressBar first (Set visibility VISIBLE)
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(4)
                    field[0] = "fullname"
                    field[1] = "username"
                    field[2] = "password"
                    field[3] = "email"
                    //Creating array for data
                    val data = arrayOfNulls<String>(4)
                    data[0] = fullname
                    data[1] = username
                    data[2] = password
                    data[3] = email
                    val putData = PutData("http://192.168.8.101/LoginRegister/signup.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE)
                            val result = putData.result
                            if (result == "Sign Up Success") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                val intent = Intent(applicationContext, Login::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    //End Write and Read data with URL
                }
            } else {
                Toast.makeText(applicationContext, "All fields required", Toast.LENGTH_SHORT).show()
            }
        })
    }
}