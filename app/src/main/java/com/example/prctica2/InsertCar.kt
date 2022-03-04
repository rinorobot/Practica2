package com.example.prctica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.prctica2.DataBase.DBCars
import com.example.prctica2.databinding.ActivityInsertCarBinding

class InsertCar : AppCompatActivity() {
    private lateinit var binding: ActivityInsertCarBinding
    private var marca_elegida: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbCars = DBCars(this)

        val marcas = resources.getStringArray(R.array.marcas_array)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,marcas)
        binding.sMarca.adapter = adapter

        binding.sMarca.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
              when(position){
                  0 ->{
                      binding.ivCar.setImageResource(R.drawable.fondo_bugatti)
                      marca_elegida = ""
                  }
                  1 ->{
                      binding.ivCar.setImageResource(R.drawable.ford)
                      marca_elegida = "Ford"
                  }
                  2 ->{
                      binding.ivCar.setImageResource(R.drawable.seat)
                      marca_elegida = "Seat"
                  }
                  3 ->{
                      binding.ivCar.setImageResource(R.drawable.suzuki)
                      marca_elegida = "Suzuki"
                  }
                  4 ->{
                      binding.ivCar.setImageResource(R.drawable.nissan)
                      marca_elegida = "Nissan"
                  }
                  5 ->{
                      binding.ivCar.setImageResource(R.drawable.kia)
                      marca_elegida = "Kia"
                  }
              }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.addCar.setOnClickListener {


            if (binding.etNombre.text.toString().isEmpty()){
                binding.tilNombre.hint = "Por favor agrega el nombre"
                binding.tilNombre.requestFocus()
            }else if (binding.etModelo.text.toString().isEmpty()){
                binding.tilModelo.hint = "Por favor agrega el año"
                binding.tilModelo.requestFocus()
            }else if (binding.etPrice.text.toString().isEmpty()){
                binding.tilPrecio.hint = "Por favor agrega el precio"
                binding.tilPrecio.requestFocus()
            }else if (marca_elegida!!.equals("")){
                binding.sMarca.requestFocus()
                Toast.makeText(this,getString(R.string.elige_marca),Toast.LENGTH_LONG).show()

            }
                else{
                val id = dbCars.insertGame(binding.etNombre.text.toString(),binding.etModelo.text.toString(),marca_elegida.toString(),binding.etPrice.text.toString())

                if (id>0){
                    Toast.makeText(this,getString(R.string.nuevo_coche),Toast.LENGTH_LONG).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }



        }





    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}