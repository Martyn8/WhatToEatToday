package com.example.whattoeattoday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val initialImage = R.drawable.ic_twotone_question_mark_24 //question mark image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = DataSet
        val imageView = findViewById<ImageView>(R.id.imageView2)
        val textView = findViewById<TextView>(R.id.restaurantName)
        val button = findViewById<Button>(R.id.button)
        imageView.setBackgroundResource(initialImage)

        //wait fo button to be clicked
        button?.setOnClickListener {
            //choose random restaurant
            val restaurant = Draw().drawRestaurant(data)
            //if there is any restaurant show the details
            restaurant?.let {
                showRestaurant(restaurant = it, imageView = imageView, textView = textView)
            } ?: disableButton(button = button) //if not disable button
        }
    }

    private fun showRestaurant(restaurant: String, imageView: ImageView, textView: TextView) {
        //name of the restaurant is the name of image
        //get rid of special characters and spaces
        val image = restaurant
            .lowercase()
            .trim()
            .replace("ö", "o")
            .filter { character ->
                character.isLetterOrDigit()
            }

        // accessing our custom image which we added in drawable folder
        val imgResId = this.resources.getIdentifier(image, "drawable", this.packageName)
        imageView.setBackgroundResource(imgResId)
        textView.text = "Let's go to ${restaurant}!"
    }

    private fun disableButton(button: Button) {
        button.isEnabled = false
        showToast()
    }

    private fun showToast() {
        val toast = Toast.makeText(
            applicationContext, "You used up all of your chances!", Toast.LENGTH_SHORT
        )
        toast.show()
    }
}