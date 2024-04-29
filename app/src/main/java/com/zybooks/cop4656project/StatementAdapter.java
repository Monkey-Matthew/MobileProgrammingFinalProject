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
    private int resourceLayout;
    private Context mContext;
    private BudgetRepository mbudgetRepo;

    public StatementAdapter(Context context, int resource, List<Transaction> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.mbudgetRepo = BudgetRepository.getInstance(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Transaction p = getItem(position);

        if (p != null) {
            TextView tt1 = v.findViewById(R.id.textViewDescription);
            TextView tt2 = v.findViewById(R.id.textViewAmount);
            TextView tt3 = v.findViewById(R.id.textViewDate);
            Button deleteButton = v.findViewById(R.id.deleteButton);

            tt1.setText(p.getDescription());
            tt2.setText(String.format(Locale.getDefault(), "$%.2f", p.getAmount()));
            tt3.setText(new SimpleDateFormat("MM/dd", Locale.getDefault()).format(p.getDate()));

            deleteButton.setOnClickListener(view -> {
                remove(p);
                mbudgetRepo.deleteTransaction(p);
                notifyDataSetChanged();
            });
        }

        return v;
    }
}
