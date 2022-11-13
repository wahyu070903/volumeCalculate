package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtwidth: EditText;
    private lateinit var edtheight: EditText;
    private lateinit var edtlength: EditText;
    private lateinit var btnCalculate: Button;
    private lateinit var result: TextView;

    companion object {
        private const val STATE_RESULT = "state_result";
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtwidth = findViewById(R.id.edt_width);
        edtheight = findViewById(R.id.edt_height);
        edtlength = findViewById(R.id.edt_lenght);
        btnCalculate = findViewById(R.id.btn_calculate);
        result = findViewById(R.id.tv_result);
        btnCalculate.setOnClickListener(this);

        if(savedInstanceState != null){
            val temp_res = savedInstanceState.getString(STATE_RESULT);
            result.text = temp_res;
        }
    }

    override fun onClick(p0: View?) {
        if(p0?.id == R.id.btn_calculate){
            val inputLenght = edtlength.text.toString().trim();
            val inputWidth = edtwidth.text.toString().trim();
            val inputHeight = edtheight.text.toString().trim();
            if(emptyCheck(edtlength,edtheight,edtwidth)){
                val volume = inputLenght.toDouble() * inputWidth.toDouble() * inputHeight.toDouble();
                result.text = volume.toString();
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, result.text.toString())
    }
    private fun emptyCheck(vararg fields: EditText):Boolean {
        var anyEmpty = false;
        for(input in fields){
            if(input.text.isEmpty()){
                input.error = "this field cant be null";
                anyEmpty = true;
            }
        }
        if(anyEmpty) return false;
        return true;
    }
}