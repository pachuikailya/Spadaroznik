package com.lich.minskattractions



import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.lich.minskattractions.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(),SightAdapter.Listener, SearchView.OnQueryTextListener {
    lateinit var binding: ActivityMainBinding
    private val adapter = SightAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcList.layoutManager= LinearLayoutManager(this)
        binding.rcList.adapter=adapter
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        val db=MainDB.getDb(this)

        /*Thread{
            db.clearAllTables()
        }.start()*/

        var k=0
        for (i in 0 until resources.getStringArray(R.array.Names).size){
            when(i){
                in 0 until 12-> k=0
                in  12 until 18-> k=1
                in  18 until 22-> k=2
                in  22 until 24-> k=3
                in  24 until 31-> k=4
                in  31 until resources.getStringArray(R.array.Names).size-> k=5
            }
            val item=Item(i,resources.getStringArray(R.array.Names)[i],
                resources.getStringArray(R.array.Adress)[i],
                resources.getStringArray(R.array.Category)[k],
                resources.getStringArray(R.array.Description)[i],
                resources.getStringArray(R.array.History)[i],
                resources.getStringArray(R.array.Images)[i],
                resources.getStringArray(R.array.Latitude)[i].toDouble(),
                resources.getStringArray(R.array.Longitude)[i].toDouble())
            Thread{
                db.getDao().insertItem(item)
            }.start()

        }

        db.getDao().getAllItem().asLiveData().observe(this){list->
            adapter.setList(list)
        }

        binding.button.setOnClickListener {
            db.getDao().filterDB(binding.button.text.toString()).asLiveData().observe(this){list->
                adapter.setList(list)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        binding.button2.setOnClickListener {
            db.getDao().filterDB(binding.button2.text.toString()).asLiveData().observe(this){list->
                adapter.setList(list)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        binding.button3.setOnClickListener {
            db.getDao().filterDB(binding.button3.text.toString()).asLiveData().observe(this){list->
                adapter.setList(list)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        binding.button4.setOnClickListener {
            db.getDao().filterDB(binding.button4.text.toString()).asLiveData().observe(this){list->
                adapter.setList(list)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        binding.button5.setOnClickListener {
            db.getDao().filterDB(binding.button5.text.toString()).asLiveData().observe(this){list->
                adapter.setList(list)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        binding.button6.setOnClickListener {
            db.getDao().filterDB(binding.button6.text.toString()).asLiveData().observe(this){list->
                adapter.setList(list)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        binding.button7.setOnClickListener {
            db.getDao().getAllItem().asLiveData().observe(this){list->
                adapter.setList(list)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }



    override fun onClick(item: Item) {
        startActivity(Intent(this,Content::class.java).apply {
            putExtra("item_id",item.id)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.menu_search -> true
            R.id.lang_item -> {
                LangFragment().show(supportFragmentManager, "lang")
                true
            }
            android.R.id.home-> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchDatabase(newText)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        // %" "% because our custom sql query will require that
        val db=MainDB.getDb(this)
        val searchQuery = "%$query%"

        db.getDao().searchDB(searchQuery).asLiveData().observe(this){list->
            adapter.setList(list)
        }
            }


    fun setLanguage(lang : String){
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources?.updateConfiguration(config, baseContext.resources.displayMetrics)
        recreate()
    }
}
