package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity4 : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private val exampleList = generateDummyList(10)
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val button = findViewById<Button>(R.id.btnHome)
        val spinner: Spinner = findViewById(R.id.spnrTask)
        val rv = findViewById<RecyclerView>(R.id.rv_table)

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)

        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.filter_by,
            R.layout.color_spinner_layout,

            ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
            spinner.adapter=adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
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

    override fun onItemClick(position: Int) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Edit")
        builder.setMessage("Are you sure you want to edit this task?")
        builder.setPositiveButton("Edit", DialogInterface.OnClickListener{ dialog, id ->

            dialog.cancel()
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialog, id ->
            dialog.cancel()
        } )
        var alert : AlertDialog = builder.create()
        alert.show()
    }
}