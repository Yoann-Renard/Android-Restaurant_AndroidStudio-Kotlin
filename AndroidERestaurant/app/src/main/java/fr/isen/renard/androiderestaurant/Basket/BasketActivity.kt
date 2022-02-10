package fr.isen.renard.androiderestaurant.Basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.renard.androiderestaurant.R
import fr.isen.renard.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.renard.androiderestaurant.Basket.Basket
import android.content.Intent
import fr.isen.renard.androiderestaurant.Registration.UserActivity

class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadlist()
        binding.orderButton.setOnClickListener{
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadlist(){
        val basket = Basket.getBasket(this)
        val items=basket.items
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
        binding.recyclerview.adapter=BasketAdapter(items) {
            basket.removeItem(it)
            basket.save(this)
            loadlist()
        }
    }
}