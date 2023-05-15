package com.batuhan.veritabaniproje

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_anaekran.*

class anaekran : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anaekran)

        mavidaire.setOnClickListener{
            val intent= Intent(this,ogrenci_tablo2::class.java)
            startActivity(intent)
        }

        ogrenci.setOnClickListener{
            val intent= Intent(this,ana_ekran_ogrenciler::class.java)
            startActivity(intent)
        }
        sinavtakvimi.setOnClickListener{
            val intent= Intent(this,sinavTakvimi::class.java)
            startActivity(intent)
        }
        eğitmen.setOnClickListener{
            val intent= Intent(this,ana_ekran_egitmenler::class.java)
            startActivity(intent)
        }
    }
}