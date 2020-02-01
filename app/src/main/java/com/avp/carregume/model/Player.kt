package com.avp.carregume.model

class Player(
    val name: String,
    val value: String
)

// test

data class PlayerTest(
    val pseudo: String,
    var vegetable: Vegetable? = null
)

sealed class Vegetable(open val id: Int, open val weaponName: String, open val icon: Int)

data class Dagger(
    override val id: Int = 1,
    override val weaponName: String = "Dague",
    override val icon: Int = android.R.drawable.alert_dark_frame
) : Vegetable(id, weaponName, icon)

data class Sword(
    override val id: Int = 2,
    override val weaponName: String = "Épée",
    override val icon: Int = android.R.drawable.alert_dark_frame
) : Vegetable(id, weaponName, icon)

data class Axe(
    override val id: Int = 3,
    override val weaponName: String = "Hache",
    override val icon: Int = android.R.drawable.alert_dark_frame
) : Vegetable(id, weaponName, icon)
