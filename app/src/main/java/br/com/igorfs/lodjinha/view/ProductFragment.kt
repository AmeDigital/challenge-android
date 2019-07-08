package br.com.igorfs.lodjinha.view

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.util.getLoaderPlaceholder
import br.com.igorfs.lodjinha.vo.ProductVo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_product.product_description
import kotlinx.android.synthetic.main.fragment_product.product_group_layout
import kotlinx.android.synthetic.main.fragment_product.product_imageview
import kotlinx.android.synthetic.main.fragment_product.product_precoDe
import kotlinx.android.synthetic.main.fragment_product.product_precoPor
import kotlinx.android.synthetic.main.fragment_product.product_progressBar
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

        product_precoDe.text = getString(R.string.preco_de, product.precoDe.toString())
        product_precoDe.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        product_precoPor.text = getString(R.string.preco_por, product.precoPor.toString())

        product_description.text = HtmlCompat.fromHtml(product.descricao, HtmlCompat.FROM_HTML_MODE_LEGACY)

        Glide
            .with(requireActivity())
            .load(product.urlImagem)
            .placeholder(getLoaderPlaceholder(requireContext()))
            .error(R.drawable.ic_placeholder)
            .into(product_imageview)
        hideLoading()
    }

    private fun hideLoading() {
        product_group_layout.visibility = View.VISIBLE
        product_progressBar.visibility = View.GONE
    }

}
