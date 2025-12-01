package com.example.kirichenkoia_01_01;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text


class ConverterActivity: AppCompatActivity() {
    lateinit var btn : Button
    lateinit var etNumber: EditText
    lateinit var etSpinner_from: Spinner
    lateinit var etSpinner_to: Spinner
    lateinit var tvResultNum: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        btn = findViewById(R.id.btn_complete)
        etNumber = findViewById(R.id.number)
        etSpinner_from = findViewById(R.id.spinner_from)
        etSpinner_to = findViewById(R.id.spinner_to)
        tvResultNum = findViewById(R.id.result_num)

        val units = arrayOf("Байт", "КилоБайт", "МегаБайт", "ГигаБайт")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        etSpinner_from.adapter = adapter
        etSpinner_to.adapter = adapter


        btn.setOnClickListener{
            ConvertUnits()
        }
    }
    fun ConvertUnits(){
        val input = etNumber.text.toString()
        if (input.isEmpty()){
            tvResultNum.text = ""
            Toast.makeText(this,"Введите число", Toast.LENGTH_SHORT).show()
            return
        }

        val number = input.toDoubleOrNull()
        if (number == null){
            tvResultNum.text = ""
            Toast.makeText(this,"Введите число", Toast.LENGTH_SHORT).show()
            return
        }

        val fromUnit = etSpinner_from.selectedItem.toString()
        val toUnit = etSpinner_to.selectedItem.toString()

        val bytes =when(fromUnit){
            "Байт" -> number
            "КилоБайт" -> number * 1024
            "МегаБайт" -> number * 1024 * 1024
            "ГигаБайт" -> number * 1024 * 1024 * 1024
            else -> number
        }

        val result =when(toUnit){
            "Байт" -> bytes
            "КилоБайт" -> bytes / 1024
            "МегаБайт" -> bytes / (1024 * 1024)
            "ГигаБайт" -> bytes / (1024 * 1024 * 1024)
            else -> bytes
        }
        tvResultNum.text =result.toString()
    }
}

