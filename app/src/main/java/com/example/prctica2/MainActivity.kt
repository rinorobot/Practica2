package com.example.prctica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.prctica2.Adapters.CarAdapter
import com.example.prctica2.DataBase.DBCars
import com.example.prctica2.Models.Car
import com.example.prctica2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listOfCars: ArrayList<Car>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val dbCars = DBCars(this)
        listOfCars = dbCars.getCars()

        if (listOfCars.size == 0) binding.tvAviso.visibility = View.VISIBLE
        else binding.tvAviso.visibility = View.INVISIBLE
        //Mostrando lista de coches
        //Adapter
        val carAdapter = CarAdapter(this,listOfCars)

        binding.listCars.adapter = carAdapter

        binding.listCars.setOnItemClickListener { adapterView, view, position, id ->


            val intent = Intent(this, DetailsCar::class.java)
            intent.putExtra("id",id.toInt())
            startActivity(intent)
            finish()

        }

        binding.fbaAddCar.setOnClickListener {
            startActivity(Intent(this,InsertCar::class.java))
            finish()
        }

    }
}