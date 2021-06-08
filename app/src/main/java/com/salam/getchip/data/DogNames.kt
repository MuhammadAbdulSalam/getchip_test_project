package com.salam.getchip.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dog")
data class DogNames(
    @PrimaryKey @ColumnInfo(name = "id") val name: String,
)
