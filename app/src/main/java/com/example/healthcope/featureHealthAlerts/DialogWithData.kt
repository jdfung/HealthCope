package com.example.healthcope.featureHealthAlerts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthcope.viewModel.SharedViewModel
import com.example.healthcope.databinding.FragmentDialogWithDataBinding



class DialogWithData: DialogFragment() {


    companion object {

        const val TAG = "DialogWithData"

    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var binding: FragmentDialogWithDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogWithDataBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val arrayAdapter: ArrayAdapter<*>
        val myArrayList = arrayListOf<String>()


        arrayAdapter = ArrayAdapter(activity as Context, android.R.layout.simple_list_item_1, myArrayList)
        binding.list.adapter = arrayAdapter
        binding.addlistBtn.setOnClickListener {
            myArrayList.add(binding.etTxt.text.toString())
            binding.etTxt.setText("")
            arrayAdapter.notifyDataSetChanged()
        }

        setupClickListeners(view, myArrayList)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupClickListeners(view: View, array: ArrayList<*>) {

        binding.btnSubmit.setOnClickListener {
            val hour = binding.timePicker.hour.toString()
            val minute = binding.timePicker.minute.toString()

//            val time = binding.timePicker.hour()
            val drugName = array.joinToString(separator = ", ")

            if(drugName.isEmpty())
            {
                binding.etTxt.setError("Input drug names for intake reminder")
            }
            else {
                viewModel.sendName("$hour:$minute\n$drugName")
                dismiss()
            }

        }

        binding.btnCancel.setOnClickListener{
            dismiss()
        }
    }

}