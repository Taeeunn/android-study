package com.example.memo

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.room.MemoDatabase
import com.example.memo.room.MemoEntity
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity(), OnDeleteListener {

    lateinit var db: MemoDatabase
    var memoList = listOf<MemoEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MemoDatabase.getInstance(this)!!

        btn_add.setOnClickListener {
            val memo = MemoEntity(
                null,
                edittext_memo.text.toString()
            )
            edittext_memo.setText("")
            insertMemo(memo)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        getAllMemos()
    }


    fun insertMemo(memo: MemoEntity) {
        //1. MainThread vs WorkerThread(Background Thread)

        val insertTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.memoDAO().insert(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemos()
            }
        }
        insertTask.execute()
    }

    fun getAllMemos() {
        val getTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                memoList = db.memoDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(memoList)
            }

        }).execute()
    }

    fun deleteMemo(memo: MemoEntity) {
        val deleteTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.memoDAO().delete(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemos()
            }

        }).execute()
    }

    fun setRecyclerView(memoList: List<MemoEntity>) {
        recyclerView.adapter = MyAdapter(this, memoList, this)

    }

    override fun onDeleteListener(memo: MemoEntity) {
        deleteMemo(memo)
    }
}