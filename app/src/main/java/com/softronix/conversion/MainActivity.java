package com.softronix.conversion;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Spinner unitTypeSpinner;

    private EditText amountTextView;

    TextView teaspoonTextView, tablespoonTextView, cupTextView, ounceTextView,
            pintTextView, quartTextView, gallonTextView, poundTextView,
            milliliterTextView, literTextView, milligramTextView, kilogramTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fills the spinner with the unit options
        addItemsToUnitTypeSpinner();

        // Add listener to the Spinner
        addListenerToUnitTypeSpinner();

        // Get a reference to the edit text view to retrieve the amount of the unit type
        amountTextView = (EditText) findViewById(R.id.amount_text_view);

        initializeTextViews();

    }

    public void initializeTextViews(){

        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);
        tablespoonTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ounceTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        milliliterTextView = (TextView) findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        milligramTextView = (TextView) findViewById(R.id.mg_text_view);
        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);

    }

    public void addItemsToUnitTypeSpinner(){

        // Get a reference to the spinner
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_types, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }

    public void addListenerToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3)
            {
                // Get the item selected in the Spinner
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();

                // Verify if I'm converting from teaspoon so that I use the right
                // conversion algorithm
                checkIfConvertingFromTsp(itemSelectedInSpinner);

            }
            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO maybe add something here later
            }
        });
    }

    public void checkIfConvertingFromTsp(String currentUnit){

        if(currentUnit.equals("teaspoon")){

            updateUnitTypesUsingTsp(Quantity.Unit.tsp);

        } else {

            if(currentUnit.equals("tablespoon")){

                updateUnitTypesUsingOther(Quantity.Unit.tbs);

            } else if(currentUnit.equals("cup")){

                updateUnitTypesUsingOther(Quantity.Unit.cup);

            } else if(currentUnit.equals("ounce")){

                updateUnitTypesUsingOther(Quantity.Unit.oz);

            } else if(currentUnit.equals("pint")){

                updateUnitTypesUsingOther(Quantity.Unit.pint);

            } else if(currentUnit.equals("quart")){

                updateUnitTypesUsingOther(Quantity.Unit.quart);

            } else if(currentUnit.equals("gallon")){

                updateUnitTypesUsingOther(Quantity.Unit.gallon);

            } else if(currentUnit.equals("pound")){

                updateUnitTypesUsingOther(Quantity.Unit.pound);

            } else if(currentUnit.equals("milliliter")){

                updateUnitTypesUsingOther(Quantity.Unit.ml);

            } else if(currentUnit.equals("liter")){

                updateUnitTypesUsingOther(Quantity.Unit.liter);

            } else if(currentUnit.equals("milligram")){

                updateUnitTypesUsingOther(Quantity.Unit.mg);

            } else {

                updateUnitTypesUsingOther(Quantity.Unit.kg);

            }

        }

    }

    public void updateUnitTypesUsingTsp(Quantity.Unit currentUnit){

        // Convert the value in the EditText box to a double
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

        // Combine value to unit
        String teaspoonValueAndUnit = doubleToConvert + " tsp";

        // Change the value for the teaspoon TextView
        teaspoonTextView.setText(teaspoonValueAndUnit);

        // Update all the Unit Text Fields
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.kg, kilogramTextView);

    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit unitConvertingTo,
                                            TextView theTextView){

        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(Quantity.Unit currentUnit){

        // Convert the value in the EditText box to a double
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

        // Create a Quantity using the teaspoon unit
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        // Create the String for the teaspoon TextView
        String valueInTeaspoons = currentQuantitySelected.to(Quantity.Unit.tsp).toString();

        // Set the text for the teaspoon TextView
        teaspoonTextView.setText(valueInTeaspoons);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.tbs, tablespoonTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.cup, cupTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.oz, ounceTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.pint, pintTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.quart, quartTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.gallon, gallonTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.pound, poundTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.ml, milliliterTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.liter, literTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.mg, milligramTextView);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.kg, kilogramTextView);


        // Set the currently selected unit to the number in the EditText
        if(currentUnit.name().equals(currentQuantitySelected.unit.name())){

            // Create the TextView text by taking the value in EditText and adding
            // on the currently selected unit in the spinner
            String currentUnitTextViewText = doubleToConvert + " " +
                    currentQuantitySelected.unit.name();

            // Create the TextView name to change by getting the currently
            // selected quantities unit name and tacking on _text_view
            String currentTextViewName = currentQuantitySelected.unit.name() +
                    "_text_view";

            // Get the resource id needed for the textView to use in findViewById
            int currentId = getResources().getIdentifier(currentTextViewName, "id",
                    MainActivity.this.getPackageName());

            // Create an instance of the TextView we want to change
            TextView currentTextView = (TextView) findViewById(currentId);

            // Put the right data in the TextView
            currentTextView.setText(currentUnitTextViewText);

        }

    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit currentUnit,
                                            Quantity.Unit preferredUnit, TextView targetTextView){

        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewText = currentQuantitySelected.to(Quantity.Unit.tsp).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }

}