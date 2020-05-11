package sg.edu.rp.c346.id18015362.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText pax;
    ToggleButton gstBtn;
    ToggleButton svsBtn;
    EditText discount;
    TextView total;
    TextView splitbill;
    Button split;
    Button reset;
    double totalCost;
    int intPax;
    double splitCost;
    int discountGiven;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        pax = findViewById(R.id.pax);
        gstBtn = findViewById(R.id.gstbtn);
        svsBtn = findViewById(R.id.svsbtn);
        discount = findViewById(R.id.discount);
        total = findViewById(R.id.bill);
        splitbill = findViewById(R.id.split_bill);
        split = findViewById(R.id.splitbtn);
        reset = findViewById(R.id.reset_btn);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                if(discount.getText().toString().equals("")) {
                    discountGiven = 0;
                }
                else{
                    String discAmount = discount.getText().toString();
                    discountGiven = Integer.parseInt(discAmount);
                }
                String cost = amount.getText().toString();
                totalCost = Double.parseDouble(cost);
                totalCost = totalCost * ((100 - discountGiven)/100);


                if(svsBtn.isChecked() && !gstBtn.isChecked()) {
                    totalCost = totalCost * (110/100);
                }else if(!svsBtn.isChecked() && gstBtn.isChecked()){
                    totalCost = totalCost * (107/100);
                }else if (svsBtn.isChecked() && gstBtn.isChecked()) {
                    totalCost = totalCost * (110/100);
                    totalCost = totalCost * (107/100);
                }
                String paying = pax.getText().toString();
                intPax = Integer.parseInt(paying);
                splitCost = totalCost/intPax;

                total.setText(String.format("Total Bill: $%.2f", totalCost));
                splitbill.setText(String.format("Each Pays: $%.2f", splitCost));


            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                amount.setText("");
                pax.setText("");
                svsBtn.setChecked(false);
                gstBtn.setChecked(false);
                discount.setText("");
                total.setText("Total Bill:");
                splitbill.setText("Each Pays:");
            }
        });

    }
}
