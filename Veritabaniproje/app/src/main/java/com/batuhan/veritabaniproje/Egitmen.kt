package com.batuhan.veritabaniproje

import java.sql.SQLException

class Egitmen(egitmenId: Int, ad: String, soyad: String, ehliyetTur: String) {
    val soyad: String=""
    val ad: String=""
    val ehliyetTur: String=""
    val egitmenId: Int=0

    fun updateEgitmen(egitmen: Egitmen): Boolean {
        val connection = DatabaseHelper.getConnection()

        return try {
            val preparedStatement = connection?.prepareStatement("UPDATE egitmen_tablo SET ad = ?, soyad = ?, ehliyet_tur = ? WHERE egitmen_id = ?")
            preparedStatement?.apply {
                setString(1, egitmen.ad)
                setString(2, egitmen.soyad)
                setString(3, egitmen.ehliyetTur)
                setInt(4, egitmen.egitmenId)
            }

            preparedStatement?.executeUpdate()
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        } finally {
            connection?.close()
        }

    }  fun getEgitmenById(egitmenId: Int): Egitmen? {
        val connection = DatabaseHelper.getConnection()

        return try {
            val preparedStatement = connection?.prepareStatement("SELECT * FROM egitmen_tablo WHERE egitmen_id = ?")
            preparedStatement?.setInt(1, egitmenId)

            val resultSet = preparedStatement?.executeQuery()

            if (resultSet?.next() == true) {
                val ad = resultSet.getString("ad")
                val soyad = resultSet.getString("soyad")
                val ehliyetTur = resultSet.getString("ehliyet_tur")

                Egitmen(egitmenId, ad, soyad, ehliyetTur)
            } else {
                null
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        } finally {
            connection?.close()
        }
    } fun getAllEgitmenler(): List<Egitmen> {
        val connection = DatabaseHelper.getConnection()
        val egitmenList = mutableListOf<Egitmen>()

        return try {
            val statement = connection?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM egitmen_tablo")

            while (resultSet?.next() == true) {
                val egitmenId = resultSet.getInt("egitmen_id")
                val ad = resultSet.getString("ad")
                val soyad = resultSet.getString("soyad")
                val ehliyetTur = resultSet.getString("ehliyet_tur")

                val egitmen = Egitmen(egitmenId, ad, soyad, ehliyetTur)
                egitmenList.add(egitmen)
            }

            egitmenList
        } catch (e: SQLException) {
            e.printStackTrace()
            emptyList()
        } finally {
            connection?.close()
        }
    }
}fun deleteEgitmen(egitmenId: Int): Boolean {
    val connection = DatabaseHelper.getConnection()

    return try {
        val preparedStatement = connection?.prepareStatement("DELETE FROM egitmen_tablo WHERE egitmen_id = ?")
        preparedStatement?.apply {
            setInt(1, egitmenId)
        }

        preparedStatement?.executeUpdate()
        true
    } catch (e: SQLException) {
        e.printStackTrace()
        false
    } finally {
        connection?.close()
    }


}    fun addEgitmen(egitmen: Egitmen): Boolean {
    val connection = DatabaseHelper.getConnection()

    return try {
        val preparedStatement = connection?.prepareStatement("INSERT INTO egitmen_tablo (egitmen_id, ad, soyad, ehliyet_tur) VALUES (?, ?, ?, ?)")
        preparedStatement?.apply {
            setInt(1, egitmen.egitmenId)
            setString(2, egitmen.ad)
            setString(3, egitmen.soyad)
            setString(4, egitmen.ehliyetTur)
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
fun getEgitmenByEhliyetTur(ehliyetTur: String): List<Egitmen> {
    val connection = DatabaseHelper.getConnection()
    val egitmenList = mutableListOf<Egitmen>()

    return try {
        val preparedStatement = connection?.prepareStatement("SELECT * FROM egitmen_tablo WHERE ehliyet_tur = ?")
        preparedStatement?.setString(1, ehliyetTur)

        val resultSet = preparedStatement?.executeQuery()

        while (resultSet?.next() == true) {
            val egitmenId = resultSet.getInt("egitmen_id")
            val ad = resultSet.getString("ad")
            val soyad = resultSet.getString("soyad")

            val egitmen = Egitmen(egitmenId, ad, soyad, ehliyetTur)
            egitmenList.add(egitmen)
        }

        egitmenList
    } catch (e: SQLException) {
        e.printStackTrace()
        emptyList()
    } finally {
        connection?.close()
    }
}
