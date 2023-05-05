package com.denobaba.notepadddeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.denobaba.notepadddeneme.databinding.NotepaddInformationBinding
import com.denobaba.notepadddeneme.model.datas
import com.denobaba.notepadddeneme.roomdb.datasdao
import com.denobaba.notepadddeneme.roomdb.datasdatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class NotepaddInformation : AppCompatActivity() {
    private lateinit var binding: NotepaddInformationBinding
    private lateinit var db: datasdatabase
    private lateinit var placeDao: datasdao
    val compositeDisposable = CompositeDisposable()
    var datasfrommain : datas? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(applicationContext,datasdatabase::class.java,"Places").allowMainThreadQueries().build()

        placeDao = db.placeDao()
        binding = NotepaddInformationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_notepadd_information)
        cek()


    }

    private fun cek(){
        val intent= intent
        val info = intent.getStringExtra("info")

        if(info=="new"){
            binding.button4.visibility = View.GONE

        }else{
            datasfrommain= intent.getSerializableExtra("selecteddatas") as? datas

            datasfrommain.let {

                if (it != null) {
                    binding.title1.setText(it.name)
                }
                if (it != null) {
                    binding.title2.setText(it.alltext)
                }


            }


        }

    }

    fun save(view: View) {
        val place = datas(binding.title1.text.toString(),binding.title2.text.toString()) //çünkü burda bir isim bide enlem boylam aldık
        placeDao.insert(place)
        placeDao.insert(place)
        compositeDisposable.add(
            placeDao.insert(place).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse)



        )



    }



    private fun handleResponse(){
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)

    }

    fun delete(view: View){

        datasfrommain?.let {
            compositeDisposable.add( placeDao.delete(it).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse)


            )

        }

    }



}