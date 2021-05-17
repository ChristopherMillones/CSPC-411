package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.myapplication.data.User
import com.example.myapplication.data.UserDatabase
import com.example.myapplication.data.UserRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btnAdd)
        val image_view = findViewById<ImageView>(R.id.imageView)
        val button2 = findViewById<Button>(R.id.btnDelete)
        val button3 = findViewById<Button>(R.id.btnViewEdit)

        button3.setOnClickListener{
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        image_view.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fullerton.edu/"))
            startActivity(i)
        }

    }
}