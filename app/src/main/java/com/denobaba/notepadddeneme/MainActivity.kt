package com.denobaba.notepadddeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.denobaba.notepadddeneme.adaptor.myadaptor
import com.denobaba.notepadddeneme.databinding.ActivityMainBinding
import com.denobaba.notepadddeneme.model.datas
import com.denobaba.notepadddeneme.roomdb.datasdatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = Room.databaseBuilder(applicationContext, datasdatabase::class.java,"Places").allowMainThreadQueries().build()

        val placeDao = db.placeDao()

        compositeDisposable.add(
            placeDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse))

    }




    private fun handleResponse(placeList: List<datas>){
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        val adapter =myadaptor(placeList)
        binding.recycleView.adapter= adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater= menuInflater
        menuInflater.inflate(R.menu.newmenu,menu)
        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.newmenuu){
            val intent = Intent(this,NotepaddInformation::class.java)
            intent.putExtra("info","new")
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)

    }

}