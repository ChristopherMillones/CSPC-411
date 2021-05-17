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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.User
import com.example.myapplication.data.UserViewModel

class MainActivity4 : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private lateinit var mUserViewModel: UserViewModel
    private val adapter = ExampleAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val button = findViewById<Button>(R.id.btnHome)
        val spinner: Spinner = findViewById(R.id.spnrTask)
        val rv = findViewById<RecyclerView>(R.id.rv_table)
        val view = this

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)


        //Init UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer { user ->
            adapter.setData(user)
        })
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.filter_by,
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
                when(position){
                    1 ->{mUserViewModel.filterByDate.observe(this@MainActivity4, Observer { user ->
                        adapter.setData(user)
                    })}
                    2 ->{mUserViewModel.readAllData.observe(this@MainActivity4, Observer { user ->
                        adapter.setData(user)
                    })}
                    3 ->{mUserViewModel.filterByType.observe(this@MainActivity4, Observer { user ->
                        adapter.setData(user)
                    })}
                    else ->{mUserViewModel.filterByType.observe(this@MainActivity4, Observer { user ->
                        adapter.setData(user)
                    })}
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    override fun onItemClick(user: User) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Edit")
        builder.setMessage("Are you sure you want to edit this task?")
        builder.setPositiveButton("Edit", DialogInterface.OnClickListener { dialog, id ->
            val intent = Intent(this, MainActivity5::class.java)
            intent.putExtra("Date", user.date)
            intent.putExtra("Class", user.class_num)
            intent.putExtra("Type", user.type)
            startActivity(intent)
            dialog.cancel()
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
            dialog.cancel()
        })
        var alert: AlertDialog = builder.create()
        alert.show()
    }
}