package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.User
import com.example.myapplication.data.UserViewModel
import java.util.*

class MainActivity2 : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    var spinner_select_type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val button = findViewById<Button>(R.id.btnHome)
        val spinner: Spinner = findViewById(R.id.spnrTask)
        val button2 = findViewById<Button>(R.id.btnSelectDate)
        val button3 = findViewById<Button>(R.id.btnAddConfirm)
        val editText = findViewById<EditText>(R.id.etDate)
        val editText2 = findViewById<EditText>(R.id.etClassNumber)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        button3.setOnClickListener{
            val class_num = editText2.text.toString()
            val date = editText.text.toString()
            val user = User(class_num, date, spinner_select_type)
            mUserViewModel.addUser(user)
            Toast.makeText(this, "Successfully added!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


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
               spinner_select_type = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

}
