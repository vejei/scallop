package io.github.zeleven.scallop.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.github.zeleven.scallop.Scallop;
import io.github.zeleven.scallop.di.component.DaggerFragmentComponent;
import io.github.zeleven.scallop.di.component.FragmentComponent;

public abstract class BaseFragment extends Fragment implements BaseContract.View {
    protected FragmentComponent fragmentComponent;
    protected Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((Scallop) (getContext().getApplicationContext()))
                        .getApplicationComponent())
                .build();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
//        fragmentComponent = DaggerFragmentComponent.builder()
//                .applicationComponent(((Scallop) (getContext().getApplicationContext()))
//                        .getApplicationComponent())
//                .build();
        onFragmentViewCreated();
        return view;
    }

    public abstract int getLayout();

    public void onFragmentViewCreated() {}

    @Override
    public boolean isNetworkConnected() {
        return false;
    }
}
