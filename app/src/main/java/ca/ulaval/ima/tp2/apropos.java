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


public class apropos extends Fragment {
    private TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewpropos = inflater.inflate(R.layout.fragment_apropos,container,false);
        txt = viewpropos.findViewById(R.id.txtViewapropos);

        String data = "";

        StringBuffer sbuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.a_propos);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if(is != null){
            try{
                while ((data = reader.readLine()) != null){
                    sbuffer.append(data + "n");
                }
                txt.setText(sbuffer);
                is.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return viewpropos;
    }
}
