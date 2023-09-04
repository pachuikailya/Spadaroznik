package com.lich.minskattractions

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.app.ActivityCompat.recreate
import java.util.*


class LangFragment : AppCompatDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lang, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getView()?.findViewById<Button?>(R.id.btnRus)?.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage("ru")
            dismiss()
        }
        getView()?.findViewById<Button?>(R.id.btnBel)?.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage("be")
            dismiss()
        }
        getView()?.findViewById<Button?>(R.id.btnEng)?.setOnClickListener {
            (requireActivity() as MainActivity).setLanguage("en")
            dismiss()
        }
    }



}