package com.example.dblearning.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dblearning.data.User
import com.example.dblearning.databinding.CustomRowBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()
    private lateinit var binding: CustomRowBinding

    inner class MyViewHolder(private val itemBinding: CustomRowBinding): RecyclerView.ViewHolder(itemBinding.root) {

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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bindItems(userList[position])

    fun setData(user: List<User>) {
        this.userList = user
        // чтобы коректно изменялись данные
        notifyDataSetChanged()
    }

}