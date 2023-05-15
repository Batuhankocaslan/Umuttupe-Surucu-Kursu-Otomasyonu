package com.batuhan.veritabaniproje

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button=findViewById<ImageButton>(R.id.imageButton)
        button.setOnClickListener{
         val intent= Intent(this,login_screen::class.java)
         startActivity(intent)
        }
        val button2=findViewById<ImageButton>(R.id.imageButton2)
        button2.setOnClickListener{
            val intent=Intent(this,kullanici::class.java)
            startActivity(intent)
        }
    }
}