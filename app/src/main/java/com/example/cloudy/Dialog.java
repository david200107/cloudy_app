package com.example.cloudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.cloudy.databinding.ErrScreenBinding;

public class Dialog extends DialogFragment {

    ErrScreenBinding errScreenBinding;
    Button restart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

         errScreenBinding=ErrScreenBinding.inflate(inflater,container,false);
         restart=errScreenBinding.restartButton;

         restart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });


         return inflater.inflate(R.layout.err_screen,container,false);
    }

}
