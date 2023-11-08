package com.example.chatapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivities
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.ChatActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.ProfileItemBinding
import com.example.chatapp.model.User

class UserAdapter (var context: Context, var userList: ArrayList<User>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val binding:ProfileItemBinding = ProfileItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.profileName.text = user.name
        Glide.with(context).load(user.profileImage)
            .placeholder(R.drawable.person)
            .into(holder.binding.profileImg)
        //**chatting code start**//
        holder.itemView.setOnClickListener {
            var intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name",user.name)
            intent.putExtra("image",user.profileImage)
            intent.putExtra("uid",user.uid)
                context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int = userList.size
}