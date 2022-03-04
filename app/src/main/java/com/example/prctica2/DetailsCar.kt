package com.example.prctica2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.prctica2.DataBase.DBCars
import com.example.prctica2.Models.Car
import com.example.prctica2.databinding.ActivityDetailsCarBinding

lateinit var binding: ActivityDetailsCarBinding
private lateinit var dbCars: DBCars
var car: Car? = null
var id: Int = 0
class DetailsCar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsCarBinding.inflate(layoutInflater)
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

        if (car != null){
            binding.etNombre.setText(car?.nombre)
            binding.etMarca.setText(car?.marca)
            binding.etModelo.setText(car?.modelo)
            binding.etPrice.setText(car?.precio)

            binding.etNombre.inputType = InputType.TYPE_NULL
            binding.etMarca.inputType = InputType.TYPE_NULL
            binding.etModelo.inputType = InputType.TYPE_NULL
            binding.etPrice.inputType = InputType.TYPE_NULL
        }

        binding.editCar.setOnClickListener {
            val intent = Intent(this,EditCar::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            finish()

        }

        binding.deleteCar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirmación")
                .setMessage("¿Estás seguro que quieres eliminar ${car?.nombre}?")
                .setPositiveButton("Sí",{ dialogInterface: DialogInterface, i: Int ->
                    if (dbCars.deleteCar(id)){
                        Toast.makeText(this,getString(R.string.registro_exito),Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }

                }).setNegativeButton("Mejor no",{ dialogInterface: DialogInterface, i: Int ->


                }).show()

        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}