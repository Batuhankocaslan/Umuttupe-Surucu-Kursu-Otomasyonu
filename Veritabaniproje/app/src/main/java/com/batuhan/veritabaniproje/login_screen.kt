package com.batuhan.veritabaniproje

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.sql.SQLException

class login_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        val button=findViewById<Button>(R.id.girisYap)
        button.setOnClickListener{
            val intent= Intent(this,anaekran::class.java)
            startActivity(intent)
        }
        fun getAdmins(): List<Admin> {
            val connection = DatabaseHelper.getConnection()
            val admins = mutableListOf<Admin>()

            try {
                val statement = connection?.createStatement()
                val resultSet = statement?.executeQuery("SELECT * FROM admin_tablo")

                while (resultSet?.next() == true) {
                    val adminId = resultSet.getInt("admin_id")
                    val adminPassword = resultSet.getString("admin_password")
                    val admin = Admin(adminId, adminPassword)
                    admins.add(admin)
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            } finally {
                connection?.close()
            }

            return admins
        }
        fun addAdmin(admin: Admin): Boolean {
            val connection = DatabaseHelper.getConnection()

            return try {
                val preparedStatement = connection?.prepareStatement("INSERT INTO admin_tablo (admin_id, admin_password) VALUES (?, ?)")
                preparedStatement?.apply {
                    setInt(1, admin.adminId)
                    setString(2, admin.adminPassword)
                }

                preparedStatement?.executeUpdate()
                true
            } catch (e: SQLException) {
                e.printStackTrace()
                false
            } finally {
                connection?.close()
            }
        }
        fun deleteAdmin(adminId: Int): Boolean {
            val connection = DatabaseHelper.getConnection()

            return try {
                val preparedStatement = connection?.prepareStatement("DELETE FROM admin_tablo WHERE admin_id = ?")
                preparedStatement?.apply {
                    setInt(1, adminId)
                }

                preparedStatement?.executeUpdate()
                true
            } catch (e: SQLException) {
                e.printStackTrace()
                false
            } finally {
                connection?.close()
            }


    }
    } }