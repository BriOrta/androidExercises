package com.example.project1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipe",
    indices = [Index(value = ["iddd"])]
)

data class Recipe(
    @ColumnInfo(name = "recipe_name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "carbs") val carbos: String,
    @ColumnInfo(name = "proteins") val proteins: String,
    @ColumnInfo(name = "calories") val calories: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "iddd")
    val iddd: Int = 0
)
