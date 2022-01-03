package com.example.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.databinding.DialogAddShoppingItemBinding

//class AddShoppingItemDialog (context: Context, var addDialogListener: AddDialogListener): AppCompatDialog(context) {
//    private var binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
//
//    override fun onCreate(savedInstanceState: Bundle?){
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        binding.tvAdd.setOnClickListener{
//            val name = binding.etName.toString()
//            val amount = binding.etAmount.toString()
//
//            if(name.isEmpty() || amount.isEmpty()){
//                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            val item = ShoppingItem(name, amount.toInt())
//            addDialogListener.onAddButtonClicked(item)
//            dismiss()
//        }
//        binding.tvCancel.setOnClickListener{
//            cancel()
//        }
//    }
//
//}

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_shopping_item)

        var tvAdd =findViewById<TextView>(R.id.tvAdd)
        var tvCancel =findViewById<TextView>(R.id.tvCancel)
        tvAdd?.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName)?.text.toString()
            val amount = findViewById<EditText>(R.id.etAmount)?.text.toString()
            if(name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel?.setOnClickListener {
            cancel()
        }
    }
}