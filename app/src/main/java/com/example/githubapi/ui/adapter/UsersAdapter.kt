package com.example.githubapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import com.example.githubapi.ui.vo.UserVO

class UsersAdapter(private val users: List<UserVO>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.users_list_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvIdentifier: TextView = itemView.findViewById(R.id.tv_id)
        private val tvLink: TextView = itemView.findViewById(R.id.tv_link)

        fun bind(user: UserVO) {
            tvName.text = user.name
            tvIdentifier.text = user.identifier.toString()
            tvLink.text = user.link
        }
    }
}