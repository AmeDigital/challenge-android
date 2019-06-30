package com.example.lodjinha.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import android.content.DialogInterface
import android.graphics.Paint
import android.text.Html
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.lodjinha.BackHandler
import com.example.lodjinha.MainActivity
import com.example.lodjinha.R
import com.squareup.picasso.Callback

class ProductFragment : Fragment(), ProductContract.View, OnBackPressedCallback, BackHandler {
    override fun handleOnBackPressed(): Boolean {
        (activity as MainActivity).getSupportActionBar()?.show()
        return true
    }

    override fun handleBackPressed(): Boolean {
        (activity as MainActivity).getSupportActionBar()?.show()
        return true
    }

    private val presenter by inject<ProductContract.Presenter> { parametersOf(this) }

    companion object {
        private const val PRODUCT_ID_OBJECT = "productIdObject"

        fun newInstance(productId: Int) = ProductFragment().apply {
            arguments = Bundle().apply {
                putInt(PRODUCT_ID_OBJECT, productId)
            }
        }
    }

    override fun showProduct(product: Product) {
        Picasso.get().load(product.imageUrl).into(ivProduct, object : Callback {
            override fun onSuccess() {}
            override fun onError(e: Exception) {
                ivProduct?.setImageResource(R.drawable.default_image)
            }
        })
        tvProductName?.text = product.name
        tvOldPrice?.text = "De: ${product.oldPrice.toString()}"
        tvNewPrice?.text = "Por ${product.newPrice.toString()}"
        tvOldPrice?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        floating_action_button?.setOnClickListener {
            presenter.reserveProduct(product.id)
            floating_action_button?.isClickable = false
        }
        ivBack?.setOnClickListener {
            (activity as MainActivity).getSupportActionBar()?.show()
            fragmentManager?.popBackStack()
        }

        tvProductDescription?.text = Html.fromHtml(product.description).toString()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView = inflater.inflate(R.layout.fragment_product, container, false)
        (activity as MainActivity).getSupportActionBar()?.hide()

        arguments?.let {
            val productId = it[PRODUCT_ID_OBJECT] as Int
            presenter.getProduct(productId)
        }

        return rootView
    }

    private fun okCallback(dialog: DialogInterface) {
        dialog.dismiss()
        (activity as MainActivity).getSupportActionBar()?.show()
        fragmentManager?.popBackStack()
    }

    override fun showSuccessAlert() {
        val alertDialog = AlertDialog.Builder(activity as MainActivity).create()
        alertDialog.setMessage(getString(R.string.product_reserve_success_message))
        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, "OK"
        ) { dialog, which -> okCallback(dialog) }
        alertDialog.show()
        floating_action_button?.isClickable = true
    }

    override fun showErrorAlert() {
        val alertDialog = AlertDialog.Builder(activity as MainActivity).create()
        alertDialog.setMessage(getString(R.string.product_reserve_error_message))
        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, "OK"
        ) { dialog, which -> okCallback(dialog) }
        alertDialog.show()
        floating_action_button?.isClickable = true
    }
}
