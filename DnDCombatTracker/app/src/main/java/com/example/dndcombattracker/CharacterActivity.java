package com.example.dndcombattracker;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        Character character = (Character) getIntent().getSerializableExtra(CharacterAdapter.CHARACTER_EXTRA);
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


    public void setNameText()
    {
        nameText.setText(character.getCharacterName());
    }

    public void setStatsText()
    {
        typeText.setText(character.getCharacterType());
        armorText.setText(character.getArmorClass());
        initiativeText.setText(character.getCurrentInitiative());
        initiativeText.setText("+" + character.getInitiativeModifier());
    }

    public void setHealthText()
    {
        currentHpText.setText(character.getCurrentHealth());
        tempHpText.setText(character.getTempHP());
    }

    //true is healed, false is damage
    public void updateCurrentHp(boolean healed, int amount) throws Exception
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

    public void changeInitiative(int newInit)
    {
        character.setCurrentInitiative(newInit);
    }

    public void changeInitiativeMod(int newMod)
    {
        character.setInitiativeModifier(newMod);
    }

    //TODO handle changing max health when current isnt at full
    public void changeMaxHealth(int newMax)
    {
        character.setMaxHealth(newMax);
    }

    public void changeArmorClass(int newAC)
    {
        character.setArmorClass(newAC);
    }

    public void changeCharacterName(String newName)
    {
        character.setCharacterName(newName);
    }

    public void buttonWatcher()
    {
        //TODO what here

    }
}
