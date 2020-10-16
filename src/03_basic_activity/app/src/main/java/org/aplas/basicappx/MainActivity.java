package org.aplas.basicappx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity {

    private AlertDialog startDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convertBtn = (Button) findViewById(R.id.convertButton);
        inputTxt = (EditText) findViewById(R.id.inputText);
        outputTxt = (EditText) findViewById(R.id.outputText);
        unitOri = (Spinner) findViewById(R.id.oriList);
        unitConv = (Spinner) findViewById(R.id.convList);
        unitType = (RadioGroup) findViewById(R.id.radioGroup);
        roundBox = (CheckBox) findViewById(R.id.chkRounded);
        formBox = (CheckBox) findViewById(R.id.chkFormula);
        imgView = (ImageView) findViewById(R.id.img);
        imgFormula = (ImageView) findViewById(R.id.imgFormula);
        unitType.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                        String checked = checkedRadioButton.getText().toString();
                        if (checked.equals("Distance")){
                            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(unitType.getContext(),
                                    R.array.distList, android.R.layout.simple_spinner_item);
                            imgView.setImageResource(R.drawable.distance);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            unitOri.setAdapter(arrayAdapter);
                            unitConv.setAdapter(arrayAdapter);
                        }else if(checked.equals("Temperature")){
                            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(unitType.getContext(),
                                    R.array.tempList, android.R.layout.simple_spinner_item);
                            imgView.setImageResource(R.drawable.temperature);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            unitOri.setAdapter(arrayAdapter);
                            unitConv.setAdapter(arrayAdapter);
                        }else if(checked.equals("Weight")){
                            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(unitType.getContext(),
                                    R.array.weightList, android.R.layout.simple_spinner_item);
                            imgView.setImageResource(R.drawable.weight);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            unitOri.setAdapter(arrayAdapter);
                            unitConv.setAdapter(arrayAdapter);
                        }
                        inputTxt.setText("0");
                        outputTxt.setText("0");
                    }
                }
        );

        convertBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                doConvert();
            }
        });

        unitOri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        unitConv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        roundBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doConvert();
            }
        });

        formBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (formBox.isChecked()){
                    imgFormula.setVisibility(View.VISIBLE);
                }else{
                    imgFormula.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    protected void onStart(){
        super.onStart();
        startDialog = new AlertDialog.Builder(MainActivity.this).create();
        startDialog.setTitle("Application started");
        startDialog.setMessage("This app can use to convert any units");
        startDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        startDialog.show();
    }

    private Distance dist = new Distance();
    private Temperature temp = new Temperature();
    private Weight weight = new Weight();
    private Button convertBtn;
    private EditText inputTxt, outputTxt;
    private Spinner unitOri, unitConv;
    private RadioGroup unitType;
    private CheckBox roundBox, formBox;
    private ImageView imgView;
    private ImageView imgFormula;

    protected double convertUnit(String type, String oriUnit, String convUnit, double value){
        if (type.equals("Temperature")) {
            return temp.convert(oriUnit,convUnit,value);
        } else if (type.equals("Distance")) {
            return dist.convert(oriUnit,convUnit,value);
        }else {
            return weight.convert(oriUnit,convUnit,value);
        }
    }

    protected String strResult(double val, boolean rounded){
        DecimalFormat f = new DecimalFormat("#.##");
        DecimalFormat f2 = new DecimalFormat("#.#####");
        if(rounded==true){
            return f.format(val);
        }else {
            return f2.format(val);
        }
    }

    public void doConvert(){
        RadioButton radioButton = (RadioButton) findViewById(unitType.getCheckedRadioButtonId());
        Spinner selectedOri = (Spinner) findViewById(R.id.oriList);
        String textOri = selectedOri.getSelectedItem().toString();
        Spinner selectedConv = (Spinner) findViewById(R.id.convList);
        String textConv = selectedConv.getSelectedItem().toString();

        double result = convertUnit(radioButton.getText().toString(), textOri, textConv, Double.parseDouble(String.valueOf(inputTxt.getText())));
        String resultText;

        if (roundBox.isChecked()){
            resultText = strResult(result,  true);
        }else{
            resultText = strResult(result, false);
        }
        outputTxt.setText(resultText);
    }

}