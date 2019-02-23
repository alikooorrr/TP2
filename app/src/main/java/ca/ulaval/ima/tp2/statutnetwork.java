package ca.ulaval.ima.tp2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class statutnetwork extends Fragment {
    private ImageView imagestatut;
    private TextView txtstatut;
    private Button btnstatut;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View statutView = inflater.inflate(R.layout.fragment_statutnetwork,container,false);

        imagestatut = statutView.findViewById(R.id.imgindicateur);
        txtstatut = statutView.findViewById(R.id.txtindicateur);
        btnstatut = statutView.findViewById(R.id.btnindicateur);

        btnstatut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wifi;
                boolean mobile;

                ConnectivityManager connexion = (ConnectivityManager) container.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connexion.getActiveNetworkInfo();
                if(info != null && info.isConnected()){
                    wifi = info.getType() == ConnectivityManager.TYPE_WIFI;
                    mobile = info.getType() == ConnectivityManager.TYPE_MOBILE;

                    if(wifi){
                        imagestatut.setImageResource(R.drawable.ic_wifi);
                        txtstatut.setText("WIFI");
                    }
                    else if (mobile){
                        imagestatut.setImageResource(R.drawable.ic_data);
                        txtstatut.setText("3G/LTE");
                    }
                }
                else{
                    imagestatut.setImageResource(R.drawable.ic_none);
                    txtstatut.setText("Aucune connexion");
                }

            }
        });
        return statutView;
    }

}
