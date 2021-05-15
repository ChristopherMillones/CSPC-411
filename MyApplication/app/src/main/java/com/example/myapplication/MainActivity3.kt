package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class MainActivity3 : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private val exampleList = generateDummyList(10)
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val button = findViewById<Button>(R.id.btnHome)
        val rv = findViewById<RecyclerView>(R.id.rv_table)

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)

        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun removeItem(position: Int){
        exampleList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onItemClick(position: Int) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Delete")
        builder.setMessage("Are you sure you want to delete this task?")
        builder.setPositiveButton("Delete", DialogInterface.OnClickListener{dialog, id ->
            removeItem(position)
            dialog.cancel()
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener{dialog, id ->
            dialog.cancel()
        } )
        var alert : AlertDialog = builder.create()
        alert.show()

    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem>{
        val list = ArrayList<ExampleItem>()

        for (i in 0 until size){
            val drawble = when (i%4) {
                0 -> R.drawable.finals_image
                1 -> R.drawable.midterm_image
                2 -> R.drawable.project_image
                else -> R.drawable.quiz_image
            }
            val item = ExampleItem(drawble, "Item $i", "Line 2", "Line 3")
            list += item
        }
        return list
    }

}

