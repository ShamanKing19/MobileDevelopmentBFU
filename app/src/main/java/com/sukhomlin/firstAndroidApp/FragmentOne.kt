package com.sukhomlin.firstAndroidApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_one.view.*

class FragmentOne : Fragment()
{
    private val myAdapter = PhonesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_one, container, false)
        loadData()

        root.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        root.recyclerView.adapter = myAdapter

        return root
    }

    private fun loadData() {
        myAdapter.setupPhone(PhonesData.phonesArr)
    }
}