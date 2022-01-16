package com.example.examenemg.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenemg.adapter.SuperHerosAdapter
import com.example.examenemg.databinding.ActivityMainBinding
import com.example.examenemg.interfaces.ItemClickListener
import com.example.examenemg.model.SuperheroModel
import com.example.examenemg.viewmodel.SuperheroViewModel
import java.io.Serializable

class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SuperHerosAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val superheroViewModel: SuperheroViewModel by viewModels()
    private val herosList = mutableListOf<SuperheroModel>()

    private var x = 1
    private var pageSize = 10;
    private var isBusy = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        superheroViewModel.onCreate(x, pageSize)
        superheroViewModel.isBusy.observe(this, Observer { value -> binding.loader.isVisible = value })
        superheroViewModel.superheros.observe(this, { value -> updateRecycler(value) } )

        binding.rvSuperhero.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0){
                    val visibleItem = layoutManager.findLastVisibleItemPosition() + 1
                    if (visibleItem == adapter.itemCount){
                        if (!isBusy){
                            isBusy = true
                            pageSize = pageSize + 10
                            x = pageSize - 9
                            superheroViewModel.onCreate(x, pageSize)
                        }
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun initRecycler(){
        layoutManager = LinearLayoutManager(this);
        adapter = SuperHerosAdapter(herosList, this)
        binding.rvSuperhero.layoutManager = layoutManager
        binding.rvSuperhero.adapter = adapter
    }

    private fun updateRecycler(superheros: List<SuperheroModel>){
        herosList.addAll(superheros)
        adapter.notifyDataSetChanged()
        isBusy = false
    }

    override fun onItemClickListener(superhero: SuperheroModel) {
        val intent = Intent(this, Superhero::class.java)
        intent.putExtra("data", superhero as Serializable)
        startActivity(intent)
    }
}