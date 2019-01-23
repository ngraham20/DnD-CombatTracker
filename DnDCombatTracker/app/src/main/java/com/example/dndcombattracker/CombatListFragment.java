package com.example.dndcombattracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CombatListFragment extends Fragment {

    View view;
    private RecyclerView mRecyclerView;
    private ArrayList<Combat> mCombats = new ArrayList<>();
    private CombatAdapter mAdapter;

    private Combat mDialogCombat;

    private static final String TAG = "CombatListFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_combat_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new CombatAdapter(getContext(), mCombats);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton fab = view.findViewById(R.id.add_combat_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginCombatDialog();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       mCombats = CombatMasterList.getInstance().getmCombats();
    }

    private void clearCombatDialog()
    {
        mDialogCombat = null;
    }

    private void cancel()
    {
        clearCombatDialog();
    }

    private void beginCombatDialog()
    {
        clearCombatDialog();
        combatNameDialog();
    }

    private void combatNameDialog()
    {
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(input);
        builder.setTitle("Combat Title");
        builder.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: Setting Combat Name");

                    mDialogCombat = new Combat(input.getText().toString());
                    addCombat(mDialogCombat);
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: Cancel");
                        cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addCombat(Combat combat)
    {
        Combat newboi = new Combat(combat);
        mCombats.add(newboi);

        mAdapter.notifyItemInserted(mCombats.indexOf(newboi));

    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

}
