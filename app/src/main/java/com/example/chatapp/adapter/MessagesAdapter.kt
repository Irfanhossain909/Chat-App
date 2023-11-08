package com.example.chatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp.R
import com.example.chatapp.databinding.DeleteLayoutBinding
import com.example.chatapp.databinding.SendMsgBinding
import com.example.chatapp.model.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MessagesAdapter (


    var context: Context,
    messages :ArrayList<Message>?,
    senderRoom:String,
    receiveRoom:String
):RecyclerView.Adapter<RecyclerView.ViewHolder?>()
{
    lateinit var messages:ArrayList<Message>
    val ITEM_SENT = 1
    val ITEM_RECEIVE = 2
    val senderRoom :String
    var receiveRoom :String


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_SENT){
           val view:View = LayoutInflater.from(context).inflate(R.layout.send_msg, parent,false)
            SensMsgHolder(view)
        }
        else{
            val view:View = LayoutInflater.from(context).inflate(R.layout.receive_msg, parent,false)
            SensMsgHolder(view)
            ReceiveMsgHolder(view)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val messages = messages[position]
        return if (FirebaseAuth.getInstance().uid == messages.senderId){
            ITEM_SENT
        }
        else{
            ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        if (holder.javaClass == SensMsgHolder::class.java){
            val viewHolder = holder as SensMsgHolder
            if (message.messege.equals("photo")){
                viewHolder.binding.image.visibility = View.VISIBLE
                viewHolder.binding.message.visibility = View.GONE
                viewHolder.binding.mLinear.visibility = View.GONE
                Glide.with(context)
                    .load(message.imageurl)
                    .placeholder(R.drawable.placeholder)
                    .into(viewHolder.binding.image)
            }
            viewHolder.binding.message.text = message.messege
            viewHolder.itemView.setOnClickListener {
                val view = LayoutInflater.from(context).inflate(R.layout.delete_layout, null)
                val binding:DeleteLayoutBinding = DeleteLayoutBinding.bind(view)
                val dialog : AlertDialog = AlertDialog.Builder(context)
                    .setTitle("Delete Message")
                    .setView(binding.root)
                    .create()
                binding.everyone.setOnClickListener {
                    message.messege = "This Message is Removed"
                    message.messegeId?.let {it1->
                        FirebaseDatabase.getInstance().reference.child("chats")
                            .child(senderRoom)
                            .child("message")
                            .child(it1).setValue(message)
                    }
                    message.messegeId.let {it1->
                        FirebaseDatabase.getInstance().reference.child("chats")
                            .child(receiveRoom)
                            .child("message")
                            .child(it1!!).setValue(message)

                    }
                    dialog.dismiss()
                }
                binding.delete.setOnClickListener {
                    message.messegeId.let { it1->
                        FirebaseDatabase.getInstance().reference.child("chats")
                            .child(senderRoom)
                            .child("message")
                            .child(it1!!).setValue(null)
                    }
                    dialog.dismiss()

                }
                binding.cencel.setOnClickListener {dialog.dismiss()}
                dialog.show()
                false
            }
        }
        else{
            val viewHolder = holder as ReceiveMsgHolder
            if (message.messege.equals("photo")){
                viewHolder.binding.image.visibility = View.VISIBLE
                viewHolder.binding.message.visibility = View.GONE
                viewHolder.binding.mLinear.visibility = View.GONE
                Glide.with(context)
                    .load(message.imageurl)
                    .placeholder(R.drawable.placeholder)
                    .into(viewHolder.binding.image)
            }
            viewHolder.binding.message.text = message.messege
            viewHolder.itemView.setOnClickListener {
                val view = LayoutInflater.from(context).inflate(R.layout.delete_layout, null)
                val binding:DeleteLayoutBinding = DeleteLayoutBinding.bind(view)
                val dialog : AlertDialog = AlertDialog.Builder(context)
                    .setTitle("Delete Message")
                    .setView(binding.root)
                    .create()
                binding.everyone.setOnClickListener {
                    message.messege = "This Message is Removed"
                    message.messegeId?.let {it1->
                        FirebaseDatabase.getInstance().reference.child("chats")
                            .child(senderRoom)
                            .child("message")
                            .child(it1).setValue(message)
                    }
                    message.messegeId.let {it1->
                        FirebaseDatabase.getInstance().reference.child("chats")
                            .child(receiveRoom)
                            .child("message")
                            .child(it1!!).setValue(message)

                    }
                    dialog.dismiss()
                }
                binding.delete.setOnClickListener {
                    message.messegeId.let { it1->
                        FirebaseDatabase.getInstance().reference.child("chats")
                            .child(senderRoom)
                            .child("message")
                            .child(it1!!).setValue(null)
                    }
                    dialog.dismiss()

                }
                binding.cencel.setOnClickListener {dialog.dismiss()}
                dialog.show()
                false
            }
        }

    }

    class SensMsgHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var binding:SendMsgBinding = SendMsgBinding.bind(itemView)
    }
    class ReceiveMsgHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var binding:SendMsgBinding = SendMsgBinding.bind(itemView)
    }
    init {
        if (messages != null){
            this.messages = messages
        }
        this.senderRoom = senderRoom
        this.receiveRoom = receiveRoom
    }




}
