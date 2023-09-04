package com.lich.minskattractions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lich.minskattractions.databinding.ActivityContentBinding
import com.squareup.picasso.Picasso

class Content : AppCompatActivity() {
    lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id=intent.getSerializableExtra("item_id") as Int
        Log.d("MyTag","$id")

        Picasso.with(this).load(resources.getStringArray(R.array.Images)[id]).into(binding.imageView)
        binding.tvContName.text=resources.getStringArray(R.array.Names)[id]
        binding.tvDesc.text = resources.getStringArray(R.array.Description)[id]


        binding.btnMap.setOnClickListener {
            startActivity(Intent(this,MapActivity::class.java).apply {
                putExtra("map_id",id)
            })
        }

        binding.btnHistory.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.btn_hist)
                .setMessage(resources.getStringArray(R.array.History)[id])
                .setPositiveButton("Ok"){dialog,which->
                    dialog.cancel()
                }.show()
        }
    }
}