package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val button = findViewById<Button>(R.id.btnHome)
        val spinner: Spinner = findViewById(R.id.spnrTask)
        val button2 = findViewById<Button>(R.id.btnSelectDate)
        val editText = findViewById<EditText>(R.id.etDate)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        button2.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mday ->
                val m1 = mMonth+1
                val date = "$m1/$mday/$mYear"
                editText.setText(date)
            }, year, month, day)
            dpd.show()
        }

        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.type_of_task,
            R.layout.color_spinner_layout,

        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
            spinner.adapter=adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>?,view: View?,position: Int,id: Long){
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }


    }
}
