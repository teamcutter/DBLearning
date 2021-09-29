package com.example.dblearning.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dblearning.model.User
import com.example.dblearning.databinding.CustomRowBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()
    private lateinit var binding: CustomRowBinding

    inner class MyViewHolder(val itemBinding: CustomRowBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItems(user: User) {
            itemBinding.userIdText.text = user.id.toString()
            itemBinding.userFirstNameText.text = user.firstName
            itemBinding.userLastNameText.text = user.lastName
            itemBinding.userAgeText.text = user.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.bindItems(currentItem)

        holder.itemBinding.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentUser = currentItem)
            // When user item in RecyclerView,
            // we are going to pass this item's data to UpdateFragment
            // and also redirect to UpdateFragment
            holder.itemBinding.rowLayout.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>) {
        this.userList = user
        // чтобы коректно изменялись данные
        notifyDataSetChanged()
    }

}