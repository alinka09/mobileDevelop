package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.lab1.data.CurrencyData
import com.example.lab1.databinding.MainActivityBinding
import com.example.lab1.ui.main.*
import com.example.lab1.viewmodel.CurrencyViewModel
import com.example.lab1.viewmodel.CurrencyViewModelFactory
import com.example.lab1.viewmodel.RepositoryInitializer

interface ActivityCallback{
    fun showCurrencyExchangeView(firstCurrency: CurrencyData, secondCurrency: CurrencyData)
    fun showCurrencyListView()
    fun showHistoryFilterView()
    fun showHistoryView()
}

class MainActivity : AppCompatActivity(), ActivityCallback {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.marketButton.setOnClickListener {
            showCurrencyListView()
        }
        binding.trendButton.setOnClickListener {
            binding.header.text = "Графики"
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrendFragment.newInstance())
                .commit()
        }
        binding.historyButton.setOnClickListener {
            showHistoryView()
        }

        if (savedInstanceState == null) {
            showCurrencyListView()
        }
    }

    override fun showCurrencyExchangeView(firstCurrency: CurrencyData, secondCurrency: CurrencyData) {
        binding.header.text = "Обмен валют"
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CurrencyExchangeFragment.newInstance())
            .commitNow()
    }

    override fun showCurrencyListView() {
        binding.header.text = "Список валют"
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
    }

    override fun showHistoryFilterView() {
        binding.header.text = "Фильтр истории"
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FilterFragment.newInstance())
            .commit()
    }

    override fun showHistoryView() {
        binding.header.text = "История обменов"
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HistoryFragment.newInstance())
            .commit()
    }


}