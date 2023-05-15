package com.batuhan.veritabaniproje

import java.sql.SQLException

data class OgrenciEgitmen(val ogrenci: Ogrenci, val egitmen: Egitmen)

fun getOgrenciEgitmenListesi(): List<OgrenciEgitmen> {
    val connection = DatabaseHelper.getConnection()
    val ogrenciEgitmenList = mutableListOf<OgrenciEgitmen>()

    return try {
        val statement = connection?.createStatement()
        val resultSet = statement?.executeQuery("SELECT * FROM ogrenci_tablo INNER JOIN egitmen_tablo ON ogrenci_tablo.egitmen_id = egitmen_tablo.egitmen_id")

        while (resultSet?.next() == true) {
            val ogrenciTc = resultSet.getInt("ogrenci_tc")
            val ogrenciAd = resultSet.getString("ad")
            val ogrenciSoyad = resultSet.getString("soyad")
            val egitmenId = resultSet.getInt("egitmen_id")
            val egitmenAd = resultSet.getString("ad")
            val egitmenSoyad = resultSet.getString("soyad")
            val ehliyetTur = resultSet.getString("ehliyet_tur")

            val ogrenci = Ogrenci(ogrenciTc, ogrenciAd, ogrenciSoyad)
            val egitmen = Egitmen(egitmenId, egitmenAd, egitmenSoyad, ehliyetTur)

            val ogrenciEgitmen = OgrenciEgitmen(ogrenci, egitmen)
            ogrenciEgitmenList.add(ogrenciEgitmen)
        }

        ogrenciEgitmenList
    } catch (e: SQLException) {
        e.printStackTrace()
        emptyList()
    } finally {
        connection?.close()
    }
}fun main() {
    val ogrenciEgitmenListesi = getOgrenciEgitmenListesi()

    // Öğrenci ve eğitmen bilgilerini eşleştirilmiş şekilde listeleyin
    for (item in ogrenciEgitmenListesi) {
        val ogrenci = item.ogrenci
        val egitmen = item.egitmen

        println("Öğrenci: ${ogrenci.ad} ${ogrenci.soyad}")
        println("Eğitmen: ${egitmen.ad} ${egitmen.soyad}")
        println("------------------------")
    }
}
