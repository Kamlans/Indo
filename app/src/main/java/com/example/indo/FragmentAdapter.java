package com.example.indo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.indo.sos.SosFragment;
import com.example.indo.userInput.UserInputFragment;
import com.example.indo.currentStatus.CurrentstautsFragment;
import com.example.indo.history.HistoryFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return  new HistoryFragment();
            case 2:
                return  new UserInputFragment();

            case 3:
                return  new SosFragment();
        }
        return new CurrentstautsFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
