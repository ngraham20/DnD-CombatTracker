package com.example.dndcombattracker;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class CharacterActivity extends AppCompatActivity {

    private static final String TAG = "CharacterActivity";

    private Character character;

    private TextView nameText;

    private TextView typeText;
    private TextView armorText;
    private TextView initiativeText;
    private TextView initModText;

    private TextView typeTextDisplay;
    private TextView armorTextDisplay;
    private TextView initiativeTextDisplay;
    private TextView initModTextDisplay;

    private TextView currentHpText;
    private TextView tempHpText;

    private Button healButton;
    private EditText healthEdit;
    private Button damageButton;

    private CheckBox saveSuccess1;
    private CheckBox saveSuccess2;
    private CheckBox saveSuccess3;

    private CheckBox saveFail1;
    private CheckBox saveFail2;
    private CheckBox saveFail3;

    private Button changeInitButton;
    private EditText editInit;

    private Button changeInitModButton;
    private EditText editInitMod;

    private Button changeHPButton;
    private EditText editHp;

    private Button changeACButton;
    private EditText editAC;

    private Button changeNameButton;
    private EditText editName;

    public CharacterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Beginning onCreate");
        Log.d(TAG, "onCreate: Calling Super");
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: Setting Layout");
        setContentView(R.layout.activity_character);


        Log.d(TAG, "onCreate: Initializing View Objects");
        initializeViews();

        Log.d(TAG, "onCreate: Getting Intent");
        character = (Character) getIntent().getSerializableExtra(CharacterAdapter.CHARACTER_EXTRA);

        Log.d(TAG, "onCreate: Setting Text Displays");
        setStatsText();

        Log.d(TAG, "onCreate: Watching Buttons");
        buttonWatcher();

    }

    public void buttonWatcher()
    {
        healButton.setOnClickListener(new HpOnClickListener(this, true));
        damageButton.setOnClickListener(new HpOnClickListener(this, false));

        changeInitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newInit = Integer.parseInt(editInit.getText().toString());
                character.setCurrentInitiative(newInit);
                setInitiativeText();

            }
        });

        changeInitModButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newInitMod = Integer.parseInt(editInitMod.getText().toString());
                character.setInitiativeModifier(newInitMod);
                setInitModText();
            }
        });

        changeHPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int oldMax = character.getMaxHealth();

                int newHp = Integer.parseInt(editHp.getText().toString());
                character.setMaxHealth(newHp);

                if(character.getCurrentHealth() == oldMax)
                {
                    character.setCurrentHealth(newHp);
                    setCurrentHealthText();
                }

            }
        });

        changeACButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newAC = Integer.parseInt(editAC.getText().toString());
                character.setArmorClass(newAC);
                setArmorText();
            }
        });

        changeNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editName.getText().toString();
                character.setCharacterName(newName);
                setNameText();
            }
        });
    }


    private class HpOnClickListener implements View.OnClickListener
    {
        private CharacterActivity activity;
        private boolean type;
        public HpOnClickListener(CharacterActivity newActivity, boolean newType)
        {
            activity = newActivity;
            type = newType;
        }

        @Override
        public void onClick(View v)
        {
            int amount = Integer.parseInt(healthEdit.getText().toString());
            activity.updateCurrentHp(type, amount);
        }
    }

    public void setNameText()
    {
        nameText.setText(character.getCharacterName());
    }

    public void setStatsText()
    {
        setTypeText();
        setArmorText();
        setInitiativeText();
        setInitModText();
        setCurrentHealthText();
        setTempHpText();
        setNameText();
    }

    public void setTypeText()
    {
        typeText.setText(character.getCharacterType());
    }

    public void setArmorText()
    {
        armorText.setText(Integer.toString(character.getArmorClass()));
    }

    public void setInitiativeText()
    {
        initiativeText.setText(Integer.toString(character.getCurrentInitiative()));
    }

    public void setInitModText()
    {
        initModText.setText("+" + Integer.toString(character.getInitiativeModifier()));
    }

    public void setCurrentHealthText()
    {
        currentHpText.setText(Integer.toString(character.getCurrentHealth()));
    }

    public void setTempHpText()
    {
        tempHpText.setText(Integer.toString(character.getTempHP()));
    }

    //true is healed, false is damage
    public void updateCurrentHp(boolean healed, int amount)
    {
        if(healed)
        {
            character.heal(amount);
        }
        else
        {
            character.takeDamage(amount);
        }
    }

    private void initializeViews()
    {
        nameText = (TextView) findViewById(R.id.characterName);
        typeText = (TextView) findViewById(R.id.characterType);
        armorText = (TextView) findViewById(R.id.armorClass);
        initiativeText = (TextView) findViewById(R.id.currentInitiative);
        initModText = (TextView) findViewById(R.id.initiativeMod);
        typeTextDisplay = (TextView) findViewById(R.id.charTypeDisplay);
        armorTextDisplay = (TextView) findViewById(R.id.armorClassDisplay);
        initiativeTextDisplay = (TextView) findViewById(R.id.currentInitiativeDisplay);
        initModTextDisplay = (TextView) findViewById(R.id.initiativeModDisplay);
        currentHpText = (TextView) findViewById(R.id.currentHP);
        tempHpText = (TextView) findViewById(R.id.tempHP);
        healButton = (Button) findViewById(R.id.heal);
        healthEdit = (EditText) findViewById(R.id.healOrDamage);
        damageButton = (Button) findViewById(R.id.damage);
        saveSuccess1 = (CheckBox) findViewById(R.id.saveSuccessOne);
        saveSuccess3 = (CheckBox) findViewById(R.id.saveSuccessThree);
        saveSuccess2 = (CheckBox) findViewById(R.id.saveSuccessTwo);
        saveFail1 = (CheckBox) findViewById(R.id.savingFailureOne);
        saveFail2 = (CheckBox) findViewById(R.id.savingFailureTwo);
        saveFail3 = (CheckBox) findViewById(R.id.savingFailureThree);
        changeInitButton = (Button) findViewById(R.id.changeInitiative);
        editInit = (EditText) findViewById(R.id.editInitiative);
        changeInitModButton = (Button) findViewById(R.id.changeInitMod);
        editInitMod = (EditText) findViewById(R.id.editInitMod);
        changeHPButton = (Button) findViewById(R.id.changeMaxHP);
        editHp = (EditText) findViewById(R.id.editHP);
        changeACButton = (Button) findViewById(R.id.changeAC);
        editAC = (EditText) findViewById(R.id.editAC);
        changeNameButton = (Button) findViewById(R.id.changeName);
        editName = (EditText) findViewById(R.id.editName);
    }

}
