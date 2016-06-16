package edu.uncc.pizza.pizza;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TOPPINGS = "TOPPINGS";
    public static final String IS_DELIVERED = "DELIVERY";
    Context mContext;
    Button add, clear, checkout;
    ProgressBar pr;
    CheckBox delivery;
    AlertDialog alert;
    ArrayList<Integer> items = new ArrayList<>();
    String item[];
    int resourceId[] = {R.drawable.bacon, R.drawable.cheese, R.drawable.garlic, R.drawable.green_pepper, R.drawable.mushroom, R.drawable.olives, R.drawable.onion, R.drawable.red_pepper};
    TableRow trow1, trow2;
    void drawToppings() {
        trow1.removeAllViews();
        trow2.removeAllViews();
        for (int i = 0; i < items.size(); i++) {
            Toppings img = new Toppings(this);
            img.topping = items.get(i);
            img.setImageResource(resourceId[items.get(i)]);
            TableRow.LayoutParams params = new TableRow.LayoutParams(60, 60);
            img.setLayoutParams(params);
            img.setOnClickListener(img);
            if (i < 5)
                trow1.addView(img);
            else
                trow2.addView(img);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        item = getResources().getStringArray(R.array.toppings);
        trow1 = (TableRow) findViewById(R.id.trow1);
        trow2 = (TableRow) findViewById(R.id.trow2);
        pr = (ProgressBar) findViewById(R.id.progressBar);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose Topping");

        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items.size() < 10) {
                    items.add(which);
                    drawToppings();
                    pr.setProgress(items.size() * 10);

                } else
                    Toast.makeText(MainActivity.this, "Maximum Capacity!", Toast.LENGTH_LONG).show();
            }
        });
        alert = builder.create();
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.show();
            }
        });
        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.clear();
                trow1.removeAllViews();
                trow2.removeAllViews();
            }
        });
        checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, OrderActivity.class);
                i.putExtra(IS_DELIVERED, delivery.isChecked());
                i.putExtra(TOPPINGS, items);
                startActivity(i);
                finish();
            }
        });
        delivery = (CheckBox) findViewById(R.id.checkBox);
    }

    class Toppings extends ImageView implements View.OnClickListener {
        int topping = -1;

        public Toppings(Context context) {
            super(context);
        }

        @Override
        public void onClick(View v) {
            items.remove(items.indexOf(topping));
            drawToppings();
            pr.setProgress(items.size() * 10);
        }
    }
}

