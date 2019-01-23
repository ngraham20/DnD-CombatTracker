package com.example.dndcombattracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// learned much of this from https://www.youtube.com/watch?v=Vyqz_-sJGFk

public class CombatAdapter extends RecyclerView.Adapter<CombatAdapter.CombatViewHolder> {

    private static final String TAG = "combatAdapter";

    private ArrayList<Combat> mcombats = new ArrayList<>();
    private Context mContext;

    public CombatAdapter(Context context, ArrayList<Combat> mcombats) {
        this.mcombats = mcombats;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CombatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.combat_list_item, parent, false);

        return new CombatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CombatViewHolder combatViewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        combatViewHolder.combat_name.setText(mcombats.get(position).getName());


        combatViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mcombats.get(position).getName());

                Toast.makeText(mContext, mcombats.get(position).getName(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mcombats.size();
    }

    public class CombatViewHolder extends RecyclerView.ViewHolder
    {
        TextView combat_name;
        ConstraintLayout parentLayout;

        public CombatViewHolder(@NonNull View itemView) {
            super(itemView);
            combat_name = itemView.findViewById(R.id.combat_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
