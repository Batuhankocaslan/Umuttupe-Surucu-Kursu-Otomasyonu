package com.batuhan.veritabaniproje

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class kullanici : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kullanici)
        val button=findViewById<ImageButton>(R.id.imageButton7)
        button.setOnClickListener{
            val intent= Intent(this,Sorular_Activity::class.java)
            startActivity(intent)
        }
        val button2=findViewById<ImageButton>(R.id.imageButton12)
        button2.setOnClickListener{
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

    }
}