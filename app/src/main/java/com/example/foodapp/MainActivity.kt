package com.example.foodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfFoods = ArrayList<Food>()
    var adapter: FoodAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfFoods.add(Food("Coffee", "Your Best friend at night", R.drawable.coffee_pot))
        listOfFoods.add(Food("Espresso", "Another form of Coffee", R.drawable.espresso))
        listOfFoods.add(Food("French Fries", "Para pap pap pa", R.drawable.french_fries))
        listOfFoods.add(Food("Strawberry", "Nah, Overrated", R.drawable.strawberry_ice_cream))
        listOfFoods.add(Food("Sugar Cubes", "Unhealthy", R.drawable.sugar_cubes))
        listOfFoods.add(Food("Honey","Swetty Sweety",R.drawable.honey))

        adapter = FoodAdapter(listOfFoods, this)
        gvListFood.adapter = adapter

    }

    class FoodAdapter(private val listOfFood: ArrayList<Food>, private val context: Context) :
        BaseAdapter() {

        override fun getCount(): Int {
            return listOfFood.size
        }

        override fun getItem(position: Int): Any {
            return listOfFood[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val food = listOfFood[position]
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val foodView = inflater.inflate(R.layout.food_ticket, null)
            foodView.ivFoodImage.setImageResource(food.image)
            foodView.tvName.text = food.name

            foodView.ivFoodImage.setOnClickListener {
                val intent = Intent(context, FoodDetails::class.java)
                intent.putExtra("name", food.name)
                intent.putExtra("des", food.des)
                intent.putExtra("image", food.image)
                context.startActivity(intent)
            }

            return foodView
        }

    }

}

