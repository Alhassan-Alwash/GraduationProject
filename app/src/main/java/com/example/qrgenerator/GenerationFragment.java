package com.example.qrgenerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;


public class GenerationFragment extends Fragment {
    private Button txtBtn;
    private  Button phnBtn;
    private  Button mailBtn;
    private  Button locBtn;
    private  Button webBtn;
    private  Button contBtn;



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_gen, container, false);

        txtBtn = view.findViewById(R.id.txtbtn);
        phnBtn = view.findViewById(R.id.phnbtn);
        mailBtn = view.findViewById(R.id.mailbtn);
        locBtn = view.findViewById(R.id.locbtn);
        contBtn = view.findViewById(R.id.conbtn);
        webBtn = view.findViewById(R.id.webbtn);

        //----------- Text Button -----------//

        txtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),txtAct.class);
                startActivity(intent);
            }
        });

        //----------- Phone Button -----------//

        phnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),PhnAct.class);
                startActivity(intent);
            }
        });

        //----------- Mail Button -----------//

        mailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MailAct.class);
                startActivity(intent);
            }
        });

        //----------- Location Button -----------//

        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),LocAct.class);
                startActivity(intent);
            }
        });

        //----------- Weblink Button -----------//

        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WebAct.class);
                startActivity(intent);
            }
        });

        //----------- Contact Button -----------//

        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ContAct.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

