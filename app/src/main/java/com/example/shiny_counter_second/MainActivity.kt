package com.example.shiny_counter_second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hunt_screen)

        //values that set up the count feature
        val countUp: Button = findViewById(R.id.btn_counter_plus)
        val countDown: Button = findViewById(R.id.btn_counter_minus)
        val countDisplay: TextView = findViewById(R.id.counter_display)
        var count = 0

        //Strings
        val oddsInput: EditText = findViewById(R.id.shiny_odds)
        val huntName: EditText = findViewById(R.id.hunt_name)

        //Spinner for determining if the hunt has worked out
        val progressStatus: Spinner = findViewById(R.id.hunt_status)

        //Buttons for changing screens
        val btnSettings: Button = findViewById(R.id.btnGoToSettings)
        val btnSaves: Button = findViewById(R.id.btnGoToSaves)
        val settingsScreen = Intent(this@MainActivity, Settings::class.java)
        val savesScreen = Intent(this@MainActivity, Saves::class.java)

        //Buttons for saving items into next list
        val saveFAB: FloatingActionButton = findViewById(R.id.save_FAB)

        countDisplay.text = "$count"

        //Carrying over data from the save screen
        val extras = intent.extras
        if (extras != null) {
            val count = extras.getInt("Count")
            countDisplay.text = count.toString()
            huntName.text = Editable.Factory.getInstance().newEditable(extras.getString("Name"))    //Learned how to set editable text using ChatGPT
            oddsInput.text = Editable.Factory.getInstance().newEditable(extras.getString("Odds"))
            val statusChange = extras.getString("Status")
//            progressStatus.selectedItem.toString() = statusChange.toString()
        }

        //Code for the counting up button
        countUp.setOnClickListener {
            count+= 1
            countDisplay.text = "$count"
        }

        //code for the counting down button
        countDown.setOnClickListener {
            if (count > 0) {
                count -= 1
                countDisplay.text = "$count"
            } else {
                Toast.makeText(this, "You cannot have negative encounters", Toast.LENGTH_SHORT).show()
            }
        }

        //Button for moving into settings
        //Originally planned to move over some values, decided not to realizing it was more incontinent (for the user)
        btnSettings.setOnClickListener {
//
//            settingsScreen.putExtra("Count", count)
//
//            if (huntName != null) {
//                settingsScreen.putExtra("Name", huntName.text.toString())
//            }
//
//            if (oddsInput != null) {
//                settingsScreen.putExtra("Odds", oddsInput.text.toString())
//            }
//
//            settingsScreen.putExtra("Status", progressStatus.selectedItem.toString())

            startActivity(settingsScreen)
        }

        //BUtton for moving to the saves screen
        btnSaves.setOnClickListener {

            startActivity(savesScreen)
        }

        //Save button, moves value into saves screen
        saveFAB.setOnClickListener {
            val newName = huntName.text.toString()
            val newOdds = oddsInput.text.toString()
            val newCount = count.toString()
            val newStatus = progressStatus.selectedItem.toString()

            val newString = "$newName | $newOdds | Count: $newCount | $newStatus"

            savesScreen.putExtra("NewLine", newString)

            savesScreen.putExtra("Count", count)

            if (huntName != null) {
                savesScreen.putExtra("Name", huntName.text)
            }

            if (oddsInput != null) {
                savesScreen.putExtra("Odds", oddsInput.text.toString())
            }

            Toast.makeText(this, "$newString", Toast.LENGTH_SHORT).show()

            savesScreen.putExtra("Status", progressStatus.selectedItem.toString())
        }

    }
}