package com.example.currencyexchange.ui

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.currencyexchange.Adapter.RecyclerMainAdapter
import com.example.currencyexchange.databinding.ActivityMainBinding
import com.example.currencyexchange.remote.Currency.GoldApiRepository
import com.example.currencyexchange.remote.Currency.GoldRequest
import com.example.currencyexchange.remote.Model.ContentModel
import com.example.currencyexchange.remote.Model.DateModel
import com.example.currencyexchange.remote.Model.GoldModel
import com.example.currencyexchange.remote.Time.TimeApiRepository
import com.example.currencyexchange.remote.Time.TimeRequest
import java.util.Currency

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val goldPrice = ArrayList<ContentModel>()
    private val currencyPrice = ArrayList<ContentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideStatusBar()
        getPrice()

        TimeApiRepository.Instance.getDate(
            object : TimeRequest {
                override fun onSuccess(data: DateModel) {
                    val date = data.date
                    val text = "${date.l_value} ${date.j_value} ${date.F_value} ${date.Y_value}"
                    binding.txtDate.text = text


                }

                override fun onNotSuccess(message: String) {

                }

                override fun onError(error: String) {

                }

            }
        )

        binding.recyclerView.layoutManager =
            LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
            )

        binding.txtTala.setOnClickListener {
            binding.txtArz.setTextColor(Color.parseColor("#787879"))
            binding.txtTala.setTextColor(Color.parseColor("#E7C376"))
            setData(goldPrice)

        }

        binding.txtArz.setOnClickListener {
            binding.txtTala.setTextColor(Color.parseColor("#787879"))
            binding.txtArz.setTextColor(Color.parseColor("#E7C376"))
            setData(currencyPrice)
        }


    }


    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            window.insetsController?.hide(WindowInsets.Type.statusBars())

        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN

            )
        }
    }

    private fun getPrice() {
        GoldApiRepository.Instance.getGold(
            object : GoldRequest {
                override fun onSuccess(data: GoldModel) {
                    goldPrice.addAll(data.data.golds)
                    currencyPrice.addAll(data.data.currencies)
                    currencyPrice.addAll(data.data.cryptocurrencies)
                    setData(goldPrice)
                }

                override fun onNotSuccess(message: String) {
                    TODO("Not yet implemented")
                }

                override fun onError(error: String) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    private fun setData(data: ArrayList<ContentModel>) {

        binding.recyclerView.adapter = RecyclerMainAdapter(data)

    }
}