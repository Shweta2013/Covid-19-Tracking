package com.example.covid_19tracking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StateDataAdapter(private val context: MainActivity, private val states: List<States>):
    RecyclerView.Adapter<StateDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_state, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state = states[position]
        holder.bind(state)
    }

    override fun getItemCount() = states.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvState = itemView.findViewById<TextView>(R.id.tvState)
        val tvDate = itemView.findViewById<TextView>(R.id.tvDateValue)
        val tvPositive = itemView.findViewById<TextView>(R.id.tvPositiveValue)
        val tvHospitalized = itemView.findViewById<TextView>(R.id.tvHospitalizedValue)
        val tvDeath = itemView.findViewById<TextView>(R.id.tvDeathValue)

        fun bind(state: States) {
            tvState.text = "State: ${state.name}"
            tvDate.text = state.date.toString()
            tvPositive.text = state.positiveCases.toString()
            tvHospitalized.text = state.hospitalizedCurrently.toString()
            tvDeath.text = state.deathCount.toString()
        }
    }
}
