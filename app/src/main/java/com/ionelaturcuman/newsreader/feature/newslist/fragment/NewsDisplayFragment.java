package com.ionelaturcuman.newsreader.feature.newslist.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ionelaturcuman.newsreader.databinding.MainFragmentBinding;
import com.ionelaturcuman.newsreader.feature.newslist.model.NewsListViewModel;
import com.ionelaturcuman.newsreader.feature.newslist.model.factory.ViewModelFactory;
import com.ionelaturcuman.newsreader.navigator.AlertNavigator;

import com.ionelaturcuman.newsreader.R;

public class NewsDisplayFragment extends Fragment {

    private NewsListViewModel mViewModel;
    private AlertNavigator alertNavigator;

    public static NewsDisplayFragment newInstance() {
        return new NewsDisplayFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertNavigator = new AlertNavigator(getChildFragmentManager(), requireContext());

        changeTopLineColor();

        mViewModel = new ViewModelProvider(this,
                new ViewModelFactory(requireActivity().getApplication())).get(NewsListViewModel.class);
        mViewModel.error.observe(this, throwable -> alertNavigator.showErrorFor(throwable));
        mViewModel.openLink.observe(this, this::openLink);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MainFragmentBinding binding = MainFragmentBinding.inflate(inflater, container, false);
        binding.setViewModel(mViewModel);
        return binding.getRoot();
    }

    private void openLink(@NonNull String link) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        startActivity(i);
    }

    private void changeTopLineColor() {
        Window window = this.getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat
                    .getColor(getActivity().getApplicationContext(),
                            R.color.top_bar_color));
    }


}