package com.example.sqlitemultitable.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitemultitable.R
import com.example.sqlitemultitable.model.TableOneModel
import com.example.sqlitemultitable.utils.ItemClickInterface

class ReadDataAdapter(
    var activity: Activity,
    var readList: ArrayList<TableOneModel>,
    var itemclickInterface: ItemClickInterface
) :
    RecyclerView.Adapter<ReadDataAdapter.ViewData>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(activity).inflate(R.layout.read_list, parent, false)
        return ViewData(view)
    }

    override fun getItemCount(): Int {
        return readList.size;
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {

        holder.iName.text = readList[position].name.toString()
        val TAG = "Adapter"
        Log.e(TAG, "onBindViewHolder: Id -- "+readList[position].id.toString() )
        holder.iEmail.text = readList[position].email.toString()
        holder.iDesig.text = readList[position].designation.toString()
        if (readList[position].gender.equals("  Male") || readList[position].gender.equals("  male")){
            holder.avtarIcon.setImageResource(R.drawable.ic_man)
        }else{
            holder.avtarIcon.setImageResource(R.drawable.ic_woman)
        }

        holder.itemView.setOnClickListener {
            itemclickInterface.clickEvent(position);
        }

        holder.iName.setOnLongClickListener{
            //Toast.makeText(activity,"long Click",Toast.LENGTH_LONG).show()
            itemclickInterface.clickEventLong(position);
            true
        }

    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iName = itemView.findViewById<TextView>(R.id.iName);
        var iEmail = itemView.findViewById<TextView>(R.id.iEmail);
        var iDesig = itemView.findViewById<TextView>(R.id.iDesig)
        var avtarIcon = itemView.findViewById<ImageView>(R.id.avtarIcon)

    }
}