package kg.geektech.newsapp38;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kg.geektech.newsapp38.databinding.FragmentHomeBinding;
import kg.geektech.newsapp38.databinding.FragmentNewsBinding;
import kg.geektech.newsapp38.models.News;


public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        /*getParentFragmentManager().setFragmentResultListener("key1", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String text = (String) result.getSerializable("key");
                Log.e("Home1", "text = " + text);
                binding.editText.setText(text);
            }
        });*/
    }

    private void save() {
        String text = binding.editText.getText().toString();
        if (binding.editText.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Поле не должно быть пустым", Toast.LENGTH_SHORT).show();
        }else {
            News news = new News(text, System.currentTimeMillis());
            Bundle bundle = new Bundle();
            bundle.putSerializable("news", news);
            bundle.putSerializable("edit_news", news);
            getParentFragmentManager().setFragmentResult("rk_news", bundle);
            getParentFragmentManager().setFragmentResult("ed_news", bundle);
            close();
        }
    }

    private void close(){
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}