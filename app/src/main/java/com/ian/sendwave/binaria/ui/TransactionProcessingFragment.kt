package com.ian.sendwave.binaria.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ian.sendwave.binaria.R
import com.ian.sendwave.binaria.databinding.FragmentTransactionProcessingBinding


class TransactionProcessingFragment : Fragment() {
    private var binding: FragmentTransactionProcessingBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionProcessingBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.buttonGohome.setOnClickListener {
            val mRootView = binding!!.root
            mRootView.findNavController()
                .navigate(R.id.action_transactionProcessingFragment_to_sendMoneyFragment)
        }

    }


}