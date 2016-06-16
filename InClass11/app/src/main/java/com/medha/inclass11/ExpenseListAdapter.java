package com.medha.inclass11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Prathyusha on 4/11/16.
 */
public class ExpenseListAdapter extends ArrayAdapter<Expense> {

    List<Expense> mData;


    Context mContext;

    int mResource;
    public ExpenseListAdapter(Context context, int resource, List<Expense> objects) {
        super(context, resource, objects);

        this.mContext = context;

        this.mData = objects ;

        this.mResource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(mResource, parent, false);

        }

        Expense expense = mData.get(position);

        TextView category = (TextView) convertView.findViewById(R.id.categoryName);

        category.setText(expense.getCategory());

        TextView amount = (TextView) convertView.findViewById(R.id.categoryAmount);

        amount.setText("$ " +expense.getAmount());


        return convertView;
    }


}
