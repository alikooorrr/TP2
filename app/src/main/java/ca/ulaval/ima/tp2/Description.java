package ca.ulaval.ima.tp2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Description extends Fragment {

    TextView prenom;
    private TextView textedescription;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View descriptionView = inflater.inflate(R.layout.fragment_description, container, false);

        textedescription = descriptionView.findViewById(R.id.textdescription);
        prenom = descriptionView.findViewById(R.id.valueprenom);
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
        return descriptionView;
    }


}
