package com.melichallenge.ui


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.melichallenge.R
import com.melichallenge.ViewModelFactory
import com.melichallenge.databinding.FragmentSearchBinding
import com.melichallenge.viewmodel.SearchViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var navigationController: NavigationController

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var bindings: FragmentSearchBinding

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)

        searchViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindings = FragmentSearchBinding.inflate(inflater, container, false)
        bindings.viewModel = searchViewModel

        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setTitle(getString(R.string.search_items))

        val adapter = SearchAdapter { navigationController.navigateToItem(it.itemId) }
        bindings.searchRv.adapter = adapter

        searchViewModel.searchData.observe(this, Observer { items -> adapter.items = items })
        searchViewModel.errorData.observe(this, Observer { error ->
            Snackbar.make(bindings.searchContainer, error, Snackbar.LENGTH_LONG).show()
        })

        bindings.searchEdit.requestFocus()
        val inputMethodManager = context?.applicationContext?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.showSoftInput(bindings.searchEdit, InputMethodManager.SHOW_IMPLICIT)

        searchViewModel.search()
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
