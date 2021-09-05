package com.app.iwitx.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.iwitx.R
import com.app.iwitx.databinding.ActivityKYCBinding
import com.app.iwitx.viewmodel.AndroidViewModel

class KYCActivity : Fragment() {
    lateinit var activityKYCBinding : ActivityKYCBinding
    lateinit var state: Spinner
    lateinit var viewModel: AndroidViewModel
    var state_name: ArrayList<String> =  ArrayList<String>()
    var state_id: ArrayList<String> =  ArrayList<String>()
    var stateid = ""
    var cityid= ""
    var city_name: ArrayList<String> =  ArrayList<String>()
    var city_id: ArrayList<String> =  ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.activity_k_y_c, container, false)

        viewmodelinit()
        initstate()
        spinnerlistener()
        submitkyc()
        return view
    }
    fun submitkyc(){
        activityKYCBinding.update.setOnClickListener {
            Toast.makeText(requireContext(),"it"+stateid+cityid,Toast.LENGTH_SHORT).show()
            viewModel.uploadkyc(stateid,cityid).observe(viewLifecycleOwner, Observer {
                    Toast.makeText(requireContext(),""+it.message,Toast.LENGTH_SHORT).show()
            })
        }
    }

    fun spinnerlistener(){
        activityKYCBinding.state.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val state = parent!!.getItemAtPosition(position).toString()
              //  Toast.makeText(requireContext(),""+state,Toast.LENGTH_SHORT).show()
                for (i in state_name.indices) {
                    if (state_name.get(i) == state) {
                        stateid = state_id.get(i)
                    }
                }
                getcity()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

        }

        activityKYCBinding.district.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val city = parent!!.getItemAtPosition(position).toString()
                //  Toast.makeText(requireContext(),""+state,Toast.LENGTH_SHORT).show()
                for (i in city_id.indices) {
                    if (city_name.get(i) == city) {
                        cityid = city_id.get(i)
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

        }

    }

    fun getcity(){
      //  Toast.makeText(requireContext(),""+stateid,Toast.LENGTH_SHORT).show()
        viewModel.getcity(stateid).observe(viewLifecycleOwner, Observer {
           city_name.clear()
           city_id.clear()
            for (i in it.data.indices) {
                city_name.add(it.data[i].districtName)
                city_id.add(it.data[i].districtId)
            }
        })
    }

    fun viewmodelinit(){
        activityKYCBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.activity_k_y_c)
        viewModel = ViewModelProvider(this).get(AndroidViewModel::class.java);
    }
    fun initstate(){
        viewModel.getstate().observe(viewLifecycleOwner, Observer {
          //  Toast.makeText(requireContext(), "" + it.message, Toast.LENGTH_SHORT).show()
//            state_name.add("Select State")
//            state_id.add("Select id")
            city_name.add("Select City")
            for (i in it.data.indices) {
                state_name.add(it.data[i].stateName)
                state_id.add(it.data[i].stateId)
            }

            val stateadapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_expandable_list_item_1, state_name)
            activityKYCBinding.state.adapter = stateadapter

            val cityadapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_expandable_list_item_1, city_name)
            activityKYCBinding.district.adapter = cityadapter

        })        
    }

}