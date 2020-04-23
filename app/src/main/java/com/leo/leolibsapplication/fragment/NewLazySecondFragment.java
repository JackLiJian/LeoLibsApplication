package com.leo.leolibsapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.leo.leolibsapplication.R;

public class NewLazySecondFragment extends Fragment {


    private Context context;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main, null);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("NewLazy","NewLazySecondFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
