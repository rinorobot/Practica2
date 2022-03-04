package com.example.prctica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.prctica2.DataBase.DBCars
import com.example.prctica2.Models.Car
import com.example.prctica2.databinding.ActivityEditCarBinding

class EditCar : AppCompatActivity() {
    private lateinit var binding: ActivityEditCarBinding
    private var marca_elegida: String? = null
    private lateinit var dbCars: DBCars
    var car: Car? = null
    var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(savedInstanceState == null){
            val bundle = intent.extras
            if (bundle != null){
                id = bundle.getInt("id",0)
            }
        }else{
            id = savedInstanceState.getSerializable("id") as Int
        }

        dbCars = DBCars(this)
        car = dbCars.getCar(id)

        val marcas = resources.getStringArray(R.array.marcas_array)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,marcas)
        binding.sMarca.adapter = adapter

        binding.sMarca.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {


                when(position){
                    0 ->{
                        binding.ivMiniCar.setImageResource(R.drawable.fondo_bugatti)
                        marca_elegida = ""
                    }
                    1 ->{
                        binding.ivMiniCar.setImageResource(R.drawable.ford)
                        marca_elegida = "Ford"
                    }
                    2 ->{
                        binding.ivMiniCar.setImageResource(R.drawable.seat)
                        marca_elegida = "Seat"
                    }
                    3 ->{
                        binding.ivMiniCar.setImageResource(R.drawable.suzuki)
                        marca_elegida = "Suzuki"
                    }
                    4 ->{
                        binding.ivMiniCar.setImageResource(R.drawable.nissan)
                        marca_elegida = "Nissan"
                    }
                    5 ->{
                        binding.ivMiniCar.setImageResource(R.drawable.kia)
                        marca_elegida = "Kia"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {



            }

        }


        if (car != null){
            binding.etNombre.setText(car?.nombre)
            binding.etModelo.setText(car?.modelo)
            binding.etPrice.setText(car?.precio)

            marca_elegida = car?.marca


            if (marca_elegida.equals("Ford"))  binding.sMarca.setSelection(1)
            if (marca_elegida.equals("Seat"))  binding.sMarca.setSelection(2)
            if (marca_elegida.equals("Suzuki"))  binding.sMarca.setSelection(3)
            if (marca_elegida.equals("Nissan"))  binding.sMarca.setSelection(4)
            if (marca_elegida.equals("Kia"))  binding.sMarca.setSelection(5)


        }


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
            binding.sMarca.requestFocus(0)
            Toast.makeText(this,getString(R.string.elige_marca),Toast.LENGTH_LONG).show()

        }
        else{

            binding.updateCar.setOnClickListener {
                if (dbCars.updateCar(id,binding.etNombre.text.toString(),marca_elegida.toString(),binding.etModelo.text.toString(),binding.etPrice.text.toString())){
                    Toast.makeText(this,getString(R.string.registro_realizado_correcto),Toast.LENGTH_LONG).show()
                    val intent = Intent(this,DetailsCar::class.java)
                    intent.putExtra("id",id)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,getString(R.string.error_actualizar),Toast.LENGTH_LONG).show()
                }


            }

        }




    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}