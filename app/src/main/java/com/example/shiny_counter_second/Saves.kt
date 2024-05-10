package com.example.shiny_counter_second

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Saves : AppCompatActivity() {

    var listItems = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null
    lateinit var listView: ListView
    var inputText: String = ""
    var latestEntry: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saves_screen)

        listView = findViewById(R.id.lst_saves)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listItems
        )

        listView.adapter = adapter

        //button for moving into the "hunt page"
        val btnHunt: Button = findViewById(R.id.btnGoToHuntFromSaves)
        val huntScreenFromSaves = Intent(this@Saves, MainActivity::class.java)

        //adds a string of information if there is any
        val extras = intent.extras
        if (extras != null) {
            val name = extras.getString("Name")
            Toast.makeText(this, "$name", Toast.LENGTH_SHORT).show()
            inputText = extras.getString("NewLine").toString()
            addListItem()
        }

        //return to "hunt"
        btnHunt.setOnClickListener {
            startActivity(huntScreenFromSaves)
        }
    }

    //Function used to add items to the list
    private fun addListItem() {
        listItems.add(inputText)
        adapter?.notifyDataSetChanged()
    }
}