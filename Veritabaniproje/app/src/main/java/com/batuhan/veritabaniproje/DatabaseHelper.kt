package com.batuhan.veritabaniproje

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseHelper {

    companion object {
        private const val DB_URL = "jdbc:mysql://localhost/ehliyetoto" // Veritabanı URL'si
        private const val DB_USER = "username" // Veritabanı kullanıcı adı
        private const val DB_PASSWORD = "password" // Veritabanı şifresi

        fun getConnection(): Connection? {
            return try {
                Class.forName("com.mysql.cj.jdbc.Driver")
                DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
            } catch (e: SQLException) {
                e.printStackTrace()
                null
            }
        }
    }
}