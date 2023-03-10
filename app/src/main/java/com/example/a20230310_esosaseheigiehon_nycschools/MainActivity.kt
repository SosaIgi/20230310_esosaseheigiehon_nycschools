package com.example.a20230310_esosaseheigiehon_nycschools

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20230310_esosaseheigiehon_nycschools.adapter.SearchItemAdapter
import com.example.a20230310_esosaseheigiehon_nycschools.databinding.ActivityMainBinding
import com.example.a20230310_esosaseheigiehon_nycschools.viewmodel.SchoolViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var menu : Menu
    private lateinit var searchItemAdapter: SearchItemAdapter
    private lateinit var viewModel : SchoolViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set content with activity_main
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
        searchItemAdapter = SearchItemAdapter(this, viewModel, listOf())
        binding.rvSearch.adapter = searchItemAdapter
        binding.rvSearch.layoutManager = LinearLayoutManager(this)


        val results = viewModel.lSearchResult.value
        searchItemAdapter.changeData(results)
        setContentView(binding.root)
    }

    override fun onStart() {
        lifecycleScope.launch{
            viewModel.lSearchResult.collect{
                searchItemAdapter.changeData(it)
            }
        }
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        this.menu = menu
        val manager = getSystemService(Context.SEARCH_SERVICE)

        val search = menu.findItem(R.id.search)

        val searchView = search.actionView as androidx.appcompat.widget.SearchView
        //add search functionalities
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    viewModel.search(query ?: "")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    viewModel.search(newText ?: "")
                }
                return true
            }
        })

        return true
    }


}
