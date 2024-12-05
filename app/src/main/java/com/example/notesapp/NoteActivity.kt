package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NoteActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageButton
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnBack = findViewById(R.id.btnBackNote)
        tvTitle = findViewById(R.id.tvTitleNote)
        tvDescription = findViewById(R.id.tvDescriptionNote)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        tvTitle.text = title
        tvDescription.text = description
    }
}