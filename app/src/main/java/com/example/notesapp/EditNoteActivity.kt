package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class EditNoteActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageButton
    private lateinit var btnSave: Button
    private lateinit var etTextTitle: EditText
    private lateinit var etTextDescription: EditText
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnBack = findViewById(R.id.btnBackEdit)
        btnSave = findViewById(R.id.btnSaveEdit)
        etTextTitle = findViewById(R.id.etTextTitleEdit)
        etTextDescription = findViewById(R.id.etTextDescriptionEdit)

        val dao = NoteDatabase.getDatabase(this).noteDao()
        val repository = NotesRepository(dao)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        val etId = intent.getIntExtra("id", 0)
        val etTitle = intent.getStringExtra("title")
        val etDescription = intent.getStringExtra("description")

        etTextTitle.setText(etTitle)
        etTextDescription.setText(etDescription)

        btnSave.setOnClickListener {
            val title = etTextTitle.text.toString()
            val description = etTextDescription.text.toString()

            if (title.isEmpty() && description.isEmpty()) {
                etTextTitle.error = "Title is required"
                etTextDescription.error = "Description is required"
            }
            if (title.isEmpty()) etTextTitle.error = "Title is required"
            if (description.isEmpty()) etTextDescription.error = "Description is required"

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val note = Note(etId, title, description)
                mainViewModel.updateNote(note)
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

    }
}