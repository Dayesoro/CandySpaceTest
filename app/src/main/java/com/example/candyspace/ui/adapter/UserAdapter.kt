package com.example.candyspace.ui.adapter






import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.candyspace.databinding.ItemLayoutBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.candyspace.domain.entity.UsersData


class UserAdapter(val onClick:(data: UsersData)->Unit): ListAdapter<UsersData, UserAdapter.UsersViewHolder>(UsersDiffCallback) {

    inner class UsersViewHolder(private val binding:ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data:UsersData){
            binding.usersName.text = "${data.Reputation}  ${data.DisplayName}"
            binding.root.setOnClickListener {
                onClick(data)
            }

        }
    }

    object UsersDiffCallback : DiffUtil.ItemCallback<UsersData>() {
        override fun areItemsTheSame(oldItem: UsersData, newItem: UsersData): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: UsersData, newItem: UsersData): Boolean {
            return oldItem.DisplayName == newItem.DisplayName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }
}