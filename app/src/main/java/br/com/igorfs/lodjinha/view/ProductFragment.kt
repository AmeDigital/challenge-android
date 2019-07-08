package br.com.igorfs.lodjinha.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.util.getLoaderPlaceholder
import br.com.igorfs.lodjinha.vo.ProductVo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_product.product_description
import kotlinx.android.synthetic.main.fragment_product.product_imageview
import kotlinx.android.synthetic.main.fragment_product.product_precoDe
import kotlinx.android.synthetic.main.fragment_product.product_precoPor
import kotlinx.android.synthetic.main.fragment_product.product_title

class ProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val productArgs : ProductFragmentArgs by navArgs()

        setupLayout(productArgs.product)
    }

    private fun setupLayout(product: ProductVo) {
        product_title.text = product.nome
        product_precoDe.text = product.precoDe.toString()
        product_precoPor.text = product.precoPor.toString()
        product_description.text = product.descricao

        Glide
            .with(requireActivity())
            .load(product.urlImagem)
            .placeholder(getLoaderPlaceholder(requireContext()))
            .error(R.drawable.ic_placeholder)
            .into(product_imageview)

    }

}
