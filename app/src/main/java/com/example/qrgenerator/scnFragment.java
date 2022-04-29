package com.example.qrgenerator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scnFragment extends Fragment {

    private Button btnScan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_scn,container,false);


         btnScan = view.findViewById(R.id.btnscan);
         btnScan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment((Fragment)scnFragment.this);

                 intentIntegrator.setPrompt("For Flash Use Volume Up Key");
                 intentIntegrator.setBeepEnabled(true);
                 intentIntegrator.setOrientationLocked(true);
                 intentIntegrator.setCaptureActivity(Capture.class);
                 intentIntegrator.initiateScan();

             }
         });

         return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Result");
            builder.setMessage(intentResult.getContents());
            builder.setNeutralButton("Copy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText(intentResult.getContents(),intentResult.getContents());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(),"Saved To ClipBoard",Toast.LENGTH_SHORT).show();

                }
            });
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "You Did Not Scan Any Thing",Toast.LENGTH_SHORT).show();
        }
    }
}

