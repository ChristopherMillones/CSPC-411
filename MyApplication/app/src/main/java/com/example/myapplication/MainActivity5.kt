package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.example.myapplication.data.User
import com.example.myapplication.data.UserViewModel
import java.util.*

class MainActivity5 : AppCompatActivity() {

    var spinner_select_type: String? = null
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val editText = findViewById<EditText>(R.id.etClassNumber1)
        val editText2 = findViewById<EditText>(R.id.etDate1)
        val spinner = findViewById<Spinner>(R.id.spnrTask1)
        val button3 = findViewById<Button>(R.id.btnSelectDate)
        val button = findViewById<Button>(R.id.btnBack)
        val button2 = findViewById<Button>(R.id.btnUpdate)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val intent = intent
        val date = intent.getStringExtra("Date").toString()
        val myClass = intent.getStringExtra("Class").toString()
        val type = intent.getStringExtra("Type").toString()

        editText.setText(myClass)
        editText2.setText(date)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        button2.setOnClickListener {
            val new_class = editText.text.toString()
            val new_date = editText2.text.toString()
            val new_type = spinner_select_type.toString()
            val updatedUser = User(new_class, new_date, new_type)
            print(updatedUser.class_num + "    " + updatedUser.date + "    " + updatedUser.type + "\n")
            mUserViewModel.updateUser(new_class, new_date, new_type, date, myClass, type)
            Toast.makeText(this, "Successfully updated!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        button.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mday ->
                    val m1 = mMonth + 1
                    val date = "$m1/$mday/$mYear"
                    editText2.setText(date)
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.type_of_task,
            R.layout.color_spinner_layout,

            ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinner_select_type = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}