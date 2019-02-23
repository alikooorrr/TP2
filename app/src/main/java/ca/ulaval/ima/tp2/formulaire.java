package ca.ulaval.ima.tp2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Button;
import java.util.Calendar;


public class formulaire extends Fragment {

    private EditText monprenom;
    private EditText monnom;
    private EditText madate;
    private RadioGroup radioGroup;
    private RadioButton radioButton;


    DatePickerDialog datePickerDialog;
    Button enregistre;
    Spinner monspinner = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState){
        final View formulaireView = inflater.inflate(R.layout.fragment_formulaire,container,false);

        monprenom = formulaireView.findViewById(R.id.editprenom);
        monnom = formulaireView.findViewById(R.id.editnom);
        madate = formulaireView.findViewById(R.id.editddn);
        radioGroup = formulaireView.findViewById(R.id.rdgrp);
        monspinner = formulaireView.findViewById(R.id.spinner);


        monprenom.addTextChangedListener(textWatcher);
        monnom.addTextChangedListener(textWatcher);
        madate.addTextChangedListener(textWatcher);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(container.getContext(),R.array.listprogramme,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        monspinner.setAdapter(adapter);
        enregistre = formulaireView.findViewById(R.id.btnsoumettre);
        madate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar calendrier = Calendar.getInstance();
                 final int jour = calendrier.get(Calendar.DAY_OF_MONTH);
                 final int mois = calendrier.get(Calendar.MONTH);
                 final int annee = calendrier.get(Calendar.YEAR);
                 datePickerDialog = new DatePickerDialog(container.getContext(), new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            madate.setText(annee +"-"+ (mois+1) + "-"+ jour);
                     }
                 },annee,mois,jour);
                 datePickerDialog.show();
             }
         });

        enregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom = monprenom.getText().toString();
                String nom = monnom.getText().toString();
                String date = madate.getText().toString();
                int radiob = radioGroup.getCheckedRadioButtonId();
                radioButton = formulaireView.findViewById(radiob);
                String sexe = radioButton.getText().toString();
                String programme = monspinner.getSelectedItem().toString();


                Intent intent = new Intent(container.getContext(),DescriptionActivity.class);
                intent.putExtra("Prenom",prenom);
                intent.putExtra("Nom",nom);
                intent.putExtra("Date",date);
                intent.putExtra("Sexe",sexe);
                intent.putExtra("Programme",programme);

                startActivity(intent);
            }
        });

        return formulaireView;

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String firstname = monprenom.getText().toString().trim();
            String lastname = monnom.getText().toString().trim();
            String bornday = madate.getText().toString().trim();

            enregistre.setEnabled(!firstname.isEmpty() && !lastname.isEmpty() && !bornday.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
