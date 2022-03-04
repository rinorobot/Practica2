package com.example.prctica2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.prctica2.Models.Car
import com.example.prctica2.R
import com.example.prctica2.databinding.ItemCarBinding

class CarAdapter(context: Context, listOfCars: ArrayList<Car>): BaseAdapter() {
    private val listOfCars = listOfCars
    private val layoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return listOfCars.size
    }

    override fun getItem(position: Int): Any {
        return listOfCars[position]
    }

    override fun getItemId(position: Int): Long {
        return listOfCars[position].id.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ItemCarBinding.inflate(layoutInflater)
        binding.tvNombre.text = listOfCars[position].nombre
        binding.tvMarca.text = listOfCars[position].marca
        binding.tvModelo.text = listOfCars[position].modelo
        binding.tvPrecio.text = listOfCars[position].precio

        if (binding.tvMarca.text.toString().equals("Ford")) binding.ivItemCar.setImageResource(R.drawable.ford)
        if (binding.tvMarca.text.toString().equals("Seat")) binding.ivItemCar.setImageResource(R.drawable.seat)
        if (binding.tvMarca.text.toString().equals("Suzuki")) binding.ivItemCar.setImageResource(R.drawable.suzuki)
        if (binding.tvMarca.text.toString().equals("Nissan")) binding.ivItemCar.setImageResource(R.drawable.nissan)
        if (binding.tvMarca.text.toString().equals("Kia")) binding.ivItemCar.setImageResource(R.drawable.kia)

        return binding.root
    }
}