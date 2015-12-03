package com.greenlionsteam.mypersonaltrainer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.greenlionsteam.mypersonaltrainer.Models.JsonFetcher;
import com.greenlionsteam.mypersonaltrainer.Models.TrainingModel;

public class ExerciseListFragment extends Fragment {

    ExerciseAdapter adapter;

    private class LoadPostRequestAsync extends AsyncTask<Void, Void, TrainingModel> {
        protected TrainingModel doInBackground(Void... urls) {
            TrainingModel trainingModel = null;
            JsonFetcher jsonFetcher = new JsonFetcher();
            try {
                trainingModel = jsonFetcher.fetchExercises();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return trainingModel;
        }

        protected void onProgressUpdate(Void... progress) {
        }

        protected void onPostExecute(TrainingModel result) {
            TrainingModel model = result;
            if (model != null){
                adapter = new ExerciseAdapter(getActivity(), model.getDaysModels());
                listView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoadPostRequestAsync().execute();
    }

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_feed, container, false);

         listView = ((ListView) v.findViewById(R.id.listView));
        ((ListView) v.findViewById(R.id.listView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.itemClicked(position);
            }
        });
//        v.findViewById(R.id.addNewExercise).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainFeedActivity)getActivity()).showAddingScreen();
//            }
//        });
        return v;
    }
}
