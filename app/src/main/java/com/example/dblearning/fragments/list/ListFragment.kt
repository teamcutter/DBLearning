package com.example.dblearning.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dblearning.R
import com.example.dblearning.viewmodel.UserViewModel
import com.example.dblearning.databinding.FragmentListBinding

class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)

        // Recyclerview
        adapter = ListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // readAllData возвращает QuerySet из объектов в виде List<User> и мы передаем его в адаптер
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { list ->
            list.let{
                adapter.setData(it)
            }
        })

        // так нас переключает на другой фрагмент
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Add menu
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.delete_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        // присваиваем searchView вьюшку (SearchView),
        // которая является actionViewClass для search (menu_search)
        // используем каст, т.к мы хотим далее работать с объектом класса SearchView
        val searchView = search.actionView as? SearchView

        // showing a submit button when query is non-empty
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null) {
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null) {
            searchDatabase(query)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        // создает окно предупреждения
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(requireContext(),
                "All users have been removed",
                Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No") {_, _ ->}
        builder.setTitle("Delete all users?")
        builder.setMessage("Are you sure you want to delete all users?")
        builder.create().show()
    }

    private fun searchDatabase(query: String) {
        // % % - is using for format our query to database query (it is required by database)
        val searchQuery = "%$query%"

        mUserViewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner, Observer { list ->
            list.let{
                adapter.setData(it)
            }
        })
    }
}