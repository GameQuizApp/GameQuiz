package com.kevmartal.gamequiz;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragSettings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button loginbutton, logoutbutton;
    TextView result;
    FirebaseAuth mAuth;
    Switch themeSwitch;

    public FragSettings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragSettings.
     */
    // TODO: Rename and change types and number of parameters
    public static FragSettings newInstance(String param1, String param2) {
        FragSettings fragment = new FragSettings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        loginbutton = view.findViewById(R.id.login_button);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Login.class));
            }
        });

        /*  signupbutton = view.findViewById(R.id.signup_button);
         *  signupbutton.setOnClickListener(new View.OnClickListener() {
         *      @Override
         *      public void onClick(View v) {
         *          startActivity(new Intent(getActivity(), Signup.class));
         *      }
         *  });
         */

        result = view.findViewById(R.id.result_textview);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.result_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                dialog.show();
            }
        });

        logoutbutton = view.findViewById(R.id.logout_button);
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                String toastTxt = getString(R.string.bye);
                Toast.makeText(getContext(), toastTxt, Toast.LENGTH_SHORT).show();
            }
        });

        themeSwitch = view.findViewById(R.id.themeswitch);

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                themeSwitch.setChecked(true);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                themeSwitch.setChecked(false);
                break;
        }

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        return view;
    }
}