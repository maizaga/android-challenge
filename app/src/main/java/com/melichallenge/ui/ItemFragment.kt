package com.melichallenge.ui


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.melichallenge.ViewModelFactory
import com.melichallenge.databinding.FragmentItemBinding
import com.melichallenge.viewmodel.ItemViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class ItemFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var bindings: FragmentItemBinding

    private var itemId: String? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        itemViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(ItemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getString(ARG_ITEM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = FragmentItemBinding.inflate(inflater, container, false)
        bindings.viewModel = itemViewModel
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AttributesAdapter()
        bindings.itemAttributesRv.layoutManager = GridLayoutManager(context, 2)
        bindings.itemAttributesRv.adapter = adapter
        bindings.itemPicturesDots.setupWithViewPager(bindings.itemPicturesPager, true)
        val pagerAdapter = PicturePagerAdapter()
        bindings.itemPicturesPager.adapter = pagerAdapter

        itemViewModel.itemData.observe(this, Observer { item ->
            bindings.item = item
            adapter.items = item.attributes
            pagerAdapter.items = item.pictures
            bindings.itemQuantity.adapter = ArrayAdapter<Int>(context!!, android.R.layout.simple_spinner_dropdown_item,
                (1..item.availableQuantity).toList())
        })
        itemViewModel.errorData.observe(this, Observer { error ->
            Snackbar.make(bindings.itemContainer, error, Snackbar.LENGTH_LONG).show()
        })

        itemId?.let { itemViewModel.getItem(it) }
    }

    companion object {
        private const val ARG_ITEM_ID = "itemId"

        fun newInstance(itemId: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ITEM_ID, itemId)
                }
            }
    }
}
