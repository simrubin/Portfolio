package com.example.lab02;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lab02.provider.Book;
import com.example.lab02.provider.BookViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListBooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText bookIdEt;
    private EditText titleEt;
    private EditText isbnEt;
    private EditText authorEt;
    private EditText descEt;
    private EditText priceEt;

    private BookViewModel bookViewModel;

    ArrayList<Item> data;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyRecyclerAdapter adapter;


    public ListBooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListBooksFragment newInstance(String param1, String param2) {
        ListBooksFragment fragment = new ListBooksFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d_b, container, false);
        recyclerView =  view.findViewById(R.id.frag_recycler_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        data = new ArrayList<>();
        //recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerAdapter();

        recyclerView.setAdapter(adapter);


        bookViewModel= new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getMyBooks().observe(getActivity(), newData -> {
            adapter.setData(newData);
            adapter.notifyDataSetChanged();
        });


        return view;
    }
}