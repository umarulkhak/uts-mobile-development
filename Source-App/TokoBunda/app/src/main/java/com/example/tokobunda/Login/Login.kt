package com.example.tokobunda.Login

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView
import android.widget.ProgressBar
import android.os.Bundle
import com.example.tokobunda.R
import android.content.Intent
import android.os.Handler
import com.example.tokobunda.Signup.SignUp
import android.os.Looper
import android.view.View
import android.widget.Button
import com.vishnusivadas.advanced_httpurlconnection.PutData
import android.widget.Toast
import com.example.tokobunda.Dashnoard.MainActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val textInputEditTextUsername = findViewById<TextInputEditText>(R.id.username)
        val textInputEditTextPassword = findViewById<TextInputEditText>(R.id.password)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val textViewSignUp = findViewById<TextView>(R.id.signUpText)
        val progressBar = findViewById<ProgressBar>(R.id.progress)

        textViewSignUp.setOnClickListener{
            val intent = Intent(applicationContext, SignUp::class.java)
            startActivity(intent)
            finish()
        }
        buttonLogin.setOnClickListener(View.OnClickListener {
            val username: String
            val password: String
            username = textInputEditTextUsername?.getText().toString()
            password = textInputEditTextPassword?.getText().toString()
            if (username != "" && password != "") {
                progressBar.setVisibility(View.VISIBLE)
                //Start ProgressBar first (Set visibility VISIBLE)
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(2)
                    field[0] = "username"
                    field[1] = "password"
                    //Creating array for data
                    val data = arrayOfNulls<String>(2)
                    data[0] = username
                    data[1] = password
                    val putData = PutData("http://192.168.8.101/LoginRegister/login.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE)
                            val result = putData.result
                            if (result == "Login Success") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                val intent = Intent(applicationContext, MainActivity::class.java)
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