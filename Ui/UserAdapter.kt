package com.example.userlistapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userlistapp.data.User
import com.example.userlistapp.databinding.ItemUserBinding

class UserAdapter(
    private val onClick: (User) -> Unit,
    private val onLongClick: (User) -> Unit
) : ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallback()) {

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, onClick: (User) -> Unit, onLongClick: (User) -> Unit) {
            binding.textName.text = user.name
            binding.textAge.text = "Возраст: ${user.age}"
            binding.root.setOnClickListener { onClick(user) }
            binding.root.setOnLongClickListener {
                onLongClick(user)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), onClick, onLongClick)
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
    }
}
