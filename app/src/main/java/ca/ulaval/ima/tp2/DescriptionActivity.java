package ca.ulaval.ima.tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DescriptionActivity extends AppCompatActivity{

    String prenom;
    String nom;
    String sexe;
    String date;
    String programme;
    private TextView textedescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        prenom = getIntent().getExtras().getString("Prenom");
        nom = getIntent().getExtras().getString("Nom");
        date = getIntent().getExtras().getString("Date");
        sexe = getIntent().getExtras().getString("Sexe");
        programme = getIntent().getExtras().getString("Programme");

        TextView textViewprenom = (TextView)findViewById(R.id.valueprenom);
        TextView textViewnom = (TextView)findViewById(R.id.valuenom);
        TextView textViewsexe = (TextView)findViewById(R.id.valuesexe);
        TextView textViewddn = (TextView)findViewById(R.id.valueddn);
        TextView textViewprogramme = (TextView)findViewById(R.id.valueprogramme);

        textViewprenom.setText(prenom);
        textViewnom.setText(nom);
        textViewddn.setText(date);
        textViewsexe.setText(sexe);
        textViewprogramme.setText(programme);

        textedescription = (TextView)findViewById(R.id.textdescription);
        String data = "";

        StringBuffer buffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.ma_description);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if(is!=null){
            try{
                while((data = reader.readLine()) != null){
                    buffer.append(data+ "n");
                }
                textedescription.setText(buffer);
                is.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
