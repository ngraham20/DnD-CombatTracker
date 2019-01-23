package com.example.dndcombattracker;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class CharacterActivity extends AppCompatActivity {

    private Character character;

    private TextView nameText = (TextView) findViewById(R.id.characterName);

    private TextView typeText = (TextView) findViewById(R.id.characterType);
    private TextView armorText = (TextView) findViewById(R.id.armorClass);
    private TextView initiativeText = (TextView) findViewById(R.id.currentInitiative);
    private TextView initModText = (TextView) findViewById(R.id.initiativeMod);

    private TextView typeTextDisplay = (TextView) findViewById(R.id.charTypeDisplay);
    private TextView armorTextDisplay = (TextView) findViewById(R.id.armorClassDisplay);
    private TextView initiativeTextDisplay = (TextView) findViewById(R.id.currentInitiativeDisplay);
    private TextView initModTextDisplay = (TextView) findViewById(R.id.initiativeModDisplay);

    private TextView currentHpText = (TextView) findViewById(R.id.currentHP);
    private TextView tempHpText = (TextView) findViewById(R.id.tempHP);

    private Button healButton = (Button) findViewById(R.id.heal);
    private EditText healthEdit = (EditText) findViewById(R.id.healOrDamage);
    private Button damageButton = (Button) findViewById(R.id.damage);

    private CheckBox saveSuccess1 = (CheckBox) findViewById(R.id.saveSuccessOne);
    private CheckBox saveSuccess2 = (CheckBox) findViewById(R.id.saveSuccessTwo);
    private CheckBox saveSuccess3 = (CheckBox) findViewById(R.id.saveSuccessThree);

    private CheckBox saveFail1 = (CheckBox) findViewById(R.id.savingFailureOne);
    private CheckBox saveFail2 = (CheckBox) findViewById(R.id.savingFailureTwo);
    private CheckBox saveFail3 = (CheckBox) findViewById(R.id.savingFailureThree);

    private Button changeInitButton = (Button) findViewById(R.id.changeInitiative);
    private EditText editInit = (EditText) findViewById(R.id.editInitiative);

    private Button changeInitModButton = (Button) findViewById(R.id.changeInitMod);
    private EditText editInitMod = (EditText) findViewById(R.id.editInitMod);

    private Button changeHPButton = (Button) findViewById(R.id.changeMaxHP);
    private EditText editHp = (EditText) findViewById(R.id.editHP);

    private Button changeACButton = (Button) findViewById(R.id.changeAC);
    private EditText editAC = (EditText) findViewById(R.id.editAC);

    private Button changeNameButton = (Button) findViewById(R.id.changeName);
    private EditText editName = (EditText) findViewById(R.id.editName);

    //TODO add character's tempHp mods to class character, xml and here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        //TODO set actual character in here???

        setNameText();
        setHealthText();
        setStatsText();
        editName.setText(character.getCharacterName());
        editInit.setText(character.getCurrentInitiative());
        editInitMod.setText(character.getInitiativeModifier());
        editAC.setText(character.getArmorClass());
        editHp.setText(character.getCurrentHealth());

        changeInitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInit.getText().toString();

            }
        });

    }

    public void setNameText()
    {
        nameText.setText(character.getCharacterName());
    }

    public void setStatsText()
    {
        typeText.setText(character.getCharacterType());
        armorText.setText(character.getArmorClass());
        initiativeText.setText(Integer.toString(character.getCurrentInitiative()));
        initiativeText.setText("+" + Integer.toString(character.getInitiativeModifier()));
    }

    public void setHealthText()
    {
        currentHpText.setText("Current HP: " + Integer.toString(character.getCurrentHealth()));
        tempHpText.setText("Temp HP: " + Integer.toString(character.getCurrentHealth()));
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
