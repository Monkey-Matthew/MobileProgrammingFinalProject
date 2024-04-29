package com.zybooks.cop4656project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.zybooks.cop4656project.models.Transaction;
import com.zybooks.cop4656project.repo.BudgetRepository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class StatementAdapter extends ArrayAdapter<Transaction> {
    //adapter to display transactions
    private int resourceLayout;
    private Context mContext;
    private BudgetRepository mbudgetRepo;

    public StatementAdapter(Context context, int resource, List<Transaction> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.mbudgetRepo = BudgetRepository.getInstance(context);
    }

    //getview to pull take transaction data and display it
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Transaction transaction = getItem(position);

        if (transaction != null) {
            TextView tv1 = v.findViewById(R.id.textViewDescription);
            TextView tv2 = v.findViewById(R.id.textViewAmount);
            TextView tv3 = v.findViewById(R.id.textViewDate);
            Button deleteButton = v.findViewById(R.id.deleteButton);

            tv1.setText(transaction.getDescription());
            tv2.setText(String.format(Locale.getDefault(), "$%.2f", transaction.getAmount()));
            tv3.setText(new SimpleDateFormat("MM/dd", Locale.getDefault()).format(transaction.getDate()));

            //when the delete button is clicked remove the item from the list
            deleteButton.setOnClickListener(view -> {
                remove(transaction);
                mbudgetRepo.deleteTransaction(transaction);
                notifyDataSetChanged();
            });
        }
        return v;
    }
}
