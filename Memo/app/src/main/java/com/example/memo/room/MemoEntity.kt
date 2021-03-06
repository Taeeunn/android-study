package com.example.memo.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-06
 */

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var memo: String = "")