package com.example.signish.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.signish.R;

public class app_demo_video extends Fragment {

    VideoView appDemoVideo;
    MediaController mediaController;
    Package aPackage;
    Context context;



    public app_demo_video() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View ui_layout = inflater.inflate(R.layout.fragment_app_demo_video, container, false);

        appDemoVideo = ui_layout.findViewById(R.id.videoView);
        appDemoVideo.setVideoPath("android.resource://"+
                getActivity().getPackageName() +"/"+R.raw.demovideo);

        mediaController = new MediaController(getContext());
        appDemoVideo.setMediaController(mediaController);
        mediaController.setAnchorView(appDemoVideo);
        mediaController.setMediaPlayer(appDemoVideo);





        return ui_layout;
    }

}
