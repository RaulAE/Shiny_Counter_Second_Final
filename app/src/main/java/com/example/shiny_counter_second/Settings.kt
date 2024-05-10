package com.example.shiny_counter_second

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Settings: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)

        //access to the setting layout
        val setName: EditText = findViewById(R.id.set_name)
        val btnName: Button = findViewById(R.id.btn_set_name)
        val setOdds: EditText = findViewById(R.id.set_odds)
        val btnOdds: Button = findViewById(R.id.btn_set_odds)
        val setCount: EditText = findViewById(R.id.set_counter)
        val btnCount: Button = findViewById(R.id.btn_set_counter)
        val setStatus: Spinner = findViewById(R.id.set_status)
        val btnStatus: Button = findViewById(R.id.btn_set_status)
        val setFAB: FloatingActionButton = findViewById(R.id.set_FAB)

        //buttons for accesing hunt
        val btnHunt: Button = findViewById(R.id.btnGoToHunt)
        val huntScreen = Intent(this@Settings, MainActivity::class.java)

        //sets hunt name value
        btnName.setOnClickListener {
            if (setName != null) {
                huntScreen.putExtra("Name", setName.text.toString())
                Toast.makeText(this, "${setName.text}", Toast.LENGTH_SHORT).show()
            }
        }

        //sets odds value
        btnOdds.setOnClickListener {
            if (setOdds != null) {
                huntScreen.putExtra("Odds", setOdds.text.toString())
                Toast.makeText(this, "${setOdds.text}", Toast.LENGTH_SHORT).show()
            }
        }

        //sets a valid count value
        btnCount.setOnClickListener {
            val setCountText = setCount.text.toString()
            if (setCountText.isNotBlank()) {
                val setCountInt = setCountText.toInt()
                huntScreen.putExtra("Count", setCountInt)
                Toast.makeText(this, "Value has been set to $setCountInt", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this , "You cannot have negative encounters", Toast.LENGTH_SHORT).show()
            }
        }

        //sets a progress status value
        btnStatus.setOnClickListener {
            huntScreen.putExtra("Status", setStatus.selectedItem.toString())
            Toast.makeText(this , "${setStatus}", Toast.LENGTH_SHORT).show()
        }

        //sets all values, contains all the previous code that sets values
        setFAB.setOnClickListener {
            val newName = setName.text.toString()
            val newOdds = setOdds.text.toString()
            val newCount = setCount.text.toString()
            val newStatus = setStatus.selectedItem.toString()

            val newString = "$newName | $newOdds | Count: $newCount | $newStatus"

            huntScreen.putExtra("NewLine", newString)

            huntScreen.putExtra("Name", newName)

            if (newCount.isNotBlank()) {
                val newCountInt = newCount.toInt()
                huntScreen.putExtra("Count", newCountInt)
                Toast.makeText(this, "$newCountInt", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this , "You cannot have negative encounters", Toast.LENGTH_SHORT).show()
            }

            if (setOdds != null) {
                huntScreen.putExtra("Odds", newOdds)
            }

            Toast.makeText(this, newString, Toast.LENGTH_LONG).show()

            huntScreen.putExtra("Status", setStatus.selectedItem.toString())
        }

        btnHunt.setOnClickListener {
            startActivity(huntScreen)
        }


    }
}