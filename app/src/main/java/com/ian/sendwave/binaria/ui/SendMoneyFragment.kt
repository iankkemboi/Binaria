package com.ian.sendwave.binaria.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hbb20.CountryCodePicker.PhoneNumberValidityChangeListener
import com.ian.sendwave.binaria.R
import com.ian.sendwave.binaria.databinding.FragmentSendMoneyBinding
import com.ian.sendwave.binaria.model.Rates
import com.ian.sendwave.binaria.utils.Resource
import com.ian.sendwave.binaria.utils.Utility

import com.ian.sendwave.binaria.utils.afterTextChanged
import com.ian.sendwave.binaria.utils.isOnline
import com.ian.sendwave.binaria.viewmodel.SendMoneyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SendMoneyFragment : Fragment() {
    private lateinit var viewModel: SendMoneyViewModel
    private lateinit var currencyrates: Rates
    private var binding: FragmentSendMoneyBinding? = null
    private var booleanvalidnumber: Boolean = false
    private var booleanratesloaded: Boolean = false
    private lateinit var errorSnackbar: Snackbar
    private var utility: Utility = Utility()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRootView = binding!!.root
        viewModel = ViewModelProvider(this)[SendMoneyViewModel::class.java]
        errorSnackbar = Snackbar.make(binding!!.homelayout, "", Snackbar.LENGTH_LONG)
        getLatestCurrency()
        setupObservers()

        binding?.ccp?.registerCarrierNumberEditText(binding?.editmobile)

        binding!!.editamount.afterTextChanged {


            if (it.length > 0) {
                val binaryAmount = it.toLong()
                val decimalAmount = utility.convertBinaryToDecimal(binaryAmount)

                setRateInfo(decimalAmount, binding?.ccp?.selectedCountryNameCode!!)
            }
        }

        binding!!.ccp.setPhoneNumberValidityChangeListener(PhoneNumberValidityChangeListener {
            booleanvalidnumber = it
        })



        binding!!.buttonUpdate.setOnClickListener {
            if (booleanratesloaded) {
                if (booleanvalidnumber) {
                    val txtfullname = binding!!.editName.text.toString()
                    val txtmobno = binding!!.editmobile.text.toString()
                    val txtamount = binding!!.editamount.text.toString()
                    if (!TextUtils.isEmpty(txtfullname)) {
                        if (!TextUtils.isEmpty(txtmobno)) {
                            if (!TextUtils.isEmpty(txtamount)) {
                                mRootView.findNavController()
                                    .navigate(R.id.action_sendMoneyFragment_to_transactionProcessingFragment)
                            } else {
                                showError("Please enter a value for Amount")

                            }

                        } else {
                            showError("Please enter a value for Mobile Number")

                        }

                    } else {
                        showError("Please enter a value for Full Name")

                    }

                } else {
                    showError("Please enter a valid phone number")

                }


            } else {
                showError("Please ensure the currency rates are fetched")

            }

        }
    }

    private fun setupObservers() {
        viewModel.currencyapi.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {


                    currencyrates = it.data?.rates!!
                    binding?.rateinfo?.visibility = View.VISIBLE
                    booleanratesloaded = true

                }
                Resource.Status.ERROR -> {
                    errorSnackbar.setText("Something went wrong fetching latest currency data")
                    errorSnackbar.setAction(
                        "TRY AGAIN"
                    ) {
                        getLatestCurrency()
                    }
                    errorSnackbar.show()


                }


                else -> {
                }
            }
        })
    }

    private fun getLatestCurrency() {
        if (activity?.isOnline() == true) {
            viewModel.getLatestCurrency()
        } else {
showError("No Internet connection detected")
        }
    }

    private fun showError(errormessage: String) {
        errorSnackbar.setText(errormessage)
        errorSnackbar.show()

    }

    private fun setRateInfo(amount: Double, countrycode: String) {
        if (this::currencyrates.isInitialized) {
            var rate = 1.0
            when (countrycode) {
                "KE" -> rate = currencyrates.kes!!
                "NG" -> rate = currencyrates.ngn!!
                "TZ" -> rate = currencyrates.tzs!!
                "UG" -> rate = currencyrates.ugx!!

            }
            val convertedAmount = rate * amount
            val binaryamount = utility.convertDecimalToBinary(convertedAmount.toInt())
            binding?.rateinfo?.text = "Rate: 1 USD = $rate ${utility.getCurrencyCode(countrycode)}"
            binding!!.recamount.text = binaryamount + " ${utility.getCurrencyCode(countrycode)}"

        }
    }

}