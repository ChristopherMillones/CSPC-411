package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.User

class ExampleAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.example_item,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = userList[position]

        // holder.imageView.setImageResource(currentItem.imageResource)
        holder.textview1.text = currentItem.date
        holder.textview2.text = currentItem.class_num
        holder.textview3.text = currentItem.type.toString()
    }

    override fun getItemCount() = userList.size

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        // val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textview1: TextView = itemView.findViewById(R.id.date_display)
        val textview2: TextView = itemView.findViewById(R.id.class_display)
        val textview3: TextView = itemView.findViewById(R.id.type_display)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(userList.get(position))
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

}