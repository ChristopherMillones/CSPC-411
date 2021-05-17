package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.User
import com.example.myapplication.data.UserViewModel


class MainActivity3 : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private val adapter = ExampleAdapter(this)
    private lateinit var mUserViewModel: UserViewModel

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
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer { user ->
            adapter.setData(user)
        })

    }


    override fun onItemClick(user: User) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Delete")
        builder.setMessage("Are you sure you want to delete this task?")
        builder.setPositiveButton("Delete", DialogInterface.OnClickListener{dialog, id ->
            mUserViewModel.DeleteUser(user.date,user.class_num,user.type.toString())
            Toast.makeText(this, "Successfully deleted!", Toast.LENGTH_SHORT).show()
            dialog.cancel()
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener{dialog, id ->
            dialog.cancel()
        } )
        var alert : AlertDialog = builder.create()
        alert.show()

    }
}

