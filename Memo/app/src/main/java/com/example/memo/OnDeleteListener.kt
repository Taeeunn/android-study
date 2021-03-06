package com.example.memo

import com.example.memo.room.MemoEntity

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-06
 */
interface OnDeleteListener {
    fun onDeleteListener(memo: MemoEntity)
}